package com.example.pro4.smartbarbell;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ExerciseListActivity extends AppCompatActivity {

    private static String workoutName;

    //List and Adapter Variables
    private static ArrayList<Object> exerciseList;
    private static ArrayAdapter<Object> adapter;

    private ListView listView;

    //Input Variables
    private EditText exerciseNameEdit;
    private static String exerciseNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        // --
        navigation();

        exerciseList = DataJSONActivity.getExerciseListOfWorkout(workoutName);
        sort(exerciseList);

        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.listItem, exerciseList);

        final TextView workoutTitle = findViewById(R.id.exercise_list_workout_name);
        workoutTitle.setText(workoutName);

        // --

        listView = findViewById(R.id.yourWorkouts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ExerciseListActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
                ExerciseActivity.setExerciseName(exerciseList.get(position).toString());
                openExercise(view);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String exerciseName = exerciseList.get(position).toString();
                // confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseListActivity.this);
                builder.setTitle("Are you sure?")
                        .setMessage("Do you really want to delete the exercise '" + exerciseName + "'?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // confirmation dialog
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(ExerciseListActivity.this);
                                builder2.setMessage("Remove only exercise from workout or delete all exercise data?")
                                        .setPositiveButton("Remove Exercise", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                exerciseList.remove(position);
                                                DataJSONActivity.deleteExercise(ExerciseListActivity.this, exerciseName, workoutName);
                                                adapter.notifyDataSetChanged();
                                            }
                                        })
                                        .setNegativeButton("Delete Data", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                exerciseList.remove(position);
                                                DataJSONActivity.deleteExerciseForAll(ExerciseListActivity.this, exerciseName);
                                                DataJSONActivity.deleteExerciseData(ExerciseListActivity.this, exerciseName);
                                                adapter.notifyDataSetChanged();
                                            }
                                        })
                                        .show();
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

        exerciseNameEdit = findViewById(R.id.exerciseName);
        Button addExercise = findViewById(R.id.addExercise);

        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseNameString = exerciseNameEdit.getText().toString();

                //check if empty and name available, push to map & JSON
                if (exerciseNameString.matches("[A-Za-z0-9]+") && DataJSONActivity.exerciseAvailable(exerciseNameString, workoutName)) {
                    // add to exerciseList
                    exerciseList.add(exerciseNameString);

                    // sort list in ascending order: sort by alphabetical order
                    sort(exerciseList);

                    // update Map & JSON
                    DataJSONActivity.addExercise(ExerciseListActivity.this, exerciseNameString, workoutName);

                    // notify listView of data changed
                    adapter.notifyDataSetChanged();

                    // hide keyboard after adding exercise
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }

                    // clear text in input field
                    exerciseNameEdit.setText("");
                } else if (!(exerciseNameString.length() > 0)) {
                    Toast.makeText(ExerciseListActivity.this, "Please enter an exercise name.", Toast.LENGTH_SHORT).show();
                } else if (!exerciseNameString.matches("[A-Za-z0-9]+")) {
                    Toast.makeText(ExerciseListActivity.this, "Please only use alphanumeric characters.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ExerciseListActivity.this, "This exercise already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        exerciseList = DataJSONActivity.getExerciseListOfWorkout(workoutName);
        sort(exerciseList);
        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.listItem, exerciseList);
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
                Intent intent = new Intent(ExerciseListActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseListActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        nfc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseListActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseListActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openExercise(View view) {
        Intent intent = new Intent(ExerciseListActivity.this, ExerciseActivity.class);
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

    public static void setWorkoutName(String workoutName) {
        ExerciseListActivity.workoutName = workoutName;
    }
}
