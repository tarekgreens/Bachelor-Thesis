package doctors365.doctorscombd.recyler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import doctors365.doctorscombd.R;

/**
 * Created by Oclemmy on 4/2/2016 for ProgrammingWizards Channel.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder>{

    Context c;
    ArrayList<String> firstnames=new ArrayList<>();
    ArrayList<String> lastnames =new ArrayList<>();
    ArrayList<String> designations =new ArrayList<>();
    ArrayList<String> degrees =new ArrayList<>();
    ArrayList<String> specialties =new ArrayList<>();
    ArrayList<Integer> ids =new ArrayList<>();
    ArrayList<Integer> reviews =new ArrayList<>();
    ArrayList<Integer> favorites =new ArrayList<>();
    ArrayList<Float> ratings =new ArrayList<>();

    public MyAdapter(Context c, ArrayList<String> firstnames, ArrayList<String> lastnames, ArrayList<String> designations, ArrayList<String> degrees, ArrayList<String> specialties, ArrayList<Integer> ids, ArrayList<Integer> reviews, ArrayList<Integer> favorites, ArrayList<Float> ratings) {
        this.c = c;
        this.firstnames = firstnames;
        this.lastnames= lastnames;
        this.designations= designations;
        this.degrees=degrees;
        this.specialties=specialties;
        this.ids=ids;
        this.reviews=reviews;
        this.favorites=favorites;
        this.ratings=ratings;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchresultunit,parent,false);
        MyHolder holder=new MyHolder(v,ids);

        return holder;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        if(designations.get(position)!=null)
        holder.tvDesName.setText(designations.get(position)+" "+firstnames.get(position)+" "+lastnames.get(position));
        else{holder.tvDesName.setText(firstnames.get(position)+" "+lastnames.get(position));}
        if(degrees.get(position)!=null&&!(degrees.get(position).isEmpty())){
            holder.tvDegrees.setVisibility(View.VISIBLE);
            holder.tvDegrees.setText(degrees.get(position)+" ");}
        if(specialties.get(position)!=null&&!(specialties.get(position).isEmpty())){
            holder.tvSpecialties.setVisibility(View.VISIBLE);
            holder.tvSpecialties.setText(specialties.get(position)+" ");}

        if(reviews.get(position)!=null)
            holder.tvReviews.setText(String.valueOf(reviews.get(position)));
        else
            holder.tvReviews.setText("0");

        if(favorites.get(position)!=null)
            holder.tvFavorites.setText(String.valueOf(favorites.get(position)));
        else
            holder.tvFavorites.setText("0");

        if(ratings.get(position)!=null)
            holder.tvRatings.setText(String.valueOf(ratings.get(position)));
        else
            holder.tvRatings.setText("1.0");
    }

    @Override
    public int getItemCount() {
        return firstnames.size();
    }

}