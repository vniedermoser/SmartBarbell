package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);




        barChart = findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 0));
        barEntries.add(new BarEntry(1, 1));
        barEntries.add(new BarEntry(2, 2));
        barEntries.add(new BarEntry(3, 3));
        barEntries.add(new BarEntry(4, 4));
        barEntries.add(new BarEntry(5, 5));

        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("MON");
        theDates.add("TUE");
        theDates.add("WED");
        theDates.add("THU");
        theDates.add("FRI");
        theDates.add("SAT");
        theDates.add("SUN");

        BarData theData = new BarData(theDates, barDataSet);
        barChart.setData(theData);





        //-----------------
        //----navigation---
        //-----------------

        ImageButton calendar = findViewById(R.id.stats_calendar) ;
        ImageButton workout = findViewById(R.id.stats_workout) ;
        ImageButton nfc = findViewById(R.id.stats_nfc) ;
        ImageButton account = findViewById(R.id.stats_account) ;

        Button settings = findViewById(R.id.stats_settings);

        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });





        nfc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

    }
}
