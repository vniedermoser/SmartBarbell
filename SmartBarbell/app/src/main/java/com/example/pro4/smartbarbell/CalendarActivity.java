package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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
                Intent intent = new Intent(CalendarActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
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
