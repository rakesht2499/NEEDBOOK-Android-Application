package com.example.admin.needbookapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListSubjects extends Fragment {

    TextView tvResultingSub;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    View view;

    public ListSubjects() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_subjects, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvResultingSub = view.findViewById(R.id.tvSubject);
        tvResultingSub.setVisibility(View.GONE);
        recyclerView = view.findViewById(R.id.listSub);
        recyclerView.setHasFixedSize(true);

//        layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager =  new GridLayoutManager(this.getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        String temp = tvResultingSub.getText().toString().trim();

        if(temp == "Biology"){
            myAdapter = new SubjectsAdapter(this.getActivity(),ApplicationClass.subBio);
            recyclerView.setAdapter(myAdapter);
        }else if (temp == "Physics"){
            myAdapter = new SubjectsAdapter(this.getActivity(),ApplicationClass.subPhy);
            recyclerView.setAdapter(myAdapter);
        }else if (temp == "Chemistry"){
            myAdapter = new SubjectsAdapter(this.getActivity(),ApplicationClass.subChem);
            recyclerView.setAdapter(myAdapter);
        }

    }

}
