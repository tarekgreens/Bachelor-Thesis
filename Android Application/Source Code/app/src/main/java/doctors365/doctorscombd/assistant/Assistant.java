package doctors365.doctorscombd.assistant;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import doctors365.doctorscombd.R;

/**
 * Created by HP on 1/20/2017.
 */

public class Assistant extends Activity {

    TextView tvOutput;
    Button btnSubmitButton;
    ImageView btnSpeak;
    EditText etQuery;
    ProgressDialog progressDialog;
    RecyclerView rv;
    ImageView noDataImg, noNetworkImg;
    String urlAddress = "http://doctors.com.bd/demo/tutorial/searcherMysqli.php";
    String specialty = "one";
    TextView tv;
    LinearLayout llassistant;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    Context c,ac;
    StreamPlayer streamPlayer;
    private SpeechToText speechService;
    String voice="";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistant);

        btnSubmitButton = (Button) findViewById(R.id.btnSubmitQuery);
        btnSpeak=(ImageView) findViewById(R.id.imageButton);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        etQuery = (EditText) findViewById(R.id.etQuery);

        llassistant = (LinearLayout) findViewById(R.id.llassistant);

        c=this;
        ac=getApplicationContext();

        HashMap<String, String> a = new HashMap<String, String>();
        //SenderReceiver sr = new SenderReceiver(Assistant.this, a, urlAddress, rv, tv, 1, noDataImg, noNetworkImg);
       // sr.execute();
        btnSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String query = etQuery.getText().toString().replace(" ", "%20");
                TextView question = new TextView(c);
                question.setText(etQuery.getText().toString());
                question.setTextColor(getResources().getColor(R.color.colorPrimary));
                llassistant.addView(question);
                HashMap data = new HashMap();
                data.put("query", "");
                PostResponseAsyncTask task = new PostResponseAsyncTask(Assistant.this, data,
                        new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                }
                                Log.d("TAG", s);
                                String outputType=s.split("LineSeparator")[0];
                                String message="";
                                String jsonList="";
                                TextView answer = new TextView(c);
                                if(outputType.equals("textlist")){
                                    message=s.split("LineSeparator")[1];
                                    jsonList=s.split("LineSeparator")[2];
                                    Intent intent = new Intent(Assistant.this, DoctorList.class);
                                    intent.putExtra("jsonList", jsonList);
                                    intent.putExtra("message", message);
                                    startActivity(intent);

                                    /*
                                    RecyclerView rv=new RecyclerView(ac);
                                    rv.setPadding(10,10,10,10);

                                    RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                                            RecyclerView.LayoutParams.WRAP_CONTENT,
                                            RecyclerView.LayoutParams.WRAP_CONTENT
                                    );
                                    rv.setLayoutParams(params);

                                    rv.setAdapter(null);
                                    llassistant.addView(rv);

                                    Parser p=new Parser(c,jsonList,rv,answer);
                                    p.execute();

                                    */

                                } else if(outputType.equals("text")){
                                    message=s.split("LineSeparator")[1];

                                    answer.setText(message);
                                    voice=message;


                                    llassistant.addView(answer);
                                    WatsonTask task = new WatsonTask();
                                    task.execute(new String[]{});
                                }





                                //tv.setText("one");
                                //tvOutput.setText(s);

                            }
                        });

                task.execute("http://138.197.72.75/doctorassistantresult?query=" + query);
            }


        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Assistant Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    etQuery.setText(result.get(0));
                }
                break;
            }

        }
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

