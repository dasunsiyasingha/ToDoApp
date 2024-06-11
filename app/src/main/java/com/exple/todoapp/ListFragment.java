package com.exple.todoapp;

import android.content.Intent;
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
    DatabaseHelper databaseHelper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getContext());

        // Inflate the layout for this fragment
        View viewlist = inflater.inflate(R.layout.fragment_list, container, false);
        int userid = this.getArguments().getInt("userid");

//        TextView usrid = viewlist.findViewById(R.id.userid);
//        usrid.setText(userid+"");

//VIEW LIST USING DATABASE DATA
        RecyclerView recyclerView = viewlist.findViewById(R.id.workrv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        WorkAdapter workAdapter =new WorkAdapter(getContext(), databaseHelper.gettasks(userid), userid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(workAdapter);
//END   VIEW LIST USING DATABASE DATA


        showDialogBtn = viewlist.findViewById(R.id.addbtn);



        // Set click listener for the button to show the dialog
        showDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundleww = new Bundle();
                bundleww.putInt("userid", userid);
                MyDialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.setArguments(bundleww);
                dialogFragment.show(getChildFragmentManager(), "myDialog"); // Use getChildFragmentManager for fragments



            }
        });



        return viewlist;

    }
}
