package com.example.mobilecontent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sf = getSharedPreferences("userdata", MODE_PRIVATE);

        boolean auto = sf.getBoolean("autoLogin", false);
        String email = sf.getString("email", "");
        String password = sf.getString("password", "");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (auto) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(splash.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(splash.this, MainActivity.class));

                    } else {
                        startActivity(new Intent(splash.this, LoginActivity.class));
                    }
                }
            });
        } else {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(splash.this, LoginActivity.class));//LoginActivity
            }, 2000);
        }
    }
}