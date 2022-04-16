package com.example.jobify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        String date=model.getDate();
        String jobType=model.getJobtype();

        //set data
        holder.title.setText(title);
        holder.company.setText(company);
        holder.location.setText(location);
        holder.date.setText(date);
        holder.jobtype.setText(jobType);
    }

    @Override
    public int getItemCount() {
        return jobArrayList.size() ;
    }

    /*-------------------View holder class to hold UI views for item_job.xml---------------*/
    class HolderJob extends RecyclerView.ViewHolder{

        //UI view of item job
        TextView title,company,location,jobtype,date;
        ImageView imageView;
        public HolderJob(@NonNull View itemView) {
            super(itemView);

            title=binding.title;
            company=binding.company;
            location=binding.location;
            jobtype=binding.jobtype;
            date=binding.date;

            imageView=binding.imageView;



        }
    }
}
