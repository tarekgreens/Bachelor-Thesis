package doctors365.doctorscombd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import doctors365.doctorscombd.adapter.SimpleAdapter;
import doctors365.doctorscombd.guisearch.SearchResult;
import doctors365.doctorscombd.assistant.Assistant;
import doctors365.doctorscombd.list.PlaceList;
import doctors365.doctorscombd.list.SpecialtyList;


public class MainActivity extends AppCompatActivity implements SimpleAdapter.CallbackInterface, NavigationView.OnNavigationItemSelectedListener {

    Button btnSearch, btnAssistant;
    ImageView imSpecialty,imLocation;
    EditText etSpecialty,etLocation;
    TextView tvDistanceValue;
    SeekBar sbDistance;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarstart);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        etSpecialty =(EditText) findViewById(R.id.etSpecialty);
        imSpecialty=(ImageView) findViewById(R.id.imSpecialty);
        imSpecialty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpecialtyList.class);
                MainActivity.this.startActivityForResult(intent,0);
                return;
            }
        });

        etLocation =(EditText) findViewById(R.id.etLocation);
        imLocation =(ImageView) findViewById(R.id.imLocation);
        imLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlaceList.class);
                MainActivity.this.startActivityForResult(intent,1);
                return;


            }
        });


        btnSearch=(Button) findViewById(R.id.searchButton);
        btnAssistant =(Button) findViewById(R.id.btnSpeak);
        btnAssistant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Assistant.class);
                intent.putExtra("location", etLocation.getText().toString());
                intent.putExtra("specialty",etSpecialty.getText().toString());
                MainActivity.this.startActivity(intent);
                return;

            }
            });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchResult.class);
                intent.putExtra("location", etLocation.getText().toString());
                intent.putExtra("specialty", etSpecialty.getText().toString());
                MainActivity.this.startActivity(intent);
                return;


        }
        });

        tvDistanceValue=(TextView) findViewById(R.id.tvDistanceValue);
        sbDistance= (SeekBar) findViewById(R.id.sbDistance);
        sbDistance.setMax(100);
        sbDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub

                tvDistanceValue.setText(String.valueOf(progress));


            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_manage1) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==0)
        {
            if(resultCode==RESULT_OK){
            String selected=data.getStringExtra("selected");
                etSpecialty.setText(selected);
                //   svSpecialty.setQuery(selectedSpecialty,false);
            //svSpecialty.setQueryHint(selectedSpecialty);

            }

        }

        if(requestCode==1)
        {
            if(resultCode==RESULT_OK){
                String selected=data.getStringExtra("selected");
                etLocation.setText(selected);

            }

        }
    }

    @Override
    public void onHandleSelection(int position, String text) {

        Toast.makeText(this, "Selected item in list "+ position + " with text "+ text, Toast.LENGTH_SHORT).show();

        // ... Start a new Activity here and pass the values
        //Intent secondActivity = new Intent(MainActivity.this, DetailActivity.class);
        //secondActivity.putExtra("Text",text);
        //secondActivity.putExtra("Position", position);
        //startActivityForResult(secondActivity, MY_REQUEST);
    }

}
