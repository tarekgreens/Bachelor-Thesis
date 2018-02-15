package doctors365.doctorscombd.mysql;


        import android.content.Context;
        import android.os.AsyncTask;
        import android.support.v7.widget.RecyclerView;
        import android.widget.TextView;
        import android.widget.Toast;

        import doctors365.doctorscombd.recyler.MyAdapter;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;

/**
 * Created by Oclemmy on 4/2/2016 for ProgrammingWizards Channel.
 */
public class Parser extends AsyncTask<Void,Void,Integer> {

    String data;
    RecyclerView rv;
    TextView tvSearchResultMessage;
    Context c;
    Boolean noResult=false;

    ArrayList<String> firstNames =new ArrayList<>();
    ArrayList<String> lastNames =new ArrayList<>();
    ArrayList<String> designations =new ArrayList<>();
    ArrayList<String> degrees =new ArrayList<>();
    ArrayList<String> specialties =new ArrayList<>();
    ArrayList<Integer> ids =new ArrayList<>();
    ArrayList<Integer> comments =new ArrayList<>();
    ArrayList<Integer> favorites =new ArrayList<>();
    ArrayList<Float> ratings =new ArrayList<>();


    public Parser(Context c, String data, RecyclerView rv, TextView tvSearchResultMessage) {
        this.c = c;
        this.data = data;
        this.rv = rv;
        this.tvSearchResultMessage=tvSearchResultMessage;
    }

    @Override
    protected Integer doInBackground(Void... params) {



        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);


        if(integer==1)
        {   if(noResult==false)
            rv.setAdapter(new MyAdapter(c, firstNames, lastNames, designations, degrees, specialties, ids, comments, favorites, ratings));
            else
            tvSearchResultMessage.setText("No doctors are found based on your search criteria.");
        }else {
            //Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
            tvSearchResultMessage.setText("No information is available right now");
        }
    }


    private int parse()
    {
        try {
            //String outputType=data.split("LineSeparator")[0];
            //data=data.split("LineSeparator")[1];
            JSONArray ja=new JSONArray(data);
            if(ja.length()==0)
                noResult=true;
            JSONObject jo=null;

            firstNames.clear(); lastNames.clear(); designations.clear(); degrees.clear(); specialties.clear(); ids.clear(); favorites.clear(); comments.clear(); ratings.clear();

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String firstname=jo.getString("firstname");
                firstNames.add(firstname);
                String lastname=jo.getString("lastname");
                lastNames.add(lastname);
                String designation=jo.getString("designation");
                designations.add(designation);
                String degree=jo.getString("degree");
                degrees.add(degree);
                String specialty=jo.getString("city");
                specialties.add(specialty);

                Integer id=Integer.parseInt(jo.getString("id"));
                ids.add(id);
                Integer favorite=Integer.parseInt(jo.getString("favorites"));
                favorites.add(favorite);
                Integer comment=Integer.parseInt(jo.getString("comments"));
                comments.add(comment);
                Float rating=Float.parseFloat(jo.getString("rating"));
                ratings.add(rating);

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}