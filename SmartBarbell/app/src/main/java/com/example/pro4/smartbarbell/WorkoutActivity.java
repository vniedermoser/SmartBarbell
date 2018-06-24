package com.example.pro4.smartbarbell;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WorkoutActivity extends AppCompatActivity {

    // List and Adapter Variables
    private static ArrayList<Object> workoutList;
    private static ArrayAdapter<Object> adapter;

    private ListView listView;

    // Input Variables
    private EditText workoutNameEditText;
    private static String workoutNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        // --
        navigation();

        workoutList = DataJSONActivity.getWorkoutList();
        sort(workoutList);

        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.listItem, workoutList);

        // --

        listView = findViewById(R.id.yourWorkouts);
        listView.setAdapter(adapter);

        listView.setLongClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(WorkoutActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
                ExerciseListActivity.setWorkoutName(workoutList.get(position).toString());
                openWorkout(view);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String name = workoutList.get(position).toString();
                // confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutActivity.this);
                builder.setTitle("Are you sure?")
                        .setMessage("Do you really want to delete the workout '" + name + "'?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                workoutList.remove(position);
                                DataJSONActivity.deleteWorkout(WorkoutActivity.this, name);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("delete", "delete was cancelled");
                            }
                        })
                        .show();

                return true;
            }
        });

        // --

        workoutNameEditText = findViewById(R.id.workoutName);
        Button addWorkout = findViewById(R.id.addWorkout);

        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutNameString = workoutNameEditText.getText().toString();

                // check if empty and name available, push to map & JSON
                if (workoutNameString.matches("[A-Za-z0-9]+") && DataJSONActivity.workoutAvailable(workoutNameString)) {
                    // add new workout to arraylist
                    workoutList.add(workoutNameString);

                    // sort list in ascending order: sort by alphabetical order
                    sort(workoutList);

                    // update Map & JSON
                    // the exerciseList for this workout is a placeholder
                    DataJSONActivity.addWorkout(WorkoutActivity.this, workoutNameString, new ArrayList<>());

                    // notify listview of data changed
                    adapter.notifyDataSetChanged();

                    // hide keyboard after adding workout
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }

                    // clear text in input field
                    workoutNameEditText.setText("");
                } else if (!(workoutNameString.length() > 0)) {
                    Toast.makeText(WorkoutActivity.this, "Please enter a workout name.", Toast.LENGTH_SHORT).show();
                } else if (!workoutNameString.matches("[A-Za-z0-9]+")) {
                    Toast.makeText(WorkoutActivity.this, "Please only use alphanumeric characters.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(WorkoutActivity.this, "This workout already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // --
        workoutList = DataJSONActivity.getWorkoutList();
        sort(workoutList);
        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.listItem, workoutList);
        listView = findViewById(R.id.yourWorkouts);
        listView.setAdapter(adapter);
    }

    public void navigation() {
        ImageButton calendar = findViewById(R.id.wo_calendar);
        ImageButton nfc = findViewById(R.id.wo_nfc);
        ImageButton stats = findViewById(R.id.wo_stats);
        ImageButton account = findViewById(R.id.wo_account);

        Button settings = findViewById(R.id.wo_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        nfc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openWorkout(View view) {
        Intent intent = new Intent(WorkoutActivity.this, ExerciseListActivity.class);
        startActivity(intent);
    }

    private void sort(ArrayList<Object> arrayList) {
        Collections.sort(arrayList, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).compareTo((String) o2);
            }
        });
    }
}
