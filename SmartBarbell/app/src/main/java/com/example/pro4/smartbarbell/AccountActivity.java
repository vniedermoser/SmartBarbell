package com.example.pro4.smartbarbell;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);



        //-----------------
        //----navigation---
        //-----------------

        ImageButton calendar = findViewById(R.id.acc_calendar) ;
        ImageButton workout = findViewById(R.id.acc_workout) ;
        ImageButton nfc = findViewById(R.id.acc_nfc) ;
        ImageButton stats = findViewById(R.id.acc_stats) ;
public class AccountActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.account, container, false);
    }
}

