package com.example.admin.needbookapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SubjectChapters extends AppCompatActivity implements SubjectsAdapter.ItemClicked {

    TextView tvSubject;
    int indexglob;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(SubjectChapters.this,com.example.admin.needbookapplication.MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(SubjectChapters.this,com.example.admin.needbookapplication.ChangePassword.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(SubjectChapters.this,com.example.admin.needbookapplication.ChangePassword.class));
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_chapters);
        tvSubject = findViewById(R.id.tvSubject);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        indexglob = intent.getIntExtra("name",0);
        tvSubject.setText(ApplicationClass.subjects.get(indexglob).getSubName());
        tvSubject.setVisibility(View.GONE);
    }

    @Override
    public void onItemClicked(int index) {
            Intent intent = new Intent(SubjectChapters.this,LessonsFromDB.class);
            intent.putExtra("name",index);
            intent.putExtra("subject_name",indexglob);
            intent.putExtra("subtopic",ApplicationClass.subjects.get(indexglob).getSubName());
            startActivity(intent);
    }
}
