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

public class EditNameFragment extends DialogFragment {
    DatabaseHelper databaseHelper;
    Button okbtn, cancelbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_name, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        String userid = String.valueOf(this.getArguments().getInt("userid"));

        okbtn = view.findViewById(R.id.okbtn);
        cancelbtn = view.findViewById(R.id.cancelbtn);

        EditText oldpswdInput = view.findViewById(R.id.oldpswd);
        EditText newusrnameInput = view.findViewById(R.id.newusernme);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpswd = oldpswdInput.getText().toString();
                String newusername = newusrnameInput.getText().toString();

                if (!(oldpswd.equals("") || oldpswd.equals(" "))) {
                    if (databaseHelper.checkpassword(userid, oldpswd)) {
                        if (!(newusername.equals("")||newusername.equals(" "))){
                            if(databaseHelper.editusername(userid, newusername)){
                                Toast.makeText(getActivity(),"User name changed.",Toast.LENGTH_SHORT).show();
                                dismiss();
                            }else{
                                Toast.makeText(getActivity(),"Can't change user name.",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(),"User name is Empty.",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(),"Password is incorrect.",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"Enter current password.",Toast.LENGTH_SHORT).show();
                }
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