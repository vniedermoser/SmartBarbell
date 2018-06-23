package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AccountActivity extends AppCompatActivity {

    Button gender_m;
    Button gender_w;

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
                Intent intent = new Intent(AccountActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
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


        //-----------------
        //----main---
        //-----------------

        gender_m = findViewById(R.id.gender_man) ;
        gender_w = findViewById(R.id.gender_woman) ;

        gender_w.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                gender_w.setBackgroundTintList(ContextCompat.getColorStateList(AccountActivity.this, R.color.color_main));
                gender_m.setBackgroundTintList(ContextCompat.getColorStateList(AccountActivity.this, R.color.color_white));
            }
        });

        gender_m.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                gender_w.setBackgroundTintList(ContextCompat.getColorStateList(AccountActivity.this, R.color.color_white));
                gender_m.setBackgroundTintList(ContextCompat.getColorStateList(AccountActivity.this, R.color.color_main));
            }
        });

        Button r = findViewById(R.id.color1);
        Button g = findViewById(R.id.color2);
        Button b = findViewById(R.id.color3);

    
    }

}
