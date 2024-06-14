package com.exple.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetailsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_task_details);

//        TextView txttaskid = findViewById(R.id.detailtaskid);
        TextView txttitle = findViewById(R.id.detailtasktitle);
        TextView txtdesc = findViewById(R.id.detailtaskdesc);
        TextView txtlocation = findViewById(R.id.detailtasklocation);
        TextView txtstatus = findViewById(R.id.statusbtn);
        TextView backbtn = findViewById(R.id.backbtn);
        Button deletebtn = findViewById(R.id.deletebtn);
        Button statusbtn = findViewById(R.id.statusbtn);

//        Bundle bundle = getArguments();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String taskid = extras.getString("taskid");
        String title = extras.getString("title");
        String desc = extras.getString("description");
        String location = extras.getString("location");
        String status = extras.getString("status");
        int userid =  extras.getInt("userid");


        if (extras == null){
//            txttaskid.setText("taskid");
            txttitle.setText("title");
            txtdesc.setText("description");
            txtlocation.setText("location");
            txtstatus.setText("status");
        }else{
//            txttaskid.setText(taskid);
            txttitle.setText(title);
            txtdesc.setText(desc);
            txtlocation.setText(location);
            txtstatus.setText(status);
        }

        if(status.equals("Completed")){
            statusbtn.setBackgroundColor(Color.GREEN);
            statusbtn.setText(status);

        }else{
            statusbtn.setText(status);
            statusbtn.setBackgroundColor(Color.RED);
        }

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String ex = String.valueOf(databaseHelper.taskCompletePrecentage());
//                txtlocation.setText(ex);

                String msg = databaseHelper.deleteTask(taskid);

                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                Intent intentlogin = new Intent(getApplicationContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("usrid", userid);
                intentlogin.putExtras(bundle);
                startActivity(intentlogin);

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlogin = new Intent(getApplicationContext(), MainActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("usrid", userid);
                intentlogin.putExtras(bundle1);
                startActivity(intentlogin);
            }
        });
    }
}