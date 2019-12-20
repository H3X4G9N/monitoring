package com.example.monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.apiEdit);
    }
    public void manageDC(View view){
        String api = editText.getText().toString();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        HTTP.apiURL = api;
    }

    public void watchDC(View view){
        String api = editText.getText().toString();
        Intent intent = new Intent(MainActivity.this, WatchDCActivity.class);
        startActivity(intent);
        HTTP.apiURL = api;
    }


}

/*
* FIRST APP ACTIVITY THAT LET'S YOU CHOOSE BETWEEN MANAGING DATA COLLECTORS OR WATCHING DATA COLLECTOR INFO
* */
