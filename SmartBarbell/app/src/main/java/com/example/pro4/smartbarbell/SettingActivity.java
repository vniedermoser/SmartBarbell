package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageButton calendar = findViewById(R.id.calendar) ;
        ImageButton workout = findViewById(R.id.workout) ;
        ImageButton nfc = findViewById(R.id.nfc) ;
        ImageButton stats = findViewById(R.id.stats) ;
        ImageButton account = findViewById(R.id.account) ;
        Button settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });



        calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
