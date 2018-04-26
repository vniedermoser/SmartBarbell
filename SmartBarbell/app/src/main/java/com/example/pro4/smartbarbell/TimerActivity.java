package com.example.pro4.smartbarbell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

// https://www.android-examples.com/android-create-stopwatch-example-tutorial-in-android-studio/

public class TimerActivity extends AppCompatActivity {

    TextView textView ;
    Button start, pause, reset;
    //Button lap;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, Centisecond;

    /*
    ListView listView ;
    String[] ListElements = new String[] {  };
    List<String> ListElementsArrayList ;
    ArrayAdapter<String> adapter ;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        textView = (TextView)findViewById(R.id.txtTimer);
        start = (Button)findViewById(R.id.btnStart);
        pause = (Button)findViewById(R.id.btnPause);
        reset = (Button)findViewById(R.id.btnReset);
        handler = new Handler() ;

        /*
        lap = (Button)findViewById(R.id.btnLap) ;
        listView = (ListView)findViewById(R.id.listview1);
        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        adapter = new ArrayAdapter<String>(TimerActivity.this,
                android.R.layout.simple_list_item_1,
                ListElementsArrayList
        );

        listView.setAdapter(adapter);
*/
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

                //ListElementsArrayList.clear();
                //adapter.notifyDataSetChanged();
            }
        });

/*
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListElementsArrayList.add(textView.getText().toString());

                adapter.notifyDataSetChanged();

            }
        });

*/

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
