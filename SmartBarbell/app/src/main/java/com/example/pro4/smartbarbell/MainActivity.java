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
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // ---------------------
    // start basic structure
    // ---------------------

    // see here for ref https://stackoverflow.com/questions/6812003/difference-between-oncreate-and-onstart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "On Create .....");

        Button button1 = (Button)findViewById(R.id.ResultListActivity);
        Button button2 = (Button)findViewById(R.id.TimerActivity);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });

        // ---
        /*
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener);
        */

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NFCActivity()).commit();
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

    // -------------------
    // end basic structure
    // -------------------

<<<<<<< HEAD
    /*
=======
/*
=======

    /*
>>>>>>> parent of d11c9b2... Merge branch 'master' of https://github.com/vniedermoser/SmartBarbell
>>>>>>> parent of d0b1a3e... Revert "Merge branch 'master' of https://github.com/vniedermoser/SmartBarbell"
    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.calender:
                    selectedFragment = new CalendarActivity();
                    break;
                case R.id.workout:
                    selectedFragment = new WorkoutActivity();
                    break;
                case R.id.nfc:
                    selectedFragment = new NFCActivity();
                    break;
                case R.id.stats:
                    selectedFragment = new StatsActivity();
                    break;
                case R.id.account:
                    selectedFragment = new AccountActivity();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
<<<<<<< HEAD
    };
*/

}
