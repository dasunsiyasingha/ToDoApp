package com.exple.todoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.MyViewHolder>{

    private List<Work> workList;
    private Context context;
    private int userid;
    String updateStatus ="";
    DatabaseHelper databaseHelper;

    public WorkAdapter(Context context, List<Work> workList, int userid) {
        this.workList = workList;
        this.context = context;
        this.userid = userid;
    }

    @NonNull
    @Override
    public WorkAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.work_card,parent, false);
        return new WorkAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.MyViewHolder holder, int position) {
        databaseHelper = new DatabaseHelper(context);

        Work work = workList.get(position);
        holder.taskid.setText(work.taskid);
        holder.title.setText(work.title);
        holder.description.setText(work.description);
        holder.location.setText(work.location);

        if(work.status.equals("Completed")){
            holder.status.setBackgroundColor(Color.GREEN);
            holder.status.setText(work.status);

        }else{
            holder.status.setText(work.status);
            holder.status.setBackgroundColor(Color.RED);
        }

        holder.taskcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("taskid", work.taskid);
                bundle.putString("title", work.title);
                bundle.putString("description", work.description);
                bundle.putString("location", work.location);
                bundle.putString("status", work.status);
                bundle.putInt("userid", userid);

                Intent intent = new Intent(context, TaskDetailsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (updateStatus.equals("")){
                    if(work.status.equals("Completed")){
                        updateStatus = databaseHelper.updateStatus("Pending", String.valueOf(userid), work.taskid);
                        holder.status.setText(updateStatus);
                        holder.status.setBackgroundColor(Color.RED);

                    }else{
                        updateStatus = databaseHelper.updateStatus("Completed", String.valueOf(userid), work.taskid);
                        holder.status.setText(updateStatus);
                        holder.status.setBackgroundColor(Color.GREEN);

                    }
                }else{
                    if(holder.status.getText().equals("Completed")){
                        updateStatus = databaseHelper.updateStatus("Pending", String.valueOf(userid), work.taskid);
                        holder.status.setText(updateStatus);
                        holder.status.setBackgroundColor(Color.RED);

                    }else{
                        updateStatus = databaseHelper.updateStatus("Completed", String.valueOf(userid), work.taskid);
                        holder.status.setText(updateStatus);
                        holder.status.setBackgroundColor(Color.GREEN);

                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return workList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView taskid, title, description, location, status;
        CardView taskcard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            taskid = itemView.findViewById(R.id.viewtaskid);
            title = itemView.findViewById(R.id.viewtitle);
            description = itemView.findViewById(R.id.viewdesc);
            location = itemView.findViewById(R.id.viewlocation);
            status = itemView.findViewById(R.id.viewstatusbtn);
            taskcard = itemView.findViewById(R.id.taskcard);

        }
    }
}
