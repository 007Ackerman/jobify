package com.example.jobify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.jobify.databinding.ActivityAdminDashboard2Binding;
import com.example.jobify.databinding.ActivitySigninBinding;

public class AdminDashboard extends AppCompatActivity
{

    private ActivityAdminDashboard2Binding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminDashboard2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashboard.this,AdminDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

}