package com.example.jobify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jobify.databinding.ActivityJobDetailsBinding;
import com.example.jobify.model.JobModel;

import java.util.ArrayList;

public class JobDetailsActivity extends AppCompatActivity {

    //binding
    private ActivityJobDetailsBinding binding;

    private  ImageView imageView;

    //ArrayList to hold list of data
    private ArrayList<JobModel> jobModelArrayList;
    private  String jobTitle, companyName,location,date,jobtype,description,img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityJobDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        //get data from list
        Intent intent=getIntent();
        jobTitle = intent.getStringExtra("jobTitle");
        companyName = intent.getStringExtra("companyName");
        location = intent.getStringExtra("location");
        date = intent.getStringExtra("date");
        jobtype = intent.getStringExtra("jobtype");
        description = intent.getStringExtra("description");
        img=intent.getStringExtra("url");



        imageView=binding.companyLogo;
        Glide.with(imageView.getContext()).load(img).into((ImageView)imageView);



        //binding
        binding.title.setText(jobTitle);
        binding.description.setText(description);
        binding.company1.setText(companyName);
        binding.location.setText(location);
        binding.company2.setText(companyName);
        binding.jobtype.setText(jobtype);





    }
}