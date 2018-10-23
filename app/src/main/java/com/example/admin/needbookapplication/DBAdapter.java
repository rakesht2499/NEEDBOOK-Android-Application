package com.example.admin.needbookapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.ViewHolder> {

    private String subjects;
    private List<String> arr = new ArrayList<String>();

    ItemClicked activity;
    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public DBAdapter(Context context, String subjects){
        activity = (ItemClicked) context;
        this.subjects = subjects;
        String s[] = this.subjects.split("-");
        arr = Arrays.asList(s);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSubject;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tvSybjects);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //activity.onItemClicked(subjects.indexOf((Integer) v.getTag()));
                }
            });

        }
    }
    @NonNull
    @Override
    public DBAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listlayoutforsubtopics, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DBAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(arr.get(i));
        viewHolder.tvSubject.setText(arr.get(i));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
