package com.example.baston105;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class EditEvent extends Activity {
    DBController2 controller = new DBController2(this);
    EditText bookdate,booknurse,bookevents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        bookdate = (EditText) findViewById(R.id.addDateEditText);
        booknurse = (EditText) findViewById(R.id.addNurseEditText);
        bookevents = (EditText) findViewById(R.id.addEventEditText);


        try{
            Intent iiii = getIntent();
            String hastaid = iiii.getStringExtra("hastaid");
            HashMap<String,String> hastaliste = controller.getBookInfo(hastaid);
            if(hastaliste.size() != 0){
                bookdate.setText(hastaliste.get("date"));
                booknurse.setText(hastaliste.get("nurse"));
                bookevents.setText(hastaliste.get("events"));



            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void goMainpage(View view){
        Intent i = new Intent(getApplicationContext(),GuardBook.class);
        startActivity(i);
    }

    public void editEvent(View view){
        HashMap<String,String> queryValue = new HashMap<String,String>();
        bookdate = (EditText) findViewById(R.id.addDateEditText);
        booknurse = (EditText) findViewById(R.id.addNurseEditText);
        bookevents = (EditText) findViewById(R.id.addEventEditText);
        Intent i = getIntent();
        String hastaid = i.getStringExtra("hastaid");
        queryValue.put("id",hastaid);
        queryValue.put("date",bookdate.getText().toString());
        queryValue.put("nurse",booknurse.getText().toString());
        queryValue.put("events",bookevents.getText().toString());
        controller.editbook(queryValue);
        goMainpage(view);

    }

    public void deleteBook(View view){
        Intent ii = getIntent();
        String hastaid = ii.getStringExtra("hastaid");
        controller.deletebook(hastaid);
        goMainpage(view);
    }
}