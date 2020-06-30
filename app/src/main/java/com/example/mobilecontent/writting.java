package com.example.mobilecontent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilecontent.databinding.ActivityWrittingBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class writting extends AppCompatActivity {
    private ActivityWrittingBinding binding;
    private SharedPreferences sf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWrittingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        binding.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=binding.titletext.getText().toString();
                String content=binding.content.getText().toString();
                sf = getSharedPreferences("userdata",MODE_PRIVATE);
                String email = sf.getString("email","");

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> userdata = new HashMap<>();

                userdata.put("user",email);
                userdata.put("title",title);
                userdata.put("content",content);
                userdata.put("Open status",false);
                userdata.put("goods",0);
                userdata.put("views",0);
//test
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
                startActivity(new Intent(writting.this,writtensetting.class));
            }
        });
    }
}