package com.exple.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ListFragment listFragment = new ListFragment();
    ReminderFragment reminderFragment = new ReminderFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentlogin = getIntent();
        Bundle extras = intentlogin.getExtras();
        String name = extras.getString("usrname");

        //link with bottom navigation bar on layout(XML) file
        bottomNavigationView = findViewById(R.id.bottomNav);

        //when Open app ByDefualt open list fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container,listFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.reminder);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(4);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {




            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.list:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();
                        return true;
                    case R.id.reminder:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, reminderFragment).commit();
                        badgeDrawable.setVisible(false);
                        badgeDrawable.clearNumber();
                        return true;
                    case R.id.profile:
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        Bundle bundle = new Bundle();
                        bundle.putString("username",name.toString());

                        profileFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.container,profileFragment).commit();
                        return true;
                }

                return false;
            }
        });

    }
}