package com.example.mobilecontent;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilecontent.databinding.ActivityAddAlreamBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addAlream extends AppCompatActivity {
    private ActivityAddAlreamBinding binding;
    Map<String, Object> alreams = new HashMap<>();
    private String AmPm;
    private Object cycle;
    private String DayOfWeek;
    private SharedPreferences sf;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAlreamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TimePickerDialog.OnTimeSetListener onStartTimeListener = new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }
                AmPm = AM_PM;
            }
        };

        Spinner();
        onclickchick();

        binding.select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Log.d("onclick", "onItemSelected: ");
                cycle = parent.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        binding.cancel.setOnClickListener(v -> startActivity(new Intent(addAlream.this, MainActivity.class)));
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartTimeListener.onTimeSet(binding.timepicker, binding.timepicker.getHour(), binding.timepicker.getMinute());
                alreams.put("title", binding.alreamname.getText().toString());
                alreams.put("hour", binding.timepicker.getHour());
                alreams.put("minute", binding.timepicker.getMinute());
                alreams.put("AmPm", AmPm);
                alreams.put("Cycle", cycle);
                alreams.put("DayOfWeek", DayOfWeek);

                sf = getSharedPreferences("userdata", MODE_PRIVATE);
                String nickname = sf.getString("nickname", "");

                db.collection("users").document(nickname)
                        .set(alreams)
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
                startActivity(new Intent(addAlream.this, MainActivity.class));
            }
        });
    }

    private void Spinner() {
        String[] showset = new String[]{
                "2주", "3주"
        };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(addAlream.this, android.R.layout.simple_spinner_item, showset);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.select.setAdapter(adapter1);
        binding.select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(addAlream.this, Integer.toString(position), Toast.LENGTH_SHORT); //본인이 원하는 작업.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void onclickchick() {
        binding.sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayOfWeek = "sunday";
            }
        });
        binding.monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayOfWeek = "sunday";
            }
        });
        binding.Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayOfWeek = "sunday";
            }
        });
        binding.weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayOfWeek = "sunday";
            }
        });
        binding.Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayOfWeek = "sunday";
            }
        });
        binding.Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayOfWeek = "sunday";
            }
        });
        binding.saterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayOfWeek = "saterday";
            }
        });
    }
}