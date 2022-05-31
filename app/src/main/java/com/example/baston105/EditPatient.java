package com.example.baston105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class EditPatient extends Activity {
    DBController controller = new DBController(this);
    EditText patientname,patientsurname,patientid,patientgender,patientage,patienthistory,patientdiseas,patienttreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        patientname = (EditText) findViewById(R.id.addNameEditText);
        patientsurname = (EditText) findViewById(R.id.addSurnameEditText);
        patientid = (EditText) findViewById(R.id.addIdEditText);
        patientgender = (EditText) findViewById(R.id.addGenderEditText);
        patientage = (EditText) findViewById(R.id.addAgeEditText);
        patienthistory = (EditText) findViewById(R.id.addPatientsHistoryEditText);
        patientdiseas = (EditText) findViewById(R.id.addCronicalDiseasEditText);
        patienttreatment = (EditText) findViewById(R.id.addTreatmentEditText);

        try{
            Intent iiii = getIntent();
            String hastaid = iiii.getStringExtra("hastaid");
            HashMap<String,String> hastaliste = controller.getPatientInfo(hastaid);
            if(hastaliste.size() != 0){
                patientname.setText(hastaliste.get("patientName"));
                patientsurname.setText(hastaliste.get("patientSurname"));
                patientid.setText(hastaliste.get("patientId"));
                patientgender.setText(hastaliste.get("patientGender"));
                patientage.setText(hastaliste.get("patientAge"));
                patienthistory.setText(hastaliste.get("patientHistory"));
                patientdiseas.setText(hastaliste.get("patientsCronicalDiseas"));
                patienttreatment.setText(hastaliste.get("patientTreatments"));


            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void goMainpage(View view){
        Intent i = new Intent(getApplicationContext(),PatientList.class);
        startActivity(i);
    }

    public void editPatient(View view){
        HashMap<String,String> queryValue = new HashMap<String,String>();
        patientname = (EditText) findViewById(R.id.addNameEditText);
        patientsurname = (EditText) findViewById(R.id.addSurnameEditText);
        patientid = (EditText) findViewById(R.id.addIdEditText);
        patientgender = (EditText) findViewById(R.id.addGenderEditText);
        patientage = (EditText) findViewById(R.id.addAgeEditText);
        patienthistory = (EditText) findViewById(R.id.addPatientsHistoryEditText);
        patientdiseas = (EditText) findViewById(R.id.addCronicalDiseasEditText);
        patienttreatment = (EditText) findViewById(R.id.addTreatmentEditText);
        Intent i = getIntent();
        String hastaid = i.getStringExtra("hastaid");
        queryValue.put("id",hastaid);
        queryValue.put("patientName",patientname.getText().toString());
        queryValue.put("patientSurname",patientsurname.getText().toString());
        queryValue.put("patientId",patientid.getText().toString());
        queryValue.put("patientGender",patientgender.getText().toString());
        queryValue.put("patientAge",patientage.getText().toString());
        queryValue.put("patientHistory",patienthistory.getText().toString());
        queryValue.put("patientsCronicalDiseas",patientdiseas.getText().toString());
        queryValue.put("patientTreatments",patienttreatment.getText().toString());
        controller.editpatient(queryValue);
        goMainpage(view);

    }

    public void deletePatient(View view){
        Intent ii = getIntent();
        String hastaid = ii.getStringExtra("hastaid");
        controller.deletepatient(hastaid);
        goMainpage(view);
    }
}