package com.exple.todoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewreminder = inflater.inflate(R.layout.fragment_info, container, false);
        int userid = this.getArguments().getInt("userid");
//        TextView user = viewreminder.findViewById(R.id.user);
//        user.setText(userid+"");
        return viewreminder;
    }
}