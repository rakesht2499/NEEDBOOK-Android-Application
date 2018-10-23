package com.example.admin.needbookapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(ChangePassword.this,com.example.admin.needbookapplication.MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(ChangePassword.this, "You are currently in this page", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(ChangePassword.this,com.example.admin.needbookapplication.ChangePassword.class));
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(ChangePassword.this, "You are currently in this page", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(ChangePassword.this,com.example.admin.needbookapplication.ChangePassword.class));
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
