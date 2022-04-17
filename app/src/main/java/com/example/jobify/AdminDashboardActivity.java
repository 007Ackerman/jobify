package com.example.jobify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.jobify.databinding.ActivityAdminDashboardBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.HashMap;
import java.util.Locale;

public class AdminDashboardActivity extends AppCompatActivity {

    //Binding
    private ActivityAdminDashboardBinding binding;

    //firebaseAuth
    private FirebaseAuth auth;

    //for logo upload
    private Uri img;

    //progressbar
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        //init firebase auth
        auth=FirebaseAuth.getInstance();

        //continue ProgressDailog

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

        //handel click attach logo
        binding.uploadLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickLogo();
            }
        });


        //handel back
        binding.backToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //onClick submit
        binding.Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationOfData();
            }
        });
    }

    private void pickLogo() {
        Intent intent=new Intent();
        intent.setType("image/jpeg");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,48);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==48 && resultCode== RESULT_OK && data!=null)
        {
            img=data.getData();
            Toast.makeText(this, "Logo is Selected", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Please Select the logo.......", Toast.LENGTH_SHORT).show();
        }
    }


    private String jobTitle="",company="",location="",jobtype="",date="" ,jobDes="";
    private void validationOfData() {
        jobTitle=binding.jobTitle.getText().toString().trim();
        company=binding.company.getText().toString().trim();
        location=binding.location.getText().toString().trim();
        jobtype=binding.jobtype.getText().toString().trim();
        date=binding.date.getText().toString().trim();
        jobDes=binding.jobDes.getText().toString().trim();


        //validation if not empty
        if(TextUtils.isEmpty(jobTitle)){
            Toast.makeText(this, "Please Enter Job Title.....", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(company)){
            Toast.makeText(this, "Please Enter Name of Company....", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(location)){
            Toast.makeText(this, "Please Enter Location of the company.......", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(jobtype)){
            Toast.makeText(this, "Please Enter Job-Type.....", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(jobDes)){
            Toast.makeText(this, "Please Describe the Job.....", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(date)){
            Toast.makeText(this, "Please Enter Post Date.....", Toast.LENGTH_SHORT).show();
        }
        else if(img==null){
            Toast.makeText(this, "Please Select the Logo of the company.......", Toast.LENGTH_SHORT).show();
        }
        else{
            uploadJobToStorage();
        }

    }

    private void uploadJobToStorage() {
        //step 2  :Adding Job to Storage

        progressDialog.setMessage("Adding Jobs....");
        progressDialog.show();

        //timestamp
        long timestamp =System.currentTimeMillis();


        //path of COMP_LOGO in firebase storage

        String filepathAndName ="LOGOS/"+timestamp;

        //storage refrence


        StorageReference storageReference= FirebaseStorage.getInstance().getReference(filepathAndName);
        storageReference.putFile(img)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        //get notes url
                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful());
                        String uploadeNotesUrl=""+uriTask.getResult();

                        //upload to firebase db
                        uploadJobToStorage(uploadeNotesUrl,timestamp);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AdminDashboardActivity.this, "LOGO upload failed due to"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void uploadJobToStorage(String uploadeLogoUrl, long timestamp) {

        //step 3 : upload to firebasedb
        progressDialog.setMessage("Adding new Job........");

        String uid= auth.getUid();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("uid",""+uid);
        hashMap.put("jobTitle",""+jobTitle);
        hashMap.put("company",""+company);
        hashMap.put("jobtype",""+jobtype);
        hashMap.put("location",""+location);
        hashMap.put("id",""+timestamp);
        hashMap.put("jobDes",""+jobDes);
        hashMap.put("date",""+date);
        hashMap.put("url",""+uploadeLogoUrl);


        //Upload ti fb db

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Jobs");
        reference.child(""+timestamp)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Job Add successfully
                        progressDialog.dismiss();
                        Toast.makeText(AdminDashboardActivity.this, "Job Added Successfully....", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(AdminDashboardActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });




    }
}