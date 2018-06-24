package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // --

        navigation();

        TextView textView = findViewById(R.id.deleteData);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataJSONActivity.deleteJSONs(SettingActivity.this);
            }
        });
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
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        workout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });
        nfc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
