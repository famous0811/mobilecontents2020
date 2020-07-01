package com.example.mobilecontent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilecontent.databinding.ActivityWrittingBinding;

public class writting extends AppCompatActivity {
    private ActivityWrittingBinding binding;
    private SharedPreferences sf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWrittingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        sf = getSharedPreferences("userdata", MODE_PRIVATE);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        String title = sf.getString("titlesave", "");
        String content = sf.getString("contentsave", "");

        //alert 창 설정 위치
        binding.titletext.setText(title);
        binding.content.setText(content);

        binding.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.titletext.getText().toString();
                String content = binding.content.getText().toString();

                String email = sf.getString("email", "");

                Intent intent = new Intent(writting.this, writtensetting.class);
                intent.putExtra("email", email);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.titletext.getText().toString();
                String content = binding.content.getText().toString();
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("titlesave", title);
                editor.putString("contentsave", content);
                editor.apply();
            }
        });
    }
}