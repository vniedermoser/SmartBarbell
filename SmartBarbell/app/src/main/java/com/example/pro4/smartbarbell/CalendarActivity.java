package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import org.json.JSONArray;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);



        Spinner mon = findViewById(R.id.spinnerMON);
        Spinner tue = findViewById(R.id.spinnerTUE);
        Spinner wed = findViewById(R.id.spinnerWED);
        Spinner thu = findViewById(R.id.spinnerTHU);
        Spinner fri = findViewById(R.id.spinnerFRI);
        Spinner sat = findViewById(R.id.spinnerSAT);
        Spinner sun = findViewById(R.id.spinnerSUN);


//item liste für spinner --> TODO: strings aus json auslesen & speichern
        String[] items = new String[]{"-------------","push ups", "deadlifts", "excercise3"};
//welches item wird agezeigt
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        mon.setAdapter(adapter);
        tue.setAdapter(adapter);
        wed.setAdapter(adapter);
        thu.setAdapter(adapter);
        fri.setAdapter(adapter);
        sat.setAdapter(adapter);
        sun.setAdapter(adapter);







        //-----------------
        //----navigation---
        //-----------------


        ImageButton workout = findViewById(R.id.cal_workout) ;
        ImageButton nfc = findViewById(R.id.cal_nfc) ;
        ImageButton stats = findViewById(R.id.cal_stats) ;
        ImageButton account = findViewById(R.id.cal_account) ;

        Button settings = findViewById(R.id.cal_settings);

        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });


    }


}
