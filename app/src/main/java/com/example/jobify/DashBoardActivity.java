package com.example.jobify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.jobify.databinding.ActivityDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoardActivity extends AppCompatActivity {

    //binding
    private  ActivityDashboardBinding binding;

    //Firebase Auth
    private FirebaseAuth firebaseAuth;

    //Shimer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Hiding the tool bar
        getSupportActionBar().hide();

        //init firebase Auth
        firebaseAuth=FirebaseAuth.getInstance();


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
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser==null){

            //not login , goto main screen
            startActivity(new Intent(DashBoardActivity.this,IntroActivity.class));
            finish();
        }
    }*/
}