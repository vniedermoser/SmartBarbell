package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    protected String name;

    // -----------------------
    // [START] basic structure
    // -----------------------

    // see here for ref https://stackoverflow.com/questions/6812003/difference-between-oncreate-and-onstart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "On Create .....");
        // ---
        Intent preLoginIntent = new Intent(MainActivity.this, PreLoginActivity.class);
        startActivity(preLoginIntent);
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



