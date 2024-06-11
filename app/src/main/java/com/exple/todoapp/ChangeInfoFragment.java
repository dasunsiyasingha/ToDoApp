package com.exple.todoapp;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeInfoFragment extends DialogFragment {
    DatabaseHelper databaseHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for your dialog
        View view = inflater.inflate(R.layout.fragment_change_info, container, false);



        databaseHelper = new DatabaseHelper(getContext());
        int id = this.getArguments().getInt("userid");

        TextView chngeinfo = view.findViewById(R.id.changeinfo);

        EditText chngeNickname = view.findViewById(R.id.chngnicknme);
        EditText chngeBd = view.findViewById(R.id.chngbd);
        EditText chngeEmail = view.findViewById(R.id.chngemail);

        Button okbtn = view.findViewById(R.id.okbtn);
        Button cancelbtn = view.findViewById(R.id.cancelbtn);



        chngeinfo.setText(String.valueOf(id));

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = chngeNickname.getText().toString();
                String bd = chngeBd.getText().toString();
                String email = chngeEmail.getText().toString();

                String msg = databaseHelper.updateuserinfo(String.valueOf(id), nickname, bd, email);
                Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



        return view;
    }
}