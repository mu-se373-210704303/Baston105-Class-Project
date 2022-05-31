package com.example.baston105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }
    public void goPatientList(View view){
        Intent i = new Intent(getApplicationContext(),PatientList.class);
        startActivity(i);
    }

    public void goGuardBook(View view){
        Intent i = new Intent(getApplicationContext(),GuardBook.class);
        startActivity(i);
    }
    public void goExtraTreatment(View view){
        Intent i = new Intent(getApplicationContext(),ExtraTreatment.class);
        startActivity(i);
    }





}