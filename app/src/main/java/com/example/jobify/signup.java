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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {
    FirebaseAuth auth;
    EditText name,email,phone,pass,pass1;
    Button signup;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();
        database= FirebaseFirestore.getInstance();

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);

        pass=findViewById(R.id.pass);
        pass1=findViewById(R.id.pass1);
        signup=findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(signup.this,"Account is Created",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signup.this,login.class));
                            }
                            else
                            {
                                Toast.makeText(signup.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(signup.this,"Password does not match with Confirm Password",Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}