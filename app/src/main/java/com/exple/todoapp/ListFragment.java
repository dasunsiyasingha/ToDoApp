package com.exple.todoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    Button showDialogBtn, dialogCancelBtn, dialogAddBtn;


    private List<Work> worklist = new ArrayList<>();

    WorkList workList = new WorkList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        if(workList != null && workList.workarraylist != null){
            RecyclerView recyclerView = view.findViewById(R.id.workrv);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            worklist = workList.workarraylist;
            WorkAdapter workAdapter =new WorkAdapter(worklist);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(workAdapter);
        }

        showDialogBtn = view.findViewById(R.id.addbtn);



        // Set click listener for the button to show the dialog
        showDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.show(getChildFragmentManager(), "myDialog"); // Use getChildFragmentManager for fragments



            }
        });



        return view;

    }
}
