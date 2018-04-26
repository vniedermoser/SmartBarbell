package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        //-----------------
        //----navigation---
        //-----------------

        ImageButton calendar = findViewById(R.id.stats_calendar) ;
        ImageButton workout = findViewById(R.id.stats_workout) ;
        ImageButton nfc = findViewById(R.id.stats_nfc) ;
        ImageButton account = findViewById(R.id.stats_account) ;

        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
