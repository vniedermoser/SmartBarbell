package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ImageButton calendar = findViewById(R.id.main_calendar);
        ImageButton workout = findViewById(R.id.main_workout);
        ImageButton stats = findViewById(R.id.main_stats);
        ImageButton account = findViewById(R.id.main_account);

        Button settings = findViewById(R.id.main_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });



        //-----------------
        //----navigation---
        //-----------------


        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        //------------------
    }
    protected void onPause() {
        super.onPause();
        Intent serviceIntent= new Intent(this,HostCardEmulatorService.class);
        serviceIntent.putExtra("flag", false);
      //  startService(serviceIntent);
        startService(serviceIntent);
        // ---
    }

    protected void onResume() {
        super.onResume();
        Intent serviceIntent= new Intent(this,HostCardEmulatorService.class);
        serviceIntent.putExtra("flag", true);
        startService(serviceIntent);
        // ---
    }
}
