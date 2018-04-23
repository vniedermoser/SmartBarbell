package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

       private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.stats);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStats();
            }
        });


    }

    public void openStats(){
        startActivity(new Intent(MainActivity.this, Stats.class));
    }

}
