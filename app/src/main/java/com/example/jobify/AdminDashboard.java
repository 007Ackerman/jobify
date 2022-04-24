package com.example.jobify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.jobify.adapter.JobAdapter;
import com.example.jobify.adapter.JobAdminAdapter;
import com.example.jobify.databinding.ActivityAdminDashboard2Binding;
import com.example.jobify.databinding.ActivitySigninBinding;
import com.example.jobify.model.JobModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener
{
    private FirebaseAuth firebaseAuth;
    private ActivityAdminDashboard2Binding binding;

    private ArrayList<JobModel> jobModelArrayList;

    private JobAdminAdapter jobAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminDashboard2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();




        firebaseAuth=FirebaseAuth.getInstance();
        binding.notif.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               PopupMenu popup=new PopupMenu(AdminDashboard.this,view);
               popup.setOnMenuItemClickListener(AdminDashboard.this);
               popup.inflate(R.menu.sidemenu);
               popup.show();
           }
       });

        loadJobs();




    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch(menuItem.getItemId())
        {
            case R.id.post:
                startActivity(new Intent(AdminDashboard.this,AdminDashboardActivity.class));
                return true;
            case R.id.logout:
                firebaseAuth.signOut();
                checkUser();
                return false;
            default :
                return false;
        }



    }



    private void checkUser() {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser==null){

            //not login , goto main screen
            startActivity(new Intent(AdminDashboard.this,IntroActivity.class));
            finish();
        }
    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.sidemenu,menu);

        return super.onCreateOptionsMenu(menu);
    }*/


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
                jobAdminAdapter = new JobAdminAdapter(AdminDashboard.this,jobModelArrayList);


                //set adapter to recycleview
                binding.recyclerView.setAdapter(jobAdminAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}