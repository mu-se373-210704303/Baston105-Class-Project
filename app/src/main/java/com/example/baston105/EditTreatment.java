package com.example.baston105;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class EditTreatment extends Activity {
    DBController3 controller = new DBController3(this);
    EditText date,medicine,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_treatment);
        date = (EditText) findViewById(R.id.addDateEditText);
        medicine = (EditText) findViewById(R.id.addMedicineEditText);
        method = (EditText) findViewById(R.id.addMethodEditText);


        try{
            Intent iiii = getIntent();
            String hastaid = iiii.getStringExtra("hastaid");
            HashMap<String,String> treatmentliste = controller.getTreatmentInfo(hastaid);
            if(treatmentliste.size() != 0){
                date.setText(treatmentliste.get("date"));
                medicine.setText(treatmentliste.get("medicine"));
                method.setText(treatmentliste.get("method"));



            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void goMainpage(View view){
        Intent i = new Intent(getApplicationContext(),ExtraTreatment.class);
        startActivity(i);
    }

    public void editTreatment(View view){
        HashMap<String,String> queryValue = new HashMap<String,String>();
        date = (EditText) findViewById(R.id.addDateEditText);
        medicine = (EditText) findViewById(R.id.addMedicineEditText);
        method = (EditText) findViewById(R.id.addMethodEditText);
        Intent i = getIntent();
        String hastaid = i.getStringExtra("hastaid");
        queryValue.put("id",hastaid);
        queryValue.put("date",date.getText().toString());
        queryValue.put("medicine",medicine.getText().toString());
        queryValue.put("method",method.getText().toString());
        controller.edittreatment(queryValue);
        goMainpage(view);

    }

    public void deleteTreatment(View view){
        Intent ii = getIntent();
        String hastaid = ii.getStringExtra("hastaid");
        controller.deletetreatment(hastaid);
        goMainpage(view);
    }
}