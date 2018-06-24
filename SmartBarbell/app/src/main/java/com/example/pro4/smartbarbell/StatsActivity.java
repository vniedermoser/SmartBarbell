package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class StatsActivity extends AppCompatActivity {


    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        double x;
        double y;

        x = 0;


        GraphView graph1 = (GraphView) findViewById(R.id.graph1);
        GraphView graph2 = (GraphView) findViewById(R.id.graph2);

        series = new LineGraphSeries<DataPoint>();
        for(int i = 0; i < 260; i++){
            x = x + 0.1;
            y = Math.sin(x);
            series.appendData(new DataPoint(x,y),true,260);
        }

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 2),
                new DataPoint(1, 6),
                new DataPoint(2, 4),
                new DataPoint(3, 3),
                new DataPoint(4, 7),
                new DataPoint(5, 5),
                new DataPoint(6, 1),
                new DataPoint(7, 4),
                new DataPoint(8, 6),
                new DataPoint(9, 7),
                new DataPoint(10, 3)
        });



        graph1.addSeries(series);
        graph2.addSeries(series2);
        series.setDrawBackground(false);
        series.setColor(R.color.color_r);
        series.setThickness(7);
        series.setColor(Color.WHITE);
        series.setAnimated(true);
        series.setDataPointsRadius(10);


        series2.setDrawBackground(false);
        series2.setColor(R.color.color_r);
        series2.setThickness(7);
        series2.setColor(Color.WHITE);
        series2.setAnimated(true);
        series2.setDataPointsRadius(10);


// styling series
        graph1.setBackgroundColor(getResources().getColor(R.color.color_darkGrey));
        graph2.setBackgroundColor(getResources().getColor(R.color.color_darkGrey));










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
