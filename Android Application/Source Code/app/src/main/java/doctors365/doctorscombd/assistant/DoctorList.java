package doctors365.doctorscombd.assistant;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

import doctors365.doctorscombd.R;
import doctors365.doctorscombd.mysql.Parser;

/**
 * Created by HP on 3/21/2017.
 */

public class DoctorList extends Activity{
    RecyclerView rv;
    TextView tv,tv1;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    StreamPlayer streamPlayer;
    private SpeechToText speechService;
    String voice="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorlist);
        rv=(RecyclerView) findViewById(R.id.rvSearchResult);
        tv=(TextView) findViewById(R.id.tvMessageResult);
        tv1=(TextView) findViewById(R.id.textView15);
        Intent intent=getIntent();
        String jsonList = intent.getStringExtra("jsonList");

        String message = intent.getStringExtra("message");
        tv.setText(message);
        voice=message;

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(null);
        Parser p=new Parser(DoctorList.this,jsonList,rv,tv1);
        p.execute();
        WatsonTask task = new WatsonTask();
        task.execute(new String[]{});
    }

    private class WatsonTask extends AsyncTask<String, Void, String> {
        private TextToSpeech initTextToSpeechService() {
            TextToSpeech service = new TextToSpeech();
            String username = "f6c0ab17-58e2-40f0-ab34-841b5bc39911";
            String password = "PtwmnPJfB5Jn";

            service.setUsernameAndPassword(username, password);
            return service;
        }

        @Override
        protected String doInBackground(String... textToSpeak) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //  tvStatus.setText("running the watson thread");
                }
            });
            String a = "ss";
            TextToSpeech textToSpeech = initTextToSpeechService();
            streamPlayer = new StreamPlayer();
            if(voice!=null ||!voice.isEmpty())
                streamPlayer.playStream(textToSpeech.synthesize(String.valueOf(voice), Voice.EN_ALLISON).execute());

/*
            TextToSpeech textToSpeech = initTextToSpeechService();
            streamPlayer = new StreamPlayer();
            streamPlayer.playStream(textToSpeech.synthesize(String.valueOf(etWatson.getText()), Voice.EN_ALLISON).execute());
  */
            return "text to speech done";

        }

        @Override
        protected void onPostExecute(String result) {
            //tvStatus.setText("TTS status: " + result);
        }
    }

}
