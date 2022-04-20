package com.example.jobify.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jobify.AdminDashboard;
import com.example.jobify.JobDetailsActivity;
import com.example.jobify.databinding.ItemJobBinding;
import com.example.jobify.model.JobModel;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.HolderJob> {

    private Context context;
    public ArrayList<JobModel> jobArrayList;

    public JobAdapter(Context context, ArrayList<JobModel> jobArrayList) {
        this.context = context;
        this.jobArrayList = jobArrayList;
    }

    //view binding
    private ItemJobBinding binding;


    @NonNull
    @Override
    public HolderJob onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //binding
        binding=ItemJobBinding.inflate(LayoutInflater.from(context),parent,false);
        return new HolderJob(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderJob holder, int position) {

        //get data
        JobModel model=jobArrayList.get(position);
        String title=model.getJobTitle();
        String company=model.getCompany();
        String location=model.getLocation();
        String id=model.getUid();
        String des=model.getJobDes();
        String date=model.getDate();
        String jobType=model.getJobtype();
        String url=model.getUrl();



        //set data
        holder.title.setText(title);
        holder.company.setText(company);
        holder.location.setText(location);
        holder.date.setText(date);
        holder.jobtype.setText(jobType);

        Glide.with(holder.imageView.getContext()).load(url).into((ImageView) holder.imageView);

        //for img
       // Glide.with(holder.qualif.getContext()).load(data[position].getProfileImage()).into((ImageView) holder.imgs);


       holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, JobDetailsActivity.class);
                intent.putExtra("jobTitle",title);
                intent.putExtra("companyName", company);
                intent.putExtra("location",location);
                intent.putExtra("date",date);
                intent.putExtra("jobtype",jobType);
                intent.putExtra("description",des);
                intent.putExtra("url",url);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobArrayList.size() ;
    }

    /*-------------------View holder class to hold UI views for item_job.xml---------------*/
    class HolderJob extends RecyclerView.ViewHolder{

        //UI view of item job
        TextView title,company,location,jobtype,date;
        CardView cardView;
        ImageView imageView;
        public HolderJob(@NonNull View itemView) {
            super(itemView);

            title=binding.title;
            company=binding.company;
            location=binding.location;
            jobtype=binding.jobtype;
            date=binding.date;
            cardView=binding.card;

            imageView=binding.imageView;



        }
    }
}
