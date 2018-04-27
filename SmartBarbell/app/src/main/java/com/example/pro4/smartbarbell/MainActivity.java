package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    protected String name;
    protected String profile_pic_url;

    // -----------------------
    // [START] basic structure
    // -----------------------

    // see here for ref https://stackoverflow.com/questions/6812003/difference-between-oncreate-and-onstart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "On Create .....");
        // ---
        Button button1 = findViewById(R.id.ResultListActivity);
        Button button2 = findViewById(R.id.TimerActivity);

        ImageButton calendar = findViewById(R.id.main_calendar);
        ImageButton workout = findViewById(R.id.main_workout);
        ImageButton stats = findViewById(R.id.main_stats);
        ImageButton account = findViewById(R.id.main_account);

        Button settings = findViewById(R.id.main_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });

        //-----------------
        //----navigation---
        //-----------------


        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        //------------------


    }

    /* (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "On Destroy .....");
        // ---
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "On Pause .....");
        // ---
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onRestart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "On Restart .....");
        // ---
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "On Resume .....");
        // ---
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "On Start .....");
        // ---
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "On Stop .....");
        // ---
    }

    // ---------------------
    // [END] basic structure
    // ---------------------

}



