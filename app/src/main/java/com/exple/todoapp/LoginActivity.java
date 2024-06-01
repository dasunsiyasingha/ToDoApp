package com.exple.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.exple.todoapp.databinding.ActivityLoginBinding;
import com.exple.todoapp.databinding.ActivityRegisterBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);



        binding.btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.signinusrnme.getText().toString();
                String password = binding.signinpswd.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this,"Please all Feilds are fill",Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkusrpwsd = databaseHelper.checusernamepswd(username, password);

                    if(checkusrpwsd == true){
                        Toast.makeText(LoginActivity.this,"Login successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"You have not registered!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.btnmoveregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}