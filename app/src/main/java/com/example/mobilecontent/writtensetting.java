package com.example.mobilecontent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilecontent.databinding.ActivityWrittensettingBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class writtensetting extends AppCompatActivity {
    private String email, title, content;
    private ActivityWrittensettingBinding binding;
    private boolean passstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWrittensettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        passstop = true;

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        email = intent.getStringExtra("email");
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");

        if (title != "")
            passstop = false;
        binding.publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(writtensetting.this, MainActivity.class));
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (passstop)
            return;

        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Date time = new Date();

        Map<String, Object> userdata = new HashMap<>();
        userdata.put("user", email);
        userdata.put("title", title);
        userdata.put("content", content);
        userdata.put("Open status", false);
        userdata.put("goods", 0);
        userdata.put("views", 0);
        userdata.put("year", year.format(time.getTime()));
        userdata.put("month", month.format(time.getTime()));
        userdata.put("day", day.format(time.getTime()));

        db.collection("userWritting").document(title)
                .set(userdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("dbtest", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("dbtest", "Error writing document", e);
                    }
                });
    }
}