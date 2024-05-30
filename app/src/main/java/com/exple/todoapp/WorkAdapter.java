package com.exple.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.MyViewHolder>{

    private List<Work> workList;

    public WorkAdapter(List<Work> workList) {
        this.workList = workList;
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
        Work work = workList.get(position);
        holder.title.setText(work.title);
        holder.description.setText(work.description);
        holder.time.setText(work.time);
        holder.location.setText(work.location);
        holder.status.setText(work.status);

    }

    @Override
    public int getItemCount() {
        return workList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, time, location, status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.viewtitle);
            description = itemView.findViewById(R.id.viewdesc);
            time = itemView.findViewById(R.id.viewtime);
            location = itemView.findViewById(R.id.viewlocation);
            status = itemView.findViewById(R.id.viewstatus);

        }
    }
}
