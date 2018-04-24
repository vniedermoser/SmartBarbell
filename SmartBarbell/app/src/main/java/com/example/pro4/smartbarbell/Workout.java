package com.example.pro4.smartbarbell;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;


public class Workout extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.workout, container, false);
        final ArrayList<entry_workout> workoutList = new ArrayList<>();


        workoutList.add(new entry_workout("entry 1"));
        workoutList.add(new entry_workout("entry 2"));
        return view;
    }
};



