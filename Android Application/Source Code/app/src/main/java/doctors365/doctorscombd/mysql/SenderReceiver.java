package doctors365.doctorscombd.mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Oclemmy on 4/2/2016 for ProgrammingWizards Channel.
 */
public class SenderReceiver extends AsyncTask<Void,Void,String> {

    Context c;
    String query;
    ProgressDialog pd;
    String urlAddress;
    RecyclerView rv;
    TextView tvSearchResultMessage;
    HashMap<String,String> queryHM;
    ImageView noDataImg,noNetworkImg;
    int type=0;

    public SenderReceiver(Context c, HashMap<String,String> queryHM, String urlAddress, RecyclerView rv, TextView tvSearchResultMessage, int type, ImageView...imageViews) {
        this.c = c;
        this.type=type;
        this.queryHM=queryHM;
        this.query = queryHM.get("query");
        this.query = queryHM.get("location");
        this.urlAddress = urlAddress;
        this.rv = rv;
        this.tvSearchResultMessage=tvSearchResultMessage;

        this.noDataImg=imageViews[0];
        this.noNetworkImg=imageViews[1];
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Search");
        pd.setMessage("Searching...Please wait");
        pd.show();

    }

    @Override
    protected String doInBackground(Void... params) {
        return this.sendAndReceive();
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        pd.dismiss();

        rv.setAdapter(null);

        if(result != null)
        {
            if(! result.contains("null"))
            {

                Parser p=new Parser(c,result,rv,tvSearchResultMessage);
                p.execute();

                noNetworkImg.setVisibility(View.INVISIBLE);
                noDataImg.setVisibility(View.INVISIBLE);
            }else {
                noNetworkImg.setVisibility(View.INVISIBLE);
                noDataImg.setVisibility(View.VISIBLE);
            }

        }else {
            noNetworkImg.setVisibility(View.VISIBLE);
            noDataImg.setVisibility(View.INVISIBLE);
        }
    }

    private String sendAndReceive()  {

        boolean addition=false;
        urlAddress=urlAddress+"?";
        for (String key: queryHM.keySet()) {
           if(key.equals("location")&&!queryHM.get(key).isEmpty()&&queryHM.get(key)!=null) {
               if (addition == true)

                       urlAddress += "&location=" + queryHM.get(key);

               if (addition == false) {
                   urlAddress += "location=" + queryHM.get(key);
                   addition = true;
               }

           }

            if(key.equals("specialty")&&!queryHM.get(key).isEmpty()&&queryHM.get(key)!=null) {
                if (addition == true)
                    urlAddress += "&specialty=" + queryHM.get(key);
                if (addition == false) {
                    urlAddress += "specialty=" + queryHM.get(key);
                    addition = true;
                }

            }

            if(key.equals("distance")&&!queryHM.get(key).isEmpty()&&queryHM.get(key)!=null) {
                if (addition == true)
                    urlAddress += "&distance=" + queryHM.get(key);
                if (addition == false) {
                    urlAddress += "distance=" + queryHM.get(key);
                    addition = true;
                }

            }

            if(key.equals("minimumrating")&&!queryHM.get(key).isEmpty()&&queryHM.get(key)!=null) {
                if (addition == true)
                    urlAddress += "&minimumrating=" + queryHM.get(key);
                if (addition == false) {
                    urlAddress += "minimumrating=" + queryHM.get(key);
                    addition = true;
                }
            }

            if(key.equals("availibility")&&!queryHM.get(key).isEmpty()&&queryHM.get(key)!=null) {
                if (addition == true)
                    urlAddress += "&availibility=" + queryHM.get(key);
                if (addition == false) {
                    urlAddress += "availibility=" + queryHM.get(key);
                        addition = true;
                }

            }

            if(key.equals("picture")&&!queryHM.get(key).isEmpty()&&queryHM.get(key)!=null) {
                if (addition == true)
                    urlAddress += "&picture=" + queryHM.get(key);
                if (addition == false) {
                    urlAddress += "picture=" + queryHM.get(key);
                    addition = true;
                }

            }

            if(key.equals("experience")&&!queryHM.get(key).isEmpty()&&queryHM.get(key)!=null) {
                if (addition == true)
                    urlAddress += "&experience=" + queryHM.get(key);
                if (addition == false) {
                    urlAddress += "experience=" + queryHM.get(key);
                    addition = true;
                }

            }

            if(key.equals("gender")&&!queryHM.get(key).isEmpty()&&queryHM.get(key)!=null) {
                if (addition == true)
                    urlAddress += "&gender=" + queryHM.get(key);
                if (addition == false) {
                    urlAddress += "gender=" + queryHM.get(key);
                    addition = true;
                }

            }


        }




        HttpURLConnection con=Connector.connect(urlAddress);
        if(con==null)
        {
            return null;
        }

        try
        {
            OutputStream os=con.getOutputStream();

            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
            //String s=new DataConverter(query,type).packData();
            bw.write(new DataConverter(query,type).packData());

            bw.flush();

            //RELEASE RES
            bw.close();
            os.close();

            //RESPONSE ???
            int responseCode=con.getResponseCode();
            StringBuffer response=new StringBuffer();

            if(responseCode== HttpURLConnection.HTTP_OK)
            {
                InputStream is=con.getInputStream();

                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                String line;

                if(br != null)
                {
                    while ((line=br.readLine()) != null)
                    {
                        response.append(line+"\n");
                    }
                }else {
                    return null;
                }

                br.close();
                is.close();

                return response.toString();


            }else
            {
                return String.valueOf(responseCode);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}


