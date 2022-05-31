package com.example.baston105;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.HashMap;

public class DBController2 extends SQLiteOpenHelper {
    private static final String DB = "books";
    private static final int version = 1;
    public DBController2(Context c) {
        super(c, DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS book(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, nurse TEXT, events TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS book");

    }

    //İD YE GÖRE olay
    public HashMap<String,String> getBookInfo(String id){
        HashMap<String,String> BookList = new HashMap<String,String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM book WHERE id = "+id+"";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()){
            do{
                //çalışmazsa buraya diğerlerini de ekle
                BookList.put("id",c.getString(0));
                BookList.put("date",c.getString(1));
                BookList.put("nurse",c.getString(2));
                BookList.put("events",c.getString(3));


            }while(c.moveToNext());
        }
        return BookList;
    }
    //yeni olay ekleme işlemi
    public void addbook(HashMap<String,String>QueryValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date",QueryValue.get("date"));
        values.put("nurse",QueryValue.get("nurse"));
        values.put("events",QueryValue.get("events"));
        db.insert("book",null,values);
        db.close();

    }
    //olay güncelleme
    public int editbook(HashMap<String,String>queryvalue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date",queryvalue.get("date"));
        values.put("nurse",queryvalue.get("nurse"));
        values.put("events",queryvalue.get("events"));


        return db.update("book",values,"id" + "=?",new String[]{queryvalue.get("id")});
    }

    //olay silme işlemi
    public void deletebook(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM book WHERE id = '"+id+"'");
    }
    //tüm olaylar listesi
    public ArrayList<HashMap<String,String>> allbooks(){
        ArrayList<HashMap<String,String>> booklist;
        booklist = new ArrayList<HashMap<String,String>>();
        String query = "SELECT * FROM book";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("id",cursor.getString(0));
                map.put("date",cursor.getString(1));
                map.put("nurse",cursor.getString(2));
                map.put("events",cursor.getString(3));
                booklist.add(map);

            }while (cursor.moveToNext());
        }
        return booklist;
    }

}
