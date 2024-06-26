package com.exple.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ListFragment listFragment = new ListFragment();
    DevinfoFragment devinfoFragment = new DevinfoFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentlogin = getIntent();
        Bundle extras = intentlogin.getExtras();
        int id = extras.getInt("usrid");

        //link with bottom navigation bar on layout(XML) file
        bottomNavigationView = findViewById(R.id.bottomNav);
        ImageView chngeinfobtn = findViewById(R.id.changeinfobtn);



        FragmentManager fragmentManagerlist = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionlist = fragmentManagerlist.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("userid",id);

        listFragment.setArguments(bundle);
        fragmentTransactionlist.replace(R.id.container,listFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Bundle bundle = new Bundle();
                bundle.putInt("userid", id);

                switch (item.getItemId()){
                    case R.id.list:
                        FragmentManager fragmentManagerlist = getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionlist = fragmentManagerlist.beginTransaction();

                        listFragment.setArguments(bundle);
                        fragmentTransactionlist.replace(R.id.container,listFragment).commit();
                        chngeinfobtn.setVisibility(View.INVISIBLE);

                        return true;

                    case R.id.reminder:
                        FragmentManager fragmentManagerreminder = getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionreminder = fragmentManagerreminder.beginTransaction();

                        devinfoFragment.setArguments(bundle);
                        fragmentTransactionreminder.replace(R.id.container, devinfoFragment).commit();
                        chngeinfobtn.setVisibility(View.INVISIBLE);

                        return true;

                    case R.id.profile:
                        FragmentManager fragmentManagerprofile = getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionprofile = fragmentManagerprofile.beginTransaction();

                        profileFragment.setArguments(bundle);
                        fragmentTransactionprofile.replace(R.id.container,profileFragment).commit();
                        chngeinfobtn.setVisibility(View.VISIBLE);
                        chngeinfobtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundleww = new Bundle();
                                bundleww.putInt("userid", id);
                                ChangeInfoFragment changeInfoFragment = new ChangeInfoFragment();
                                changeInfoFragment.setArguments(bundleww);
                                changeInfoFragment.show(profileFragment.getChildFragmentManager(), "Changeinfo"); // Use getChildFragmentManager for fragments

                            }
                        });
                        return true;
                }
                return false;
            }
        });

    }

}