package com.example.pro4.smartbarbell;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        ImageButton calendar = findViewById(R.id.calendar) ;
        ImageButton workout = findViewById(R.id.workout) ;
        ImageButton nfc = findViewById(R.id.nfc) ;
        ImageButton stats = findViewById(R.id.stats) ;
        ImageButton account = findViewById(R.id.account) ;
        Button settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        stats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        declareVariables();

        timerListener();

        calendar();

        results();

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

    public void declareVariables(){

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
    }
    public void timerListener(){
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
    }
    public void calendar(){
        dateInput.setText(day+"\r\n"+monthArray[month-1]);
        //save date correct for list
        saveDate = day + "/" + month +"/" + year;
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

    }
    public void results(){
        ListView listView=(ListView)findViewById(R.id.results);

        arrayList = new ArrayList<>();
        filename = "Jason.json";
        readJson(filename);

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

                saveJson(filename,newItem);

                adapter.notifyDataSetChanged();
            }

        });
    }

    public void saveJson(String filename, String item){

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(item.getBytes());
            //outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        try {
            Writer output;

            File file = new File("//assets/" + filename + ".json");
            output = new BufferedWriter(new FileWriter(file));

            JSONObject obj = new JSONObject() ;

            try {
                obj.put("item", item);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            output.write(obj.toString());
            output.close();

            Toast.makeText(getApplicationContext(), "Composition saved", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        */


    }


    public void readJson(String filename){
        try {
            FileInputStream is = openFileInput(filename);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String content = new String(buffer);
            arrayList.add(0,content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /*
        try{
            InputStream in = getAssets().open(filename);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();

            String json = new String (buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                arrayList.add(0,obj.getString("item"));
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(ExerciseActivity.this, "Error reading file!", Toast.LENGTH_SHORT).show();
        }


        //return txt;
         }*/

}
