package doctors365.doctorscombd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import doctors365.doctorscombd.R;
import doctors365.doctorscombd.guisearch.Data;


/**
 * Created by HP on 27/5/2016.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleHolder> {
        private CallbackInterface mCallback;
        Context c;
        Integer selector;
        ArrayList<String> dataList=new ArrayList<String>();




    public interface CallbackInterface{

        /**
         * Callback invoked when clicked
         * @param position - the position
         * @param text - the text to pass back
         */
        void onHandleSelection(int position, String text);
    }

    public SimpleAdapter(Context c, Integer selector) {
        this.c = c;
        this.selector=selector;
        Data data=new Data();
        this.dataList= data.getDataList(selector);



    }


    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.datalistunit,parent,false);
        SimpleHolder holder=new SimpleHolder(v,selector,c);

        return holder;
    }

    @Override
    public void onBindViewHolder(SimpleHolder holder, final int position) {
        holder.tvSpecialtyName.setText(dataList.get(position));


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
