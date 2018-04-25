package com.example.pro4.smartbarbell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class ExerciseActivity extends AppCompatActivity {

    //Variables
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        ListView listView=(ListView)findViewById(R.id.results);
        String[] items={"Apple","Banana","Clementine"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.result_item,R.id.txtitem,arrayList);
        listView.setAdapter(adapter);
        userInput=(EditText)findViewById(R.id.userInput);
        Button addResult=(Button)findViewById(R.id.addResult);

        addResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem=userInput.getText().toString();
                // add new item to arraylist
                arrayList.add(newItem);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }

        });

    }
}
