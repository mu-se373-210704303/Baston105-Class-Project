package com.example.baston105;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.VideoView;

import java.util.HashMap;

public class AddTreatment extends Activity {
    DBController3 controller = new DBController3(this);
    EditText date,medicine,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatment);
        date = (EditText) findViewById(R.id.addDateEditText);
        medicine = (EditText) findViewById(R.id.addMedicineEditText);
        method = (EditText) findViewById(R.id.addMethodEditText);



    }
    public void newTreatmentAdd(View view){
        HashMap<String,String> query = new HashMap<String,String>();
        query.put("date", date.getText().toString());
        query.put("medicine", medicine.getText().toString());
        query.put("method", method.getText().toString());
        controller.addtreatment(query);
        goMainPage(view);
    }
    public void goMainPage(View view){
        Intent i = new Intent(getApplicationContext(),ExtraTreatment.class);
        startActivity(i);
    }
}