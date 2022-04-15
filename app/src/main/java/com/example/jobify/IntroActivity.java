package com.example.jobify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    Button signin,signup;
    //SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getSupportActionBar().hide();

       // SharedPreferences sharedpreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);

       /* int j = sharedpreferences.getInt("key", 0);
        if(j > 0){
            Intent activity = new Intent(getApplicationContext(), DashBoardActivity.class);
            startActivity(activity);
        }*/

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,SignupActivity.class));
                /*int autoSave = 1;
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("key", autoSave);
                editor.apply();*/
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,SignINActivity.class));
            }
        });







    }
}