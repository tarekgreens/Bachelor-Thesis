package doctors365.doctorscombd.mysql;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Oclemmy on 4/2/2016 for ProgrammingWizards Channel.
 */
public class DataConverter {

    String query;
    int type=0;

    public DataConverter(String query,int type) {
        if(type==1){
            this.query = query;
        }
        if(type==2){
            this.query = query;
        }
    }

    public String packData()
    {
        JSONObject jo=new JSONObject();
        StringBuffer queryString=new StringBuffer();

        try {
            jo.put("Query",query);


            Boolean firstValue=true;

            Iterator it=jo.keys();

            do{
                String key=it.next().toString();
                String value=jo.get(key).toString();

                if(firstValue)
                {
                    firstValue=false;
                }else {
                    queryString.append("&");
                }

                queryString.append(URLEncoder.encode(key,"UTF-8"));
                queryString.append("=");
                queryString.append(URLEncoder.encode(value,"UTF-8"));

            }while (it.hasNext());

            return queryString.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}