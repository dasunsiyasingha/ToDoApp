package com.exple.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ListFragment listFragment = new ListFragment();
    InfoFragment infoFragment = new InfoFragment();
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
        TextView settingbtn = findViewById(R.id.settingbtn);

        //when Open app ByDefualt open list fragment
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,listFragment).commit();
        FragmentManager fragmentManagerlist = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionlist = fragmentManagerlist.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("userid",id);

        listFragment.setArguments(bundle);
        fragmentTransactionlist.replace(R.id.container,listFragment).commit();

//        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.reminder);
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setNumber(4);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Bundle bundle = new Bundle();
                bundle.putInt("userid", id);

                switch (item.getItemId()){
                    case R.id.list:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();
                        FragmentManager fragmentManagerlist = getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionlist = fragmentManagerlist.beginTransaction();

//                        Bundle bundle = new Bundle();
//                        bundle.putInt("userid",id);

                        listFragment.setArguments(bundle);
                        fragmentTransactionlist.replace(R.id.container,listFragment).commit();
                        settingbtn.setVisibility(View.INVISIBLE);

                        return true;

                    case R.id.info:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, reminderFragment).commit();
                        FragmentManager fragmentManagerreminder = getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionreminder = fragmentManagerreminder.beginTransaction();

//                        Bundle bundle = new Bundle();
//                        bundle.putInt("userid", id);

                        infoFragment.setArguments(bundle);
                        fragmentTransactionreminder.replace(R.id.container, infoFragment).commit();

//                        badgeDrawable.setVisible(false);
//                        badgeDrawable.clearNumber();
                        settingbtn.setVisibility(View.INVISIBLE);
                        return true;

                    case R.id.profile:
                        FragmentManager fragmentManagerprofile = getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionprofile = fragmentManagerprofile.beginTransaction();

//                        Bundle bundle = new Bundle();
//                        bundle.putInt("userid",id);

                        profileFragment.setArguments(bundle);
                        fragmentTransactionprofile.replace(R.id.container,profileFragment).commit();

                        settingbtn.setVisibility(View.VISIBLE);
                        return true;
                }

                return false;
            }
        });




    }
}