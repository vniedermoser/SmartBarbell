package com.example.pro4.smartbarbell;

import android.app.DatePickerDialog;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class ExerciseActivity extends AppCompatActivity {

    //Timer Variables
    TextView textView ;
    ImageButton start, pause, reset;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, Centisecond;

    //List and Adapter Variables
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    //Input Variables
    private EditText weightInput;
    private EditText repsInput;
    private TextView dateInput;

    private Button buttonWeight;

    //Calendar Variables
    int day, month, year;

    //Array for Design output
    private String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    //saveDate saves the whole date data
    private String saveDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        // ----- T I M E R ------ //
        textView = (TextView)findViewById(R.id.txtTimer);
        start = (ImageButton)findViewById(R.id.btnStart);
        pause = (ImageButton)findViewById(R.id.btnPause);
        reset = (ImageButton)findViewById(R.id.btnReset);
        handler = new Handler() ;


        // ----- I N P U T ----- //
        dateInput = (TextView) findViewById(R.id.dateInput);
        Calendar myCurrentDate = Calendar.getInstance();
        day = myCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = myCurrentDate.get(Calendar.MONTH)+1;
        year = myCurrentDate.get(Calendar.YEAR);


        //set Text for Design
        dateInput.setText(day+"\r\n"+monthArray[month-1]);
        //save date correct for list
        saveDate = day + "/" + month +"/" + year;


        // Timer Listener

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);

            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                Centisecond = 0 ;

                textView.setText("00:00:00");
            }
        });


        //Data Listener

        //https://www.youtube.com/watch?v=5qdnoRHfAYU
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ExerciseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        //set Text for Design
                        dateInput.setText(dayOfMonth +"\r\n"+monthArray[monthOfYear-1]);
                        //save date correct for list
                        saveDate = dayOfMonth + "/" + monthOfYear +"/" + year;
                    }
                }, year, month-1, day);
                datePickerDialog.show();
            }
        });

        ListView listView=(ListView)findViewById(R.id.results);
        String[] items={"JSON Entry 1", "JSON Entry 2"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.result_item,R.id.txtResults,arrayList);
        listView.setAdapter(adapter);

        //dateInput =(EditText)findViewById(R.id.dateInput);
        weightInput =(EditText)findViewById(R.id.weightInput);
        repsInput =(EditText)findViewById(R.id.repsInput);

        buttonWeight=(Button)findViewById(R.id.buttonWeight);
        Button addResult=(Button)findViewById(R.id.addResult);

        addResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newItem= saveDate + " " + weightInput.getText().toString() + buttonWeight.getText().toString() +" "+ repsInput.getText().toString();
                // add new item to arraylist
                arrayList.add(0,newItem);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }

        });
    }

    public Runnable runnable = new Runnable() {

        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            Centisecond = (int) (UpdateTime % 100);
            textView.setText("" + String.format("%02d", Minutes) + ":"
                    + String.format("%02d", Seconds) + ","
                    + String.format("%02d", Centisecond));
            handler.postDelayed(this, 0);
        }

    };


}
