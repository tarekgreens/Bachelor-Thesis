package doctors365.doctorscombd.mysql;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import doctors365.doctorscombd.R;

/**
 * Created by HP on 22/5/2016.
 */
public class DoctorProfile extends Activity {

    TextView designation,firstname,lastname,phone,website,schedule,rating,description,specialties,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorprofile);
        designation= (TextView) findViewById(R.id.tvDrDesignation);
        firstname= (TextView) findViewById(R.id.tvFirstName);
        lastname= (TextView) findViewById(R.id.tvDrLastName);
        phone= (TextView) findViewById(R.id.tvDrPhone);
        website= (TextView) findViewById(R.id.tvDrWebsite);
        schedule= (TextView) findViewById(R.id.tvDrSchedule);
        rating= (TextView) findViewById(R.id.tvDrRating);
        description= (TextView) findViewById(R.id.tvDrDescription);
        specialties= (TextView) findViewById(R.id.tvSpecialty);
        address=(TextView) findViewById(R.id.tvDrAddress);

        Intent intent = getIntent();
        Integer doctorID= intent.getIntExtra("id",0);




        PostResponseAsyncTask task = new PostResponseAsyncTask(DoctorProfile.this,
                new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                        Log.d("TAG", s);
                        if (s.contains("firstname")) {
                            try {
                                JSONArray ja=new JSONArray(s);
                                JSONObject jo=ja.getJSONObject(0);
                                String drdesignation=jo.getString("designation");
                                designation.setText(drdesignation);
                                String drfirstname=jo.getString("firstname");
                                firstname.setText(drfirstname);
                                String drlastname=jo.getString("lastname");
                                lastname.setText(drlastname);
                                String drspecialty=jo.getString("specialty");
                                specialties.setText(drspecialty);
                                String drphoneNumber=jo.getString("phoneNumber");
                                phone.setText(drphoneNumber);
                                String drwebsite=jo.getString("website");
                                website.setText(drwebsite);
                                String draddress=jo.getString("address1");
                                address.setText(draddress);
                                String drdescription=jo.getString("description");
                                description.setText(drdescription);
                                String drschdule=jo.getString("appointmentTimes");
                                schedule.setText(drschdule);
                                String drRating=jo.getString("rating");
                                rating.setText(drRating);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else if (s.contains("wrong")) {



                        } else {



                        }
                    }
                });

        task.execute("http://138.197.72.75/doctorprofile?doctorid="+String.valueOf(doctorID));


    }
}
