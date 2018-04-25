package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;


public class WorkoutActivity extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.workout, container, false);

        String[] workouts = {"Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3","Workout 1", "Workout 2", "Workout 3"};

        ListView workoutList = view.findViewById(R.id.listOfWorkouts);


        // Adapter macht die liste clickable
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                workouts
        );
        workoutList.setAdapter(listViewAdapter);

        workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position >= 0){
                    Toast.makeText(getActivity(), "go to exercise", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ExerciseListActivity.class);
                    startActivity(intent);
                }

            }
        });

        return view;
    }
};



