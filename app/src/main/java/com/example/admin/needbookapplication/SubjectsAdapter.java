package com.example.admin.needbookapplication;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> {

    private ArrayList<Subjects> subjects;

    ItemClicked activity;
    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public SubjectsAdapter(Context context, ArrayList<Subjects> subjects){
        activity = (ItemClicked) context;
        this.subjects = subjects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSubject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tvSybjects);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(subjects.indexOf((Subjects) v.getTag()));
                }
            });

        }
    }

    @NonNull
    @Override
    public SubjectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if(subjects.get(0).getSubName() == "Biology") {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listviewlayout, viewGroup, false);
        }else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listlayoutforsubtopics, viewGroup, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(subjects.get(i));
        viewHolder.tvSubject.setText(subjects.get(i).getSubName());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}
