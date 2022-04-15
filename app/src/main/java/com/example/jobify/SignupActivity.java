package com.example.jobify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobify.databinding.ActivitySignupBinding;
import com.example.jobify.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    FirebaseAuth auth;


    //view Binding
    private ActivitySignupBinding binding;

    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        database= FirebaseFirestore.getInstance();





        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateData();
            }
        });



       /* signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname,uemail,uphone,upass,upass1;
                uname=name.getText().toString();
                uemail=email.getText().toString();
                uphone=phone.getText().toString();

                upass=pass.getText().toString();
                upass1=pass1.getText().toString();

                //Object Calling

                User obj=new User();
                obj.setEmail(uemail);
                obj.setName(uname);
                obj.setPhone(uphone);
                obj.setPass(upass1);


                //Authentication
                if(upass1.equals(upass))
                {
                    auth.createUserWithEmailAndPassword(uemail,upass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                database.collection("Users")
                                        .document(uname).set(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {


                                    }
                                });
                                Toast.makeText(SignupActivity.this,"Account is Created",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, SignINActivity.class));
                            }
                            else
                            {
                                Toast.makeText(SignupActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(SignupActivity.this,"Password does not match with Confirm Password",Toast.LENGTH_SHORT).show();
                }



            }
        });
*/
    }

    private  String name= "", email="", phone="", password="";
    private void validateData() {

        name=binding.name.getText().toString().trim();
        email=binding.email.getText().toString().trim();
        password=binding.pass.getText().toString().trim();
        phone=binding.phone.getText().toString().trim();
        String cPassword = binding.pass1.getText().toString().trim();


        //vaildate data
        if(TextUtils.isEmpty(name)){
            Toast.makeText(SignupActivity.this, "Enter you name.....", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(SignupActivity.this, "Invalid email Pattern.....", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(SignupActivity.this, "Enter Password....!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(SignupActivity.this, "Enter Phone....!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cPassword)){
            Toast.makeText(SignupActivity.this, "Confirm Password....!", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(cPassword)){
            Toast.makeText(SignupActivity.this, "Password doesn't match.... ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            createuserAccount();
        }


    }

    private void createuserAccount() {


        //create user in firebase auth
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //account creation is successful now the data in firebase
                        updateUserInfo();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //acount creating failed

                        Toast.makeText(SignupActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void updateUserInfo() {

        //TimeStamp
        long timestamp =System.currentTimeMillis();

        //Creating Uid
        String uid =auth.getUid();

        //setup data to add in db
        HashMap<String, Object> hashMap=new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("email", email);
        hashMap.put("name",name);
        hashMap.put("password",password);
        hashMap.put("phone",phone);
        hashMap.put("Profileimage","");
        hashMap.put("userType", "user"); //option: User or Admin  :: I will add admin manualliy by db
        hashMap.put("timestamp",timestamp);


        //set data to db
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(aVoid -> {
                    //data added to db

                    Toast.makeText(SignupActivity.this, "Account Created...", Toast.LENGTH_SHORT).show();
                    //starting new activity
                    startActivity(new Intent(SignupActivity.this, DashBoardActivity.class ));
                    finish();

                })
                .addOnFailureListener(e -> {

                    Toast.makeText(SignupActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                });



    }
}