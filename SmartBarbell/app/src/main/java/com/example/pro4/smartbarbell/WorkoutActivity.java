package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WorkoutActivity extends AppCompatActivity {

    /**
     //List and Adapter Variables
     private ArrayList<String> arrayList;
     private ArrayAdapter<String> adapter;

     //Input Variables
     private EditText workoutName;
     **/

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

        Button settings = findViewById(R.id.wo_settings);

        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, HomeActivity.class);
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

        /*
         ListView listView=(ListView)findViewById(R.id.yourWorkouts);

         arrayList = new ArrayList<>();

         adapter=new ArrayAdapter<String>(this,R.layout.workout_item,R.id.txtWorkouts,arrayList);
         listView.setAdapter(adapter);

         workoutName =(EditText)findViewById(R.id.workoutName);
         Button addWorkout=(Button)findViewById(R.id.addWorkout);

         addWorkout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        String newItem= workoutName.getText().toString();
        // add new item to arraylist
        arrayList.add(0,newItem);
        // notify listview of data changed
        adapter.notifyDataSetChanged();
        }
        });
         */
    }


}
