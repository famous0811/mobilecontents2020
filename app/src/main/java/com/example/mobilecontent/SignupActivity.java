package com.example.mobilecontent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilecontent.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public static final String TAG = "shared";
    private String email, nickname, password, passwordagain;
    private SharedPreferences sp;
    private ActivitySignupBinding binding;
    private boolean backsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backsw = false;
        //로그인부분
        mAuth = FirebaseAuth.getInstance();

        binding.back.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                backsw=true;
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
        binding.signup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.email.getText().toString();
                nickname = binding.nickname.getText().toString();
                if (password != passwordagain) {
                    Toast.makeText(SignupActivity.this, "비밀번호를 잘못입력했습니다.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        binding.password.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력이 끝났을 때
//                if(!arg0.toString().contains("@"))
                //애러처리 해주기
                password=s.toString();
            }
        });

        binding.againpassword.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때
//                        if(arg0.toString().equals(password))
                //애러처리 해주기
                passwordagain = arg0.toString();
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(backsw)
            return;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("nickname", nickname);
        //sharedpreferences에 값 저장
        sp = getSharedPreferences("userdata",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("email",email);
        editor.putString("password",password);
        editor.putString("nickname",nickname);
        editor.apply();

        db.collection("users").document(nickname)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
}