package doctors365.doctorscombd.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import doctors365.doctorscombd.R;
import doctors365.doctorscombd.adapter.SimpleAdapter;

/**
 * Created by HP on 27/5/2016.
 */


public class PlaceList extends Activity {

    RecyclerView rvMostSearchedPlaces,rvAllPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placelist);
        rvMostSearchedPlaces=(RecyclerView)findViewById(R.id.rvMostSearchedPlaces);
        rvMostSearchedPlaces.setLayoutManager(new LinearLayoutManager(this));
        rvMostSearchedPlaces.setAdapter(new SimpleAdapter(PlaceList.this, 2));
        rvAllPlaces=(RecyclerView)findViewById(R.id.rvAllPlaces);
        rvAllPlaces.setLayoutManager(new LinearLayoutManager(this));
        rvAllPlaces.setAdapter(new SimpleAdapter(PlaceList.this, 3));




    }
}


