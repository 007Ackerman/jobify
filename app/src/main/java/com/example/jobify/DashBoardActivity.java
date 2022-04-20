package com.example.jobify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
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

        //search
        binding.filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //search as when user type each word
                try{
                    jobAdapter.getFilter().filter(charSequence);
                }
                catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*firebaseAuth.signOut();
                checkUser();*/
                startActivity(new Intent(DashBoardActivity.this,AdminDashboardActivity.class));


            }
        });
    }



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
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            //not login , goto main screen
            startActivity(new Intent(DashBoardActivity.this, IntroActivity.class));
            finish();
        }
       /* else
        {
            //logged in got user info
            String email=firebaseUser.getEmail();

            //get in textview of toolbar
            binding.subtitle.setText(email);
        }
    }*/

   /* private void checkUser() {

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser==null){

            //not login , goto main screen
            startActivity(new Intent(DashBoardActivity.this,IntroActivity.class));
            finish();
        }
    }*/
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder= new AlertDialog.Builder(DashBoardActivity.this);
        builder.setMessage("Are you sure want to leave?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                  dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}