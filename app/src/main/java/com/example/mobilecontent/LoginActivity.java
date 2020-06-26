package com.example.mobilecontent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "shared";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signupbutton=(Button)findViewById(R.id.signup);
        Button loginbutton=(Button)findViewById(R.id.login);

//        final String email=((EditText)findViewById(R.id.userid)).getText().toString();
//        final String password=((EditText)findViewById(R.id.userpassword)).getText().toString();
        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        SharedPreferences sf = getSharedPreferences("userdata",MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String email = sf.getString("email","");
        String password = sf.getString("password","");

//        EditText emailText=(EditText)findViewById(R.id.userid);
//        EditText passwordText=(EditText)findViewById(R.id.userpassword);
//        if(email.length()!=0 || password.length()!=0){
//            emailText.setText(email);
//            passwordText.setText(password);
//        }

        signupbutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

        loginbutton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                String email=((EditText)findViewById(R.id.userid)).getText().toString();
                String password=((EditText)findViewById(R.id.userpassword)).getText().toString();
                if(email.length()==0 || password.length()==0)
                    return;

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            Toast.makeText(LoginActivity.this, "로그인 완료",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "로그인 실패",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}