package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);





        ImageButton calendar = findViewById(R.id.set_calendar) ;
        ImageButton workout = findViewById(R.id.set_workout) ;
        ImageButton nfc = findViewById(R.id.set_nfc) ;
        ImageButton stats = findViewById(R.id.set_stats) ;
        ImageButton account = findViewById(R.id.set_account) ;
        Button main = findViewById(R.id.set_settings);

        main.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



        calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }


}
