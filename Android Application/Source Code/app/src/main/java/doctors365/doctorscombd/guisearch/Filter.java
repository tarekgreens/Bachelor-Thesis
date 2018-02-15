package doctors365.doctorscombd.guisearch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

import doctors365.doctorscombd.MainActivity;
import doctors365.doctorscombd.R;

/**
 * Created by HP on 14/5/2016.
 */
public class Filter extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    SeekBar distance,minimumRating;
    TextView tvDistance,tvMinimumRating,tvDistanceUnit;
    String location="",specialty="",distancekm="",gender="",minimumrating="";
    Boolean availibility=false,experience=false,picture=false;
    Button btnApplyFilter;
    CheckBox cbMale, cbFemale, cbAvailable, cbPicture, cbExperience;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtersearch);
        intent = getIntent();
        location = intent.getStringExtra("location");
        specialty = intent.getStringExtra("specialty");

        ///mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Filter");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF01A3D4));

        distance = (SeekBar) findViewById(R.id.seekBarDistance);
        minimumRating = (SeekBar) findViewById(R.id.sbMinimumRating);
        tvDistance = (TextView) findViewById(R.id.tvDistanceValue);
        tvDistanceUnit = (TextView) findViewById(R.id.tvDistanceUnit);
        tvMinimumRating = (TextView) findViewById(R.id.tvMinimumRatingValue);
        cbMale= (CheckBox) findViewById(R.id.cbMale);
        cbFemale= (CheckBox) findViewById(R.id.cbFemale);
        cbAvailable= (CheckBox) findViewById(R.id.cbAvailable);
        cbPicture= (CheckBox) findViewById(R.id.cbAvailable);
        cbExperience= (CheckBox) findViewById(R.id.cbAvailable);
        btnApplyFilter=(Button) findViewById(R.id.applyfilter);


        minimumRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                tvMinimumRating.setText(String.valueOf(progress));
                minimumrating=String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        distance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                if(Integer.parseInt(String.valueOf(progress))<=1){
                    tvDistance.setText("500");
                    tvDistanceUnit.setText(" m");
                    distancekm="0.5";
                }
                else if (Integer.parseInt(String.valueOf(progress))>1&&Integer.parseInt(String.valueOf(progress))<=2){
                    tvDistance.setText("1");
                    tvDistanceUnit.setText(" km");
                    distancekm="1";
                }
                else if (Integer.parseInt(String.valueOf(progress))>2&&Integer.parseInt(String.valueOf(progress))<=3){
                    tvDistance.setText("2");
                    tvDistanceUnit.setText(" km");
                    distancekm="2";
                }
                else if (Integer.parseInt(String.valueOf(progress))>3&&Integer.parseInt(String.valueOf(progress))<=4){
                    tvDistance.setText("3");
                    tvDistanceUnit.setText(" km");
                    distancekm="3";
                }
                else if (Integer.parseInt(String.valueOf(progress))>4&&Integer.parseInt(String.valueOf(progress))<=5){
                    tvDistance.setText("5");
                    tvDistanceUnit.setText(" km");
                    distancekm="4";
                }
                else if (Integer.parseInt(String.valueOf(progress))>5&&Integer.parseInt(String.valueOf(progress))<=6){
                    tvDistance.setText("10");
                    distancekm="10";
                }
                else if (Integer.parseInt(String.valueOf(progress))>6&&Integer.parseInt(String.valueOf(progress))<=7){
                    tvDistance.setText("25");
                    distancekm="25";
                }
                else if (Integer.parseInt(String.valueOf(progress))>7&&Integer.parseInt(String.valueOf(progress))<=9){
                    tvDistance.setText("50");
                    distancekm="50";
                }
                else if (Integer.parseInt(String.valueOf(progress))>9&&Integer.parseInt(String.valueOf(progress))<=10){
                    tvDistance.setText("100");
                    distancekm="100";
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        cbMale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              cbChangeSelection(v,cbFemale,0);

            }
        });

        cbFemale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cbChangeSelection(cbMale,v,1);

            }
        });

        cbAvailable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                availibility=cbChangeSelection(v);
            }
        });

        cbPicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                picture=cbChangeSelection(v);
            }
        });
        cbExperience.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                experience=cbChangeSelection(v);
            }
        });

        btnApplyFilter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Filter.this, SearchResult.class);
                if(!distancekm.isEmpty())
                    intent.putExtra("distance",distancekm);
                if(!gender.isEmpty())
                    intent.putExtra("gender",gender);
                if(!minimumrating.isEmpty())
                    intent.putExtra("minimumrating",minimumrating);
                if(availibility==true)
                    intent.putExtra("availibility",true);
                if(picture==true)
                    intent.putExtra("picture",true);
                if(experience==true)
                    intent.putExtra("experience",true);
                Filter.this.startActivity(intent);
                return;
            }
        });

    }

    //Self created methods
    //Applicable only for male and female checkbox
    private void cbChangeSelection(View vM, View vF,int i){
        CheckBox cbM=(CheckBox) vM;
        CheckBox cbF=(CheckBox) vF;
        boolean stateM=cbMale.isChecked();
        boolean stateF=cbFemale.isChecked();
        if(stateM==true&&stateF==true)
            if(i==0)
                cbM.setChecked(stateM);
            else
                cbF.setChecked(stateF);
        else if(stateM==false&&stateF==false)
            if(i==0)
                cbF.setChecked(true);
            else
                cbM.setChecked(true);
        else                                 //one true, one false
            if(i==0)
                if(stateM==true){
                cbM.setChecked(true);
                gender="male";}
                else
                cbM.setChecked(false);
            else
                if(stateF==true) {
                    cbF.setChecked(true);
                    gender="female";
                }
                else
                    cbF.setChecked(false);


    }

    //Self created methods
    //Applicable for availibility, have picture and similar
    private boolean cbChangeSelection(View v){
        CheckBox cb=(CheckBox) v;
        boolean state=cb.isChecked();
        if(state==true)
            state=true;
        else
            state=false;
        //now, checkbox ke set koro state er value diye.
        cb.setChecked(state);
        return state;

    }




}

