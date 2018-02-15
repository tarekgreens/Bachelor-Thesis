package doctors365.doctorscombd.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import doctors365.doctorscombd.R;
import doctors365.doctorscombd.adapter.SimpleAdapter;

/**
 * Created by HP on 27/5/2016.
 */


    public class SpecialtyList extends Activity {

    RecyclerView rvMostSearchedSpecialties;

    public SpecialtyList(RecyclerView rvMostSearchedSpecialties) {
        System.out.println();
    }

    public SpecialtyList() {
        System.out.println();
    }


    RecyclerView rvAllSpecialties;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialtylist);
        rvMostSearchedSpecialties = (RecyclerView) findViewById(R.id.rvMostSearchedSpecialties);
        rvMostSearchedSpecialties.setLayoutManager(new LinearLayoutManager(this));
        rvMostSearchedSpecialties.setAdapter(new SimpleAdapter(SpecialtyList.this, 0));
        rvAllSpecialties = (RecyclerView) findViewById(R.id.rvAllSpecialties);
        rvAllSpecialties.setLayoutManager(new LinearLayoutManager(this));
        rvAllSpecialties.setAdapter(new SimpleAdapter(SpecialtyList.this, 1));


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                String selectedSpecialty = data.getStringExtra("specialty");
                String a=selectedSpecialty;

            }
        }


    }
}

