package com.example.mobilecontent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobilecontent.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm = getSupportFragmentManager();
    private ActivityMainBinding binding;
    private main searchFragment = new main();
    private alream AlreamFragment =new alream();
    private question questionFragment= new question();
    private user quserFramgent=new user();
    private write writeFragment = new write();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(false);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, searchFragment).commitAllowingStateLoss();

        binding.title.setText("home");
        binding.topbarButton.setVisibility(View.INVISIBLE);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Toolbar toolbar = findViewById(R.id.toolbar);
//                toolbar.setTitle("test");
//                setSupportActionBar();
//                ActionBar actionBar = getSupportActionBar();
                binding.topbarButton.setVisibility(View.INVISIBLE);
                FragmentTransaction transaction = fm.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home: {
                        binding.title.setText("home");
                        transaction.replace(R.id.nav_host_fragment,searchFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_alarm: {
                        binding.title.setText("alram");
                        binding.topbarButton.setVisibility(View.VISIBLE);
                        binding.topbarButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this,addAlream.class));
                            }
                        });
                        transaction.replace(R.id.nav_host_fragment,AlreamFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_question: {
                        binding.topbarButton.setVisibility(View.VISIBLE);
                        binding.topbarButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this,sarchpiture.class));
                            }
                        });
                        binding.title.setText("question");
                        transaction.replace(R.id.nav_host_fragment, questionFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_user: {
                        binding.title.setText("user");
                        transaction.replace(R.id.nav_host_fragment, quserFramgent).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_write: {
                        binding.topbarButton.setVisibility(View.VISIBLE);
                        binding.title.setText("write");
                        binding.topbarButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this,writting.class));
                            }
                        });
                        transaction.replace(R.id.nav_host_fragment, writeFragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });
    }
    @Override public void onBackPressed() {
        //super.onBackPressed();
    }
}