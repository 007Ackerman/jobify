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

public class AdminDashboard extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener
{

    private ActivityAdminDashboard2Binding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAdminDashboard2Binding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_admin_dashboard2);
        getSupportActionBar().hide();

      /* binding.notif.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               PopupMenu popup=new PopupMenu(AdminDashboard.this,view);
               popup.setOnMenuItemClickListener(AdminDashboard.this);
               popup.inflate(R.menu.sidemenu);
               popup.show();
           }
       });*/
    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch(menuItem.getItemId())
        {
            case R.id.post:
                startActivity(new Intent(AdminDashboard.this,DashBoardActivity.class));
                return true;
            case R.id.logout:
                return false;
            default :
                return false;
        }


    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.sidemenu,menu);

        return super.onCreateOptionsMenu(menu);
    }*/
}