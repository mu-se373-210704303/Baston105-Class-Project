package com.example.baston105;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.HashMap;

public class DBController3 extends SQLiteOpenHelper {
    private static final String DB = "treatments";
    private static final int version = 1;
    public DBController3(Context c) {
        super(c, DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS treatment(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, medicine TEXT, method TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS treatment");

    }

    //İD YE GÖRE olay
    public HashMap<String,String> getTreatmentInfo(String id){
        HashMap<String,String> TreatmentList = new HashMap<String,String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM treatment WHERE id = "+id+"";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()){
            do{
                //çalışmazsa buraya diğerlerini de ekle
                TreatmentList.put("id",c.getString(0));
                TreatmentList.put("date",c.getString(1));
                TreatmentList.put("medicine",c.getString(2));
                TreatmentList.put("method",c.getString(3));


            }while(c.moveToNext());
        }
        return TreatmentList;
    }
    //yeni olay ekleme işlemi
    public void addtreatment(HashMap<String,String>QueryValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date",QueryValue.get("date"));
        values.put("medicine",QueryValue.get("medicine"));
        values.put("method",QueryValue.get("method"));
        db.insert("treatment",null,values);
        db.close();

    }
    //olay güncelleme
    public int edittreatment(HashMap<String,String>queryvalue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date",queryvalue.get("date"));
        values.put("medicine",queryvalue.get("medicine"));
        values.put("method",queryvalue.get("method"));


        return db.update("treatment",values,"id" + "=?",new String[]{queryvalue.get("id")});
    }

    //olay silme işlemi
    public void deletetreatment(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM treatment WHERE id = '"+id+"'");
    }
    //tüm olaylar listesi
    public ArrayList<HashMap<String,String>> alltreatments(){
        ArrayList<HashMap<String,String>> treatmentlist;
        treatmentlist = new ArrayList<HashMap<String,String>>();
        String query = "SELECT * FROM treatment";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("id",cursor.getString(0));
                map.put("date",cursor.getString(1));
                map.put("medicine",cursor.getString(2));
                map.put("method",cursor.getString(3));
                treatmentlist.add(map);

            }while (cursor.moveToNext());
        }
        return treatmentlist;
    }

}

