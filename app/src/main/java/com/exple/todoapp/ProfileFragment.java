package com.exple.todoapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.exple.todoapp.databinding.ActivityLoginBinding;
import com.exple.todoapp.databinding.FragmentProfileBinding;

import java.util.HashMap;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    DatabaseHelper databaseHelper;
    Button signoutbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        databaseHelper = new DatabaseHelper(getContext());

        int userid = this.getArguments().getInt("userid");

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView hinicknme = view.findViewById(R.id.texthinme);
        TextView name = view.findViewById(R.id.usernameview);
        TextView nickname = view.findViewById(R.id.usernicknameview);
        TextView birthday = view.findViewById(R.id.userbdview);
        TextView email = view.findViewById(R.id.useremailview);

        HashMap<String,String> userinfo = databaseHelper.getuserinfo(userid);

        hinicknme.setText("Hi, "+userinfo.get("nickname"));
        name.setText(userinfo.get("username"));
        nickname.setText(userinfo.get("nickname"));
        birthday.setText(userinfo.get("birthday"));
        email.setText(userinfo.get("email"));

        signoutbtn = view.findViewById(R.id.signoutbtn);
        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
}