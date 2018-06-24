package com.example.pro4.smartbarbell;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;


public class ExerciseActivity extends AppCompatActivity {

    private static String exerciseName;

    // Timer Variables
    TextView textView;
    ImageButton start, pause, reset;
    long millisecondTime, startTime, timeBuff, updateTime = 0L;
    Handler handler;
    int centiseconds, seconds, minutes;

    // List and Adapter Variables
    private static ArrayList<Object> exerciseDataList;
    private static ArrayList<Object> exerciseDataListStrings;
    private static ArrayAdapter<Object> adapter;

    // Input Variables
    private EditText weightInput;
    private static String weightString;
    private static final String unit = "kg";
    private EditText repsInput;
    private static String repsString;
    private TextView dateInput;
    private static String dateString; // entire date

    // Calendar Variables
    private static int day, month, year;
    // Array for Design output
    private static String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        // --
        navigation();

        declareVariables();

        timerListener();

        calendar();

        results();

        final TextView exerciseTitle = findViewById(R.id.exercise_name);
        exerciseTitle.setText(exerciseName);
    }

    public Runnable runnable = new Runnable() {
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int) (updateTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            centiseconds = (int) (updateTime % 100);

            textView.setText(String.format(Locale.GERMANY, "%02d", minutes) + ":"
                    + String.format(Locale.GERMANY, "%02d", seconds) + ","
                    + String.format(Locale.GERMANY, "%02d", centiseconds));
            handler.postDelayed(this, 0);
        }
    };

    public void declareVariables() {
        // ----- T I M E R ------ //
        textView = findViewById(R.id.txtTimer);
        start = findViewById(R.id.btnStart);
        pause = findViewById(R.id.btnPause);
        reset = findViewById(R.id.btnReset);
        handler = new Handler();

        // ----- I N P U T ----- //
        dateInput = findViewById(R.id.dateInput);
        Calendar myCurrentDate = Calendar.getInstance();
        day = myCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = myCurrentDate.get(Calendar.MONTH);
        year = myCurrentDate.get(Calendar.YEAR);
    }

    public void timerListener() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                reset.setEnabled(false);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeBuff += millisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                millisecondTime = 0L;
                startTime = 0L;
                timeBuff = 0L;
                updateTime = 0L;
                seconds = 0;
                minutes = 0;
                centiseconds = 0;

                textView.setText("00:00:00");
            }
        });
    }

    public void calendar() {
        dateInput.setText(String.format(Locale.GERMANY, "%d %s", day, monthArray[month]));
        // save date correct for list
        dateString = day + "/" + (month + 1) + "/" + year;

        // https://www.youtube.com/watch?v=5qdnoRHfAYU
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ExerciseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int Year, int monthOfYear, int dayOfMonth) {
                        // set Text for Design
                        dateInput.setText(String.format(Locale.GERMANY, "%d %s", dayOfMonth, monthArray[monthOfYear]));
                        // save date correct for list
                        dateString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + Year;
                        day = dayOfMonth;
                        month = monthOfYear;
                        year = Year;
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void results() {
        exerciseDataList = DataJSONActivity.getExerciseData(exerciseName);
        exerciseDataListStrings = toStringList(exerciseDataList);
        reverseSort(exerciseDataListStrings);

        adapter = new ArrayAdapter<>(ExerciseActivity.this, R.layout.result_item, R.id.resultItem, exerciseDataListStrings);

        ListView listView = findViewById(R.id.results);
        listView.setAdapter(adapter);

        // --

        weightInput = findViewById(R.id.weightInput);
        repsInput = findViewById(R.id.repsInput);

        Button addResult = findViewById(R.id.addResult);

        addResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightString = weightInput.getText().toString();
                repsString = repsInput.getText().toString();

                String newEntry = dateString + "  -  " + weightString + " " + unit + "  -  " + repsString + " reps";

                // add new item to arraylist
                exerciseDataListStrings.add(0, newEntry);

                // sort list in reverse order: newest date first
                reverseSort(exerciseDataListStrings);

                // notify listview of data changed
                adapter.notifyDataSetChanged();

                // --

                ArrayList<Object> data_distance = new ArrayList<>();
                ArrayList<Object> data_weight = new ArrayList<>();
                String date = year + "-" + (month + 1) + "-" + day + " ";
                int reps = repsString.length() > 0 ? (int) Double.parseDouble(repsString) : 0;
                int weight = weightInput.length() > 0 ? (int) Double.parseDouble(weightString) : 0;

                ArrayList<Object> exerciseData = new ArrayList<>();
                exerciseData.add(data_distance);
                exerciseData.add(data_weight);
                exerciseData.add(date);
                exerciseData.add(reps);
                exerciseData.add(weight);

                ArrayList<Object> entry = new ArrayList<>();
                entry.add(exerciseData);

                DataJSONActivity.addExerciseData(ExerciseActivity.this, exerciseName, entry);

                // --

                // hide keyboard after adding exercise
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                // reset date to current date
                Calendar myCurrentDate = Calendar.getInstance();
                day = myCurrentDate.get(Calendar.DAY_OF_MONTH);
                month = myCurrentDate.get(Calendar.MONTH);
                year = myCurrentDate.get(Calendar.YEAR);

                dateInput.setText(String.format(Locale.GERMANY, "%d %s", day, monthArray[month]));
                // save date correct for list
                dateString = day + "/" + (month + 1) + "/" + year;

                // clear text in input field
                weightInput.setText("");
                repsInput.setText("");
            }
        });
    }

    public static ArrayList<Object> toStringList(ArrayList<Object> exerciseDataList) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Object entry : exerciseDataList) {
            // --
            String date = "";
            String weight = "";
            String reps = "";
            // --
            int i = 0;
            for (Object subentry : (ArrayList) entry) {
                // date
                if (i == 2 && subentry instanceof String) {
                    String data = subentry.toString();
                    date = data.substring(0, data.indexOf(' '));
                    String[] ymd = date.split("-");
                    date = ymd[2] + "/" + ymd[1] + "/" + ymd[0]; // display only date
                }
                // reps
                if (i == 3 && subentry instanceof Long || i == 3 && subentry instanceof Integer) {
                    reps = subentry.toString();
                }
                // weight
                if (i == 4 && subentry instanceof Long || i == 4 && subentry instanceof Integer) {
                    weight = subentry.toString();
                }
                i++;
            }
            String exerciseData = date + "  -  " + weight + " " + unit + "  -  " + reps + " reps";
            arrayList.add(exerciseData);
        }
        return arrayList;
    }

    private void reverseSort(ArrayList<Object> arrayList) {
        Collections.sort(arrayList, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).compareTo((String) o2);
            }
        });
        Collections.reverse(arrayList);
    }

    private void navigation() {
        ImageButton calendar = findViewById(R.id.calendar);
        ImageButton workout = findViewById(R.id.workout);
        ImageButton nfc = findViewById(R.id.nfc);
        ImageButton stats = findViewById(R.id.stats);
        ImageButton account = findViewById(R.id.account);
        Button settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        workout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        nfc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void setExerciseName(String exerciseName) {
        ExerciseActivity.exerciseName = exerciseName;
    }
}
