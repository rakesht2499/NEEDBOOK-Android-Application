package com.example.admin.needbookapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SubjectsAdapter.ItemClicked {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(MainActivity.this, "You are currently in this page", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                        startActivity(new Intent(MainActivity.this,com.example.admin.needbookapplication.ChangePassword.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(MainActivity.this,com.example.admin.needbookapplication.ChangePassword.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(MainActivity.this,com.example.admin.needbookapplication.Login.LoginPage.class));
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onItemClicked(int index) {
        Intent intent = new Intent(MainActivity.this,com.example.admin.needbookapplication.SubjectChapters.class);
        intent.putExtra("name",index);
        startActivity(intent);

    }
}
