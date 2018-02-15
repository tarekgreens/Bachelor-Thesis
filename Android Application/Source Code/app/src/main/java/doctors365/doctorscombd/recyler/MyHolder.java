package doctors365.doctorscombd.recyler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import doctors365.doctorscombd.R;
import doctors365.doctorscombd.mysql.DoctorProfile;


/**
 * Created by Oclemmy on 4/2/2016 for ProgrammingWizards Channel.
 */
public class MyHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView tvDesName, tvDegrees, tvSpecialties,tvDescription, tvAppointmentTimes, tvReviews, tvFavorites, tvRatings;
    ArrayList<Integer> ids =new ArrayList<>();
    Context context;

    public MyHolder(View itemView,ArrayList<Integer> ids) {
        super(itemView);
        context=itemView.getContext();
        itemView.setOnClickListener(this);
        this.ids=ids;
        tvDesName=(TextView) itemView.findViewById(R.id.doctorDesName);
        tvDegrees=(TextView) itemView.findViewById(R.id.doctorDegree);
        tvSpecialties=(TextView) itemView.findViewById(R.id.doctorSpecialty);
        tvDescription=(TextView) itemView.findViewById(R.id.tvDrDescription);
        tvAppointmentTimes=(TextView) itemView.findViewById(R.id.tvDrSchedule);
        tvReviews=(TextView) itemView.findViewById(R.id.doctorReviews);
        tvFavorites=(TextView) itemView.findViewById(R.id.doctorFavorites);
        tvRatings=(TextView) itemView.findViewById(R.id.doctorRating);
    }

    @Override
    public void onClick(View view) {
       //Toast.makeText(view.getContext(), "position = " + ids.get(getAdapterPosition())+" ", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(context,DoctorProfile.class);
        myIntent.putExtra("id", ids.get(getAdapterPosition()));
        context.startActivity(myIntent);

    }
}