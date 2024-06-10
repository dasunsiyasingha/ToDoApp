package com.exple.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyDialogFragment extends DialogFragment  {
    Button dialogCancelBtn, dialogAddBtn;
    private List<Work> worklist = new ArrayList<>();
    DatabaseHelper databaseHelper;
    WorkAdapter workAdapter;
    public WorkList workList = new WorkList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for your dialog
        View view = inflater.inflate(R.layout.fragment_dialog_layout, container, false);

        databaseHelper = new DatabaseHelper(getContext());

        int userid = this.getArguments().getInt("userid");

        // Find views and configure the dialog here (optional)
        dialogAddBtn = view.findViewById(R.id.diaddbtn);
        dialogCancelBtn = view.findViewById(R.id.dialogcancelbtn);

        EditText title = view.findViewById(R.id.edtTitle);
        EditText description = view.findViewById(R.id.edtDesc);
        EditText location = view.findViewById(R.id.edtLocation);
        TextView taskid = view.findViewById(R.id.taskid);


        CheckBox checkbox = view.findViewById(R.id.edtStatus);
        int nextTaskid = Integer.parseInt(databaseHelper.lasttaskid())+1;


        taskid.setText(String.valueOf(nextTaskid));


        dialogAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//DATABASE ADDING PART START
                int in_taskid = nextTaskid;
                String in_title = title.getText().toString();
                String in_desc = description.getText().toString();
                String in_location = location.getText().toString();

                String status;
                if (checkbox.isChecked()) {
                    status = "Completed";
                } else {
                    status = "Pending";
                }

                boolean result = databaseHelper.taskAdd(in_taskid, in_title, in_desc, in_location, status, userid);
                if(result == true){
                    Toast.makeText(getActivity(),"Task added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"Task not add, Try again..", Toast.LENGTH_SHORT).show();
                }
//DATABASE ADDING PART CLOSE

            }
        });

        dialogCancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }

        });
        return view;
    }
}