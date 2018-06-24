package com.example.pro4.smartbarbell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // --
        DataJSONActivity.init(HomeActivity.this);

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
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        // ---
    }

    protected void onResume() {
        super.onResume();
        Intent serviceIntent= new Intent(this,HostCardEmulatorService.class);
        serviceIntent.putExtra("flag", true);
        startService(serviceIntent);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("my-integer"));
        // ---
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        Handler handler = new Handler();

            public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            int yourInteger = intent.getIntExtra("message",-1/*default value*/);
            final TextView NfcText = findViewById(R.id.textView3);
                Runnable runnable = new Runnable() {
                    public void run() {
                        NfcText.setText("waiting for connection...");
                        NfcText.setTextColor(Color.parseColor("#ffffff"));
                    }
                };
            switch (yourInteger) {
                case 0:
                    handler.removeCallbacks(runnable);
                    NfcText.setText("... connecting ...");
                    NfcText.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case 1:
                    handler.removeCallbacks(runnable);
                    NfcText.setText("Loged In !");
                    NfcText.setTextColor(Color.parseColor("#00ff00"));
                    break;
                case 2:
                    handler.removeCallbacks(runnable);
                    NfcText.setText("Loged Out !");
                    NfcText.setTextColor(Color.parseColor("#ff0000"));
                    break;
                case 3:
                    handler.removeCallbacks(runnable);
                    NfcText.setText("Error !");
                    NfcText.setTextColor(Color.parseColor("#00ff00"));
                    break;
            }
                handler.postDelayed(runnable, 3000);


            Log.i("LoginLog", "Service Communication Check"+yourInteger);
        }
    };
}
