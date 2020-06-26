package com.example.mobilecontent;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm = getSupportFragmentManager();

    private main searchFragment = new main();
    private alream AlreamFragment =new alream();
    private question questionFragment= new question();
    private user quserFramgent=new user();
    private write writeFragment = new write();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, searchFragment).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Toolbar toolbar = findViewById(R.id.toolbar);
//                toolbar.setTitle("test");
//                setSupportActionBar();
//                ActionBar actionBar = getSupportActionBar();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home: {
//                        actionBar.setTitle("home");
                        transaction.replace(R.id.nav_host_fragment,searchFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_alarm: {
//                        actionBar.setTitle("alarm");
                        transaction.replace(R.id.nav_host_fragment,AlreamFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_question: {
//                        actionBar.setTitle("question");
                        transaction.replace(R.id.nav_host_fragment, questionFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_user: {
//                        actionBar.setTitle("user");
                        transaction.replace(R.id.nav_host_fragment, quserFramgent).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_write: {
//                        actionBar.setTitle("write");
                        transaction.replace(R.id.nav_host_fragment, writeFragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });
    }
}