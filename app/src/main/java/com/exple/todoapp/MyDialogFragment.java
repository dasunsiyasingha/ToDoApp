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

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyDialogFragment extends DialogFragment  {
    Button dialogCancelBtn, dialogAddBtn;
    private List<Work> worklist = new ArrayList<>();
    WorkAdapter workAdapter;


    public WorkList workList = new WorkList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for your dialog
        View view = inflater.inflate(R.layout.fragment_dialog_layout, container, false);
        View viewList = inflater.inflate(R.layout.fragment_list, container, false);

        // Find views and configure the dialog here (optional)
        dialogAddBtn = view.findViewById(R.id.diaddbtn);
        dialogCancelBtn = view.findViewById(R.id.dialogcancelbtn);

        EditText title = view.findViewById(R.id.edtTitle);
        EditText description = view.findViewById(R.id.edtDesc);
        EditText location = view.findViewById(R.id.edtLocation);
        EditText time = view.findViewById(R.id.edtTime);
        CheckBox status = view.findViewById(R.id.edtStatus);



        dialogAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Work work1 = new Work();

                work1.title = title.getText().toString();
                work1.description = description.getText().toString();
                work1.location =location.getText().toString();
                work1.time = time.getText().toString();
                work1.status = status.getText().toString();

//                workList.add(work1);
                workList.addwork(work1);


            }
        });

        dialogCancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                View inputwrapper = view.findViewById(R.id.inputslayout);
                inputwrapper.setVisibility(View.INVISIBLE);

//                RecyclerView recyclerView = viewList.findViewById(R.id.workrv);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(viewList.getContext());
//                workAdapter = new WorkAdapter(workList);
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setAdapter(workAdapter);




//                if(workList != null && workList.workarraylist != null){
//                    RecyclerView recyclerView = view.findViewById(R.id.workrv);
//                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//                    worklist = workList.workarraylist;
//                    WorkAdapter workAdapter =new WorkAdapter(worklist);
//                    recyclerView.setLayoutManager(layoutManager);
//                    recyclerView.setAdapter(workAdapter);
//                }

            }

        });



        return view;
    }
}