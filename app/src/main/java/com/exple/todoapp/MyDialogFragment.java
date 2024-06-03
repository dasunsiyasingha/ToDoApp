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

        CheckBox status = view.findViewById(R.id.edtStatus);

        taskid.setText(String.valueOf(databaseHelper.lasttaskid()));
//        taskid.setText(String.valueOf(userid));


        dialogAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Work work1 = new Work();
//                work1.title = title.getText().toString();
//                work1.description = description.getText().toString();
//                work1.location =location.getText().toString();
//                work1.status = status.getText().toString();
//                workList.addwork(work1);

//DATABASE ADDING PART START
                int in_taskid = Integer.parseInt(taskid.getText().toString())+1;
                String in_title = title.getText().toString();
                String in_desc = description.getText().toString();
                String in_location = location.getText().toString();
                String in_status = status.getText().toString();

                boolean result = databaseHelper.taskAdd(in_taskid, in_title, in_desc, in_location, in_status, userid);
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

//                View inputwrapper = view.findViewById(R.id.inputslayout);
                dismiss();
//                inputwrapper.setVisibility(View.INVISIBLE);

//                RecyclerView recyclerView = viewList.findViewById(R.id.workrv);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(viewList.getContext());
//                workAdapter = new WorkAdapter(workList);
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setAdapter(workAdapter);




//                if(workList != null && workList.workarraylist != null){
//                    RecyclerView recyclerView = view.findViewById(R.id.workrv);
//                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
////                    worklist = workList.workarraylist;
//                    WorkAdapter workAdapter =new WorkAdapter(databaseHelper.gettasks(userid));
//                    recyclerView.setLayoutManager(layoutManager);
//                    recyclerView.setAdapter(workAdapter);
//                }

            }

        });



        return view;
    }
}