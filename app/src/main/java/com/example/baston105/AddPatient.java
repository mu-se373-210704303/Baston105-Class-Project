package com.example.baston105;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.VideoView;

import java.util.HashMap;

public class AddPatient extends Activity {
    DBController controller = new DBController(this);
    EditText patientname,patientsurname,patientid,patientgender,patientage,patienthistory,patientdiseas,patienttreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        patientname = (EditText) findViewById(R.id.addNameEditText);
        patientsurname = (EditText) findViewById(R.id.addSurnameEditText);
        patientid = (EditText) findViewById(R.id.addIdEditText);
        patientgender = (EditText) findViewById(R.id.addGenderEditText);
        patientage = (EditText) findViewById(R.id.addAgeEditText);
        patienthistory = (EditText) findViewById(R.id.addPatientsHistoryEditText);
        patientdiseas = (EditText) findViewById(R.id.addCronicalDiseasEditText);
        patienttreatment = (EditText) findViewById(R.id.addTreatmentEditText);


    }
    public void newPatientAdd(View view){
        HashMap<String,String> query = new HashMap<String,String>();
        query.put("patientName", patientname.getText().toString());
        query.put("patientSurname", patientsurname.getText().toString());
        query.put("patientId", patientid.getText().toString());
        query.put("patientGender", patientgender.getText().toString());
        query.put("patientAge", patientage.getText().toString());
        query.put("patientHistory", patienthistory.getText().toString());
        query.put("patientsCronicalDiseas", patientdiseas.getText().toString());
        query.put("patientTreatments", patienttreatment.getText().toString());
        controller.addpatient(query);
        goMainPage(view);
    }
    public void goMainPage(View view){
        Intent i = new Intent(getApplicationContext(),PatientList.class);
        startActivity(i);
    }
}