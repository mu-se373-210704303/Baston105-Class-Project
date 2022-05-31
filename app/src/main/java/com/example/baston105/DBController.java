package com.example.baston105;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {
    private static final String DB = "patients";
    private static final int version = 1;
    public DBController(Context c) {
        super(c, DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS patients(id INTEGER PRIMARY KEY AUTOINCREMENT, patientName TEXT, patientSurname TEXT, patientId TEXT, patientGender TEXT, patientAge TEXT, patientHistory TEXT, patientsCronicalDiseas TEXT, patientTreatments TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS patients");

    }

    //İD YE GÖRE HASTA ADI
    public HashMap<String,String> getPatientInfo(String id){
        HashMap<String,String> PatientList = new HashMap<String,String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM patients WHERE id = "+id+"";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()){
            do{
                //çalışmazsa buraya diğerlerini de ekle
                PatientList.put("id",c.getString(0));
                PatientList.put("patientName",c.getString(1));
                PatientList.put("patientSurname",c.getString(2));
                PatientList.put("patientId",c.getString(3));
                PatientList.put("patientGender",c.getString(4));
                PatientList.put("patientAge",c.getString(5));
                PatientList.put("patientHistory",c.getString(6));
                PatientList.put("patientsCronicalDiseas",c.getString(7));
                PatientList.put("patientTreatments",c.getString(8));

            }while(c.moveToNext());
        }
        return PatientList;
    }
    //yeni hasta ekleme işlemi
    public void addpatient(HashMap<String,String>QueryValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("patientName",QueryValue.get("patientName"));
        values.put("patientSurname",QueryValue.get("patientSurname"));
        values.put("patientId",QueryValue.get("patientId"));
        values.put("patientGender",QueryValue.get("patientGender"));
        values.put("patientAge",QueryValue.get("patientAge"));
        values.put("patientHistory",QueryValue.get("patientHistory"));
        values.put("patientsCronicalDiseas",QueryValue.get("patientsCronicalDiseas"));
        values.put("patientTreatments",QueryValue.get("patientTreatments"));
        db.insert("patients",null,values);
        db.close();

    }
    //hasta güncelleme
    public int editpatient(HashMap<String,String>queryvalue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("patientName",queryvalue.get("patientName"));
        values.put("patientSurname",queryvalue.get("patientSurname"));
        values.put("patientId",queryvalue.get("patientId"));
        values.put("patientGender",queryvalue.get("patientGender"));
        values.put("patientAge",queryvalue.get("patientAge"));
        values.put("patientHistory",queryvalue.get("patientHistory"));
        values.put("patientsCronicalDiseas",queryvalue.get("patientsCronicalDiseas"));
        values.put("patientTreatments",queryvalue.get("patientTreatments"));

        return db.update("patients",values,"id" + "=?",new String[]{queryvalue.get("id")});
    }

    //hasta silme işlemi
    public void deletepatient(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM patients WHERE id = '"+id+"'");
    }
    //tüm hasta listesi
    public ArrayList<HashMap<String,String>> allpatients(){
        ArrayList<HashMap<String,String>> patientlist;
        patientlist = new ArrayList<HashMap<String,String>>();
        String query = "SELECT * FROM patients";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("id",cursor.getString(0));
                map.put("patientName",cursor.getString(1));
                map.put("patientSurname",cursor.getString(2));
                map.put("patientId",cursor.getString(3));
                map.put("patientGender",cursor.getString(4));
                map.put("patientAge",cursor.getString(5));
                map.put("patientHistory",cursor.getString(6));
                map.put("patientsCronicalDiseas",cursor.getString(7));
                map.put("patientTreatments",cursor.getString(8));
                patientlist.add(map);

            }while (cursor.moveToNext());
        }
        return patientlist;
    }

}

