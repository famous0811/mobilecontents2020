package com.example.mobilecontent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilecontent.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "shared";
    private FirebaseAuth mAuth;
    private ActivityLoginBinding binding;
    private static String email;
    private static String password;
    private String sw;
    private SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        sf = getSharedPreferences("userdata", MODE_PRIVATE);

        email = sf.getString("email", "");
        password = sf.getString("password", "");

        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환

        binding.userid.setText(email);
        binding.userpassword.setText(password);

        binding.signup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));//LoginActivity
            }
        });

        binding.autologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sf.edit();
                editor.putBoolean("autoLogin", binding.autologin.isChecked());
//        editor.putString("nickname",nickname)
                editor.apply();
            }
        });
        binding.login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.userid.getText().toString();
                password = binding.userpassword.getText().toString();
                if (email.length() == 0 || password.length() == 0)
                    return;

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            Toast.makeText(LoginActivity.this, "로그인 완료",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "로그인 실패",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sp;

        sp = getSharedPreferences("userdata", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("email", email);
        editor.putString("password", password);

//        editor.putString("nickname",nickname)
        editor.apply();
    }
    @Override public void onBackPressed() {
        //super.onBackPressed();
    }
}