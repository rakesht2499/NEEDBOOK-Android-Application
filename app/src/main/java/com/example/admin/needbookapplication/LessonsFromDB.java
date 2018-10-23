package com.example.admin.needbookapplication;

import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class LessonsFromDB extends AppCompatActivity implements SubjectsAdapter.ItemClicked {

    TextView tvshowdata;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_from_db);

        tvshowdata = findViewById(R.id.tvShowData);
        Intent intent = getIntent();
        int index = intent.getIntExtra("name",0);
        String subtopic = intent.getStringExtra("subtopic");
        int subIndex = intent.getIntExtra("subject_name",0);
        ConnectToDB db = new ConnectToDB(this);
        try{
            db.open();
            tvshowdata.setText(db.getData(index,subtopic,subIndex));
            db.close();
        }
        catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
//        view = this.view;
//        recyclerView = view.findViewById(R.id.thisrecycler);
//        recyclerView.setHasFixedSize(true);
////        layoutManager = new LinearLayoutManager(this.getActivity());
//        layoutManager =  new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        ConnectToDB c = new ConnectToDB(this);
//        ArrayList<Subjects> some = c.returnString();
//        myAdapter = new SubjectsAdapter(this,some);
//        recyclerView.setAdapter(myAdapter);
    }
    @Override
    public void onItemClicked(int index) {

    }
}
