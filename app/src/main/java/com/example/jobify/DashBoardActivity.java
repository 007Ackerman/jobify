package com.example.jobify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.jobify.adapter.JobAdapter;
import com.example.jobify.databinding.ActivityDashboardBinding;
import com.example.jobify.model.JobModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    //binding
    private  ActivityDashboardBinding binding;

    //Firebase Auth
    private FirebaseAuth firebaseAuth;

    //arrayList
    private ArrayList<JobModel> jobModelArrayList;

    //adapter
    private JobAdapter jobAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Hiding the tool bar
        getSupportActionBar().hide();

        //init firebase Auth
        firebaseAuth=FirebaseAuth.getInstance();
        checkUserwetherLogInOrNot();

        loadJobs();


        binding.notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*firebaseAuth.signOut();
                checkUser();*/
                startActivity(new Intent(DashBoardActivity.this,AdminDashboardActivity.class));


            }
        });
    }


  /*  private void checkUser() {
=======
    private void loadJobs() {

        //init arrayList
        jobModelArrayList=new ArrayList<>();

        //get all job posts from database

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Jobs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear arrylist before Adding data in it

                jobModelArrayList.clear();
                for (DataSnapshot ds:snapshot.getChildren()){


                    //get data
                    JobModel jobModel=ds.getValue(JobModel.class);

                    //add to arrayList

                    jobModelArrayList.add(jobModel);
                }

                //setUp adapter
                jobAdapter = new JobAdapter(DashBoardActivity.this,jobModelArrayList);


                //set adapter to recycleview
                binding.recyclerViewJob.setAdapter(jobAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void checkUserwetherLogInOrNot() {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if (firebaseUser==null){
            //not login , goto main screen
            startActivity(new Intent(DashBoardActivity.this,IntroActivity.class));
            finish();
        }
       /* else
        {
            //logged in got user info
            String email=firebaseUser.getEmail();

            //get in textview of toolbar
            binding.subtitle.setText(email);
        }*/
    }

    private void checkUser() {

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser==null){

            //not login , goto main screen
            startActivity(new Intent(DashBoardActivity.this,IntroActivity.class));
            finish();
        }
    }*/
}