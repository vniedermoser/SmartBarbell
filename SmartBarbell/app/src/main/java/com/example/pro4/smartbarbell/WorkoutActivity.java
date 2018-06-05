package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {


     //List and Adapter Variables
     private ArrayList<String> arrayList;
     private ArrayAdapter<String> adapter;

     //Input Variables
     private EditText workoutName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        navigation();


         ListView listView=(ListView)findViewById(R.id.yourWorkouts);

         arrayList = new ArrayList<>();

         //TODO: Aus dem bestehenden Json, die Namen der Workouts abrufen und schon in Liste schreiben

         adapter=new ArrayAdapter<String>(this,R.layout.workout_item,R.id.WorkoutButton,arrayList);
         listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(WorkoutActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });

         workoutName =(EditText)findViewById(R.id.workoutName);
         Button addWorkout=(Button)findViewById(R.id.addWorkout);

         //TODO: check if empty and name available, push to jason

         addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newItem= workoutName.getText().toString();

                // add new item to arraylist
                arrayList.add(0,newItem);
                //TODO: In das bestehende Json, den neuen Workout Namen schreiben (wenn noch nicht oben geschehen)

                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }
        });

    }
    public void navigation(){
        ImageButton calendar = findViewById(R.id.wo_calendar) ;
        ImageButton nfc = findViewById(R.id.wo_nfc) ;
        ImageButton stats = findViewById(R.id.wo_stats) ;
        ImageButton account = findViewById(R.id.wo_account) ;

        Button settings = findViewById(R.id.wo_settings);

        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, SettingActivity.class);
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

    }

    public void clickMe(View view){
        Button bt=(Button)view;
        //Toast.makeText(this, bt.getText().toString(),Toast.LENGTH_LONG).show();

        //Todo: Link auf die richtige Liste an Übungen
        Intent intent = new Intent(WorkoutActivity.this, ExerciseListActivity.class);
        startActivity(intent);
    }


}
