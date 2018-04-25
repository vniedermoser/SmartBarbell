package com.example.pro4.smartbarbell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
    }
}

/*
 package com.example.pro4.smartbarbell;

 import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import android.os.SystemClock;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.ListView;
 import android.widget.TextView;
 import android.os.Handler;
 import java.util.Arrays;
 import java.util.List;
 import java.util.ArrayList;

 public class AccountActivity extends Fragment {

 //Variables
 TextView txtTimer;
 Button start;
 long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
 Handler handler;
 int Seconds, Minutes, MilliSeconds ;

 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

 View view = inflater.inflate(R.layout.account, container, false);


 //initialize Variables
 txtTimer = (TextView)view.findViewById(R.id.txtTimer);
 start = (Button)view.findViewById(R.id.btnStart);

 handler = new Handler() ;

 start.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 StartTime = SystemClock.uptimeMillis();
 handler.postDelayed(runnable, 0);
 }
 });


 return inflater.inflate(R.layout.account, container, false);
 };

 public Runnable runnable = new Runnable() {

 public void run() {

 MillisecondTime = SystemClock.uptimeMillis() - StartTime;

 UpdateTime = TimeBuff + MillisecondTime;

 Seconds = (int) (UpdateTime / 1000);

 Minutes = Seconds / 60;

 Seconds = Seconds % 60;

 MilliSeconds = (int) (UpdateTime % 1000);

 txtTimer.setText("" + Minutes + ":"
 + String.format("%02d", Seconds) + ":"
 + String.format("%03d", MilliSeconds));

 handler.postDelayed(this, 0);
 }

 };
 }

*/