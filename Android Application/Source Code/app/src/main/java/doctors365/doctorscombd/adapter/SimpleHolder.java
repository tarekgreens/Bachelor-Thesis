package doctors365.doctorscombd.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import doctors365.doctorscombd.R;
import doctors365.doctorscombd.guisearch.Data;

import static android.app.Activity.RESULT_OK;

/**
 * Created by HP on 27/5/2016.
 */
public class SimpleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvSpecialtyName;
    Activity c;
    ArrayList<String> dataList=new ArrayList<String>();
    Integer selector;

    public SimpleHolder(View itemView,Integer selector,Context c) {
        super(itemView);
        itemView.setOnClickListener(this);
        Data data=new Data();
        this.dataList= data.getDataList(selector);
        this.selector=selector;
        tvSpecialtyName= (TextView) itemView.findViewById(R.id.specialtyName);
        this.c=(Activity) c;

    }


    @Override
    public void onClick(View view) {
        Intent intent=c.getIntent();
        intent.putExtra("selected",dataList.get(getAdapterPosition()));
        c.setResult(RESULT_OK, intent);
        c.finish();
    }
}
