package com.example.mobilecontent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilecontent.databinding.ActivityAddAlreamBinding;

public class addAlream extends AppCompatActivity {
    private ActivityAddAlreamBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddAlreamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        binding.timepicker.getHour();
//        binding.timepicker.getMinute();

        Log.d("bindingtest", binding.cancel.getText().toString());
        binding.cancel.setOnClickListener(v -> startActivity(new Intent(addAlream.this,MainActivity.class)));
        binding.save.setOnClickListener(v -> startActivity(new Intent(addAlream.this,MainActivity.class)));
    }
}