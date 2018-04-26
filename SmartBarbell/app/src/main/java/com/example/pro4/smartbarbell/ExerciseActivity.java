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

    //Variables
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    //Calendar Variables
    private TextView tv;
    private Calendar myCurrentDate;
    int day, month, year;

    //Input Variables
    // private EditText dateInput;
    private EditText weightInput;
    private EditText repsInput;

    private Button buttonWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        tv = (TextView) findViewById(R.id.dateInput);
        myCurrentDate = Calendar.getInstance();
        day = myCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = myCurrentDate.get(Calendar.MONTH)+1;
        year = myCurrentDate.get(Calendar.YEAR);

        tv.setText(day+"/n"+month);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ExerciseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        tv.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
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

                String newItem= tv.getText().toString() +"   " + weightInput.getText().toString() + buttonWeight.getText().toString() +" "+ repsInput.getText().toString();
                // add new item to arraylist
                arrayList.add(newItem);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }

        });
    }
}
