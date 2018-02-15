package doctors365.doctorscombd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import doctors365.doctorscombd.R;

/**
 * Created by HP on 3/5/2016.
 */
public class SearchResultUnitAdapter extends BaseAdapter{

    private final Context context;
    private final int resourceID;
    private static LayoutInflater inflater=null;
    List<String> foodList_;
    String[] imageList_;
    int[] caloryList_;
    String[] foodNameArray_;


    public SearchResultUnitAdapter(Context context, int resource, List<String> foodList, String[] imageList, int[] caloryList, String[] foodNameArray) {

        this.context = context;
        this.resourceID = resource;
        this.foodList_ =foodList;
        this.imageList_ =imageList;
        this.caloryList_ =caloryList;
        this.foodNameArray_=foodNameArray;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // View rowView = inflater.inflate(resourceID, parent, false);
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.searchresultunit, null);
        String foodName=foodList_.get(position);
        int foodIndex= Arrays.asList(foodNameArray_).indexOf(foodName);

        /*

        holder.elemfoodName =(TextView) rowView.findViewById(R.id.foodName);
        holder.elemfoodName.setText(foodNameArray_[foodIndex]);
        holder.elemImageName=(ImageView)rowView.findViewById(R.id.foodImage);
        int id = holder.elemImageName.getContext().getResources().getIdentifier(imageList_[foodIndex], "drawable", holder.elemImageName.getContext().getPackageName());
        holder.elemImageName.setImageResource(id);

        holder.elemfoodCalory=(TextView) rowView.findViewById(R.id.caloryValue);
        holder.elemfoodCalory.setText(String.valueOf(Variables.getAssociatedCalory(foodNameArray_[foodIndex])));
        //holder.elemImageName.setImageResource(R.drawable.);


        */
        return rowView;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return foodList_.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView doctorName,specialty,address,distance,rating,reviews,favorites;
        ImageView profilePicture;


    }
}
