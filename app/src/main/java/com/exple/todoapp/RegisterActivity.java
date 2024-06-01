package com.exple.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.exple.todoapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    DatabaseHelper databaseHelper;

    Button btnMoveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_register);
        databaseHelper = new DatabaseHelper(this);

        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.signupusrnme.getText().toString();
                String nickname = binding.signupnicknme.getText().toString();
                String birthday = binding.signupbd.getText().toString();
                String password = binding.signuppswd.getText().toString();
                String confirmpassword = binding.signuprepswd.getText().toString();
                String email = binding.signupemail.getText().toString();

                if(username.equals("") || nickname.equals("") || birthday.equals("") || password.equals("") || confirmpassword.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please all Feilds are fill",Toast.LENGTH_LONG).show();
                }else{
                    if(password.equals(confirmpassword)){
                        Boolean checkUsername = databaseHelper.checkusername(username);

                        if(checkUsername == false){
                            Boolean insert = databaseHelper.insertDatauser(username, nickname, birthday, password, email);

                            if(insert){
                                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Register Unsuccessfull, please Try again", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this,"You have already registered",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,"Invalid password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.btnmovelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });


    }


}