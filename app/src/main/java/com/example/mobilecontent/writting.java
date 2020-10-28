package com.example.mobilecontent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);


        //sharedpreferen값 가져오기
        sf = getSharedPreferences("userdata", MODE_PRIVATE);
        String title = sf.getString("titlesave", "");
        String content = sf.getString("contentsave", "");

        setalert();

        //저장한거 불러오는 부분
//        binding.titletext.setText(title);
//        binding.content.setText(content);

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

        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이부분 어쩌지...?
//                builder.create().show(); //보이기
            }
        });

        binding.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setalert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(writting.this);
        Spinner catagori = new Spinner(writting.this);

        builder.setTitle("작성 설정"); //AlertDialog의 제목 부분
        builder.setMessage("일지,질문,일지 라고하면 누구일지?, 카테고리설정(일부분 목록 보여주고 추가 작성 가능),저장한 내용 불러오기"); //AlertDialog의 내용 부분

        String[] showset = new String[]{
                "2주", "3주"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(writting.this, android.R.layout.simple_spinner_item, showset);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        catagori.setAdapter(adapter1);
        catagori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(writting.this, Integer.toString(position), Toast.LENGTH_SHORT); //본인이 원하는 작업.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        builder.setView(catagori);

        builder.setPositiveButton("작성하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
//        builder.setNegativeButton("작성안해",  null);
        builder.setNeutralButton("작성안해", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(writting.this, MainActivity.class));
            }
        });
        builder.create().show(); //보이기
    }
    private void wrritequestion() {
//        Date time = new Date();
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        SimpleDateFormat year = new SimpleDateFormat("yyyy");
//        SimpleDateFormat month = new SimpleDateFormat("MM");
//        SimpleDateFormat day = new SimpleDateFormat("dd");
//
//        Map<String, Object> userdata = new HashMap<>();
//        String email =  getSharedPreferences("userdata", MODE_PRIVATE).getString("email", "");
//
//        userdata.put("title", binding.titletext.getText().toString());
//        userdata.put("content", binding.content.getText().toString());
//        userdata.put("year", year.format(time.getTime()));
//        userdata.put("month", month.format(time.getTime()));
//        userdata.put("day", day.format(time.getTime()));
//        userdata.put("goods", 0);
//        userdata.put("views", 0);
//        userdata.put("user", email);
//
//        db.collection("userwritequestion").document(binding.titletext.getText().toString())
//                .set(userdata)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        startActivity(new Intent(writequestion.this, MainActivity.class));
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("TAG", "Error writing document", e);
//                    }
//                });
    }

    private void wirteDiary() {
//        SimpleDateFormat year = new SimpleDateFormat("yyyy");
//        SimpleDateFormat month = new SimpleDateFormat("MM");
//        SimpleDateFormat day = new SimpleDateFormat("dd");
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Date time = new Date();
//
//        Map<String, Object> userdata = new HashMap<>();
//        userdata.put("user", email);
//        userdata.put("title", title);
//        userdata.put("content", content);
//        userdata.put("Open status", false);
//        userdata.put("goods", 0);
//        userdata.put("views", 0);
//        userdata.put("year", year.format(time.getTime()));
//        userdata.put("month", month.format(time.getTime()));
//        userdata.put("day", day.format(time.getTime()));
//
//        db.collection("userWritting").document(title)
//                .set(userdata)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d("dbtest", "DocumentSnapshot successfully written!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("dbtest", "Error writing document", e);
//                    }
//                });
    }
}