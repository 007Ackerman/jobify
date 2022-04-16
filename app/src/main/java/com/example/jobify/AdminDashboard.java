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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminDashboard extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener
{
    private FirebaseAuth firebaseAuth;
    private ActivityAdminDashboard2Binding binding;



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

}