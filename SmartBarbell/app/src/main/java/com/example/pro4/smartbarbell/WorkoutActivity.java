package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        
        //-----------------
        //----navigation---
        //-----------------

        ImageButton calendar = findViewById(R.id.wo_calendar) ;
        ImageButton nfc = findViewById(R.id.wo_nfc) ;
        ImageButton stats = findViewById(R.id.wo_stats) ;
        ImageButton account = findViewById(R.id.wo_account) ;

        calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        stats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }


}
