package doctors365.doctorscombd.guisearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import doctors365.doctorscombd.R;
import doctors365.doctorscombd.mysql.SenderReceiver;

/**
 * Created by HP on 24/5/2016.
 */
public class SearchResult extends AppCompatActivity {

    RecyclerView rv;
    SearchView sv;
    ImageView noDataImg,noNetworkImg;
    LinearLayout llFilter;
    LinearLayout llMap;
    String location,specialty,distance,gender,minimumrating,availibility,picture,experience;
    HashMap<String,String> hm;
    TextView tvSearchResultMessage;

    //String urlAddress="http://doctors.com.bd/demo/tutorial/searcherMysqli.php";
    String urlAddress="http://138.197.72.75/guisearch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchcontent);

        Intent intent = getIntent();





        hm=new HashMap<String,String>();
        location = intent.getStringExtra("location");
        hm.put("location",location);
        specialty = intent.getStringExtra("specialty");
        hm.put("specialty",specialty);
        distance=intent.getStringExtra("distance");
        hm.put("distance",distance);
        minimumrating = intent.getStringExtra("minimumrating");
        hm.put("minimumrating",minimumrating);
        availibility = intent.getStringExtra("availibility");
        hm.put("availibility",availibility);
        picture=intent.getStringExtra("picture");
        hm.put("picture",picture);
        experience=intent.getStringExtra("experience");
        hm.put("experience",experience);
        gender=intent.getStringExtra("gender");
        hm.put("gender",gender);

        tvSearchResultMessage=(TextView) findViewById(R.id.tvSearchResultMessage);
        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        sv= (SearchView) findViewById(R.id.sv);
        noDataImg= (ImageView) findViewById(R.id.nodataImg);
        noNetworkImg= (ImageView) findViewById(R.id.noserver);
        llFilter=(LinearLayout) findViewById(R.id.llFilter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarstart);
        setSupportActionBar(toolbar);

       // String query=sv.getQuery().toString();
        SenderReceiver sr=new SenderReceiver(SearchResult.this,hm,urlAddress,rv,tvSearchResultMessage,1, noDataImg,noNetworkImg);
        sr.execute();

        sv= (SearchView) findViewById(R.id.sv);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SenderReceiver sr=new SenderReceiver(SearchResult.this,hm,urlAddress,rv,tvSearchResultMessage,1, noDataImg,noNetworkImg);
                sr.execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String query) {


                SenderReceiver sr=new SenderReceiver(SearchResult.this,hm,urlAddress,rv,tvSearchResultMessage,1,noDataImg,noNetworkImg);
                sr.execute();

                return false;
            }
        });

        llFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResult.this, Filter.class);
                intent.putExtra("location",location);
                intent.putExtra("specialty",specialty);
                SearchResult.this.startActivity(intent);
            }
        });










    }
}
