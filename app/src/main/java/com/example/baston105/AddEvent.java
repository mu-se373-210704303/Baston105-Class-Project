package com.example.baston105;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.VideoView;

import java.util.HashMap;

public class AddEvent extends Activity {
    DBController2 controller = new DBController2(this);
    EditText bookdate,booknurse,bookevents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        bookdate = (EditText) findViewById(R.id.addDateEditText);
        booknurse = (EditText) findViewById(R.id.addNurseEditText);
        bookevents = (EditText) findViewById(R.id.addEventEditText);



    }
    public void newPatientAdd(View view){
        HashMap<String,String> query = new HashMap<String,String>();
        query.put("date", bookdate.getText().toString());
        query.put("nurse", booknurse.getText().toString());
        query.put("events", bookevents.getText().toString());
        controller.addbook(query);
        goMainPage(view);
    }
    public void goMainPage(View view){
        Intent i = new Intent(getApplicationContext(),GuardBook.class);
        startActivity(i);
    }
}