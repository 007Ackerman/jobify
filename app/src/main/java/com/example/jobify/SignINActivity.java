package com.example.jobify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignINActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText email,pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().hide();


        auth= FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lemail,lpass;
                lemail=email.getText().toString();
                lpass=pass.getText().toString();

                auth.signInWithEmailAndPassword(lemail,lpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(SignINActivity.this, DashBoardActivity.class));
                            Toast.makeText(SignINActivity.this,"Logged In",Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText(SignINActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
                }

            });

    }
}