package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        /*Fix 26.04.18*/



        //-----------------
        //----navigation---
        //-----------------

        ImageButton calendar = findViewById(R.id.acc_calendar) ;
        ImageButton workout = findViewById(R.id.acc_workout) ;
        ImageButton nfc = findViewById(R.id.acc_nfc) ;
        ImageButton stats = findViewById(R.id.acc_stats) ;

        Button settings = findViewById(R.id.acc_settings);

        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

}
