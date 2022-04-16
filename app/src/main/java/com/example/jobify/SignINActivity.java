package com.example.jobify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.jobify.AdminDashboardActivity;
import com.example.jobify.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignINActivity extends AppCompatActivity {


    //view binding
    private ActivitySigninBinding binding;

    //firebase Auth
    private FirebaseAuth firebaseAuth;

    //progressbar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        //init firebase Auth
        firebaseAuth=FirebaseAuth.getInstance();




       /* binding.noAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });*/

        //handel click , begin login
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

    }

    private String email="" ,password ="";
    private void validateData() {
        email=binding.email.getText().toString();
        password=binding.pass.getText().toString();


        //Vaildate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(SignINActivity.this, "Invalid email Pattern.....", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(SignINActivity.this, "Enter Password....!", Toast.LENGTH_SHORT).show();
        }
        else{
            //data is vaildated begin login
            loginuser();
        }

    }

    private void loginuser() {


        //login User

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //login successful ,check if user is user or Admin
                        checkuser();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(SignINActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void checkuser() {




        // check if user is user or Admin
        //get current user
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();

        //check in db
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        //get user type
                        String userType= ""+snapshot.child("userType").getValue();

                        //check user type
                        if(userType.equals("user")){
                            //this is the simple user open the user Dashboard
                            startActivity(new Intent(SignINActivity.this,DashBoardActivity.class));
                            finish();

                        }else if(userType.equals("admin")){
                            //this the Admin , Open the Admin Dashboard
                            startActivity(new Intent(SignINActivity.this, AdminDashboard.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}