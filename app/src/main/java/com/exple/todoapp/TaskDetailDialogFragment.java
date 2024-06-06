package com.exple.todoapp;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskDetailDialogFragment extends DialogFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_detail_dialog, container, false);

//        TextView taskid = view.findViewById(R.id.detailtaskid);
//        TextView title = view.findViewById(R.id.detailtasktitle);
//        TextView desc = view.findViewById(R.id.detailtaskdesc);
//        TextView location = view.findViewById(R.id.detailtasklocation);
//        TextView status = view.findViewById(R.id.detailtaskstatus);
//
////        Bundle bundle = getArguments();
//        Bundle bundle = this.getArguments();
//        if (bundle != null){
//            taskid.setText("taskid");
//            title.setText("title");
//            desc.setText("description");
//            location.setText("location");
//            status.setText("status");
//        }else{
//            taskid.setText(bundle.getString("taskid"));
//            title.setText(bundle.getString("title"));
//            desc.setText(bundle.getString("description"));
//            location.setText(bundle.getString("location"));
//            status.setText(bundle.getString("status"));
//        }
        return view;
    }
}