package com.exple.todoapp;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class PswdChangeFragment extends DialogFragment {

    DatabaseHelper databaseHelper;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pswd_change, container, false);

        TextView oldpswdInput = view.findViewById(R.id.oldpswd);
        TextView newpswdInput = view.findViewById(R.id.newpswd);
        TextView renewpswdInput = view.findViewById(R.id.renewpswd);

        Button okbtn = view.findViewById(R.id.okbtn);
        Button cancelbtn = view.findViewById(R.id.cancelbtn);


        TextView text = view.findViewById(R.id.text6);
        databaseHelper = new DatabaseHelper(getContext());

        int userid = this.getArguments().getInt("userid");
        text.setText(String.valueOf(userid));

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpswd = oldpswdInput.getText().toString();
                String newpswd = newpswdInput.getText().toString();
                String renewpswd = renewpswdInput.getText().toString();

                if(oldpswd.equals("") || oldpswd.equals(" ") ){
                    Toast.makeText(getActivity(),"please Enter Old Password", Toast.LENGTH_SHORT).show();
                }else if(databaseHelper.checkpassword(String.valueOf(userid),oldpswd)){
                    if(!(newpswd.equals("")|| newpswd.equals(" "))){
                        if (newpswd.equals(renewpswd)){
                            if(databaseHelper.updatepassword(String.valueOf(userid), newpswd)){
                                Toast.makeText(getActivity(),"Password Updated", Toast.LENGTH_SHORT).show();
                                dismiss();
                            }else{
                                Toast.makeText(getActivity(),"Can't Update Password.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(),"Passwords are Different", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(),"Password is Not entered", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"please Enter Correct Password", Toast.LENGTH_SHORT).show();
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