package com.exple.todoapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exple.todoapp.databinding.ActivityLoginBinding;
import com.exple.todoapp.databinding.FragmentProfileBinding;

import java.util.HashMap;

import kotlin.internal.UProgressionUtilKt;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    DatabaseHelper databaseHelper;
    Button signoutbtn, pswdchangebtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        databaseHelper = new DatabaseHelper(getContext());

        int userid = this.getArguments().getInt("userid");
        float precentge = databaseHelper.taskCompletePrecentage(String.valueOf(userid));
        int progressint = (int)precentge;

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView hinicknme = view.findViewById(R.id.texthinme);
        TextView name = view.findViewById(R.id.usernameview);
        TextView nickname = view.findViewById(R.id.usernicknameview);
        TextView birthday = view.findViewById(R.id.userbdview);
        TextView email = view.findViewById(R.id.useremailview);
        TextView txtprogrss = view.findViewById(R.id.txtprogress);
        ProgressBar taskprogress = view.findViewById(R.id.taskprogress);

        HashMap<String,String> userinfo = databaseHelper.getuserinfo(userid);

        hinicknme.setText("Hi, "+userinfo.get("nickname"));
        name.setText(userinfo.get("username"));
        nickname.setText(userinfo.get("nickname"));
        birthday.setText(userinfo.get("birthday"));
        email.setText(userinfo.get("email"));


        txtprogrss.setText(String.valueOf(progressint)+"%");
        taskprogress.setProgress(progressint);

        signoutbtn = view.findViewById(R.id.signoutbtn);
        pswdchangebtn = view.findViewById(R.id.chngpswdtbtn);

        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        pswdchangebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putInt("userid", userid);
                PswdChangeFragment pswdChangeFragment = new PswdChangeFragment();
                pswdChangeFragment.setArguments(bundle1);
                pswdChangeFragment.show(getChildFragmentManager(), "PswdChange"); // Use getChildFragmentManager for fragments

            }
        });
        return view;

    }
}