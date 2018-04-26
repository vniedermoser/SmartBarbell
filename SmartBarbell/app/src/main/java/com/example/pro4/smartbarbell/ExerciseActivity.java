package com.example.pro4.smartbarbell;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class ExerciseActivity extends AppCompatActivity {

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

        dateInput = (TextView) findViewById(R.id.dateInput);
        Calendar myCurrentDate = Calendar.getInstance();
        day = myCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = myCurrentDate.get(Calendar.MONTH)+1;
        year = myCurrentDate.get(Calendar.YEAR);


        //set Text for Design
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

        ListView listView=(ListView)findViewById(R.id.results);
        String[] items={""};
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
}
