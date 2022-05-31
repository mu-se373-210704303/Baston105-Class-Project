package com.example.baston105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class GuardBook extends ListActivity {
    DBController2 controller = new DBController2(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_book);

        ArrayList<HashMap<String,String>> booklist = controller.allbooks();
        if(booklist.size()!=0){
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(GuardBook.this,booklist,R.layout.book_info,new String[]{"id","date","nurse"},new int[]{R.id.hastaid,R.id.hastaadi,R.id.hastasoyadi});
            setListAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LinearLayout listitem = (LinearLayout)view;
                    TextView hastaid = (TextView) listitem.findViewById(R.id.hastaid);
                    String dId = hastaid.getText().toString();
                    Intent i = new Intent(getApplicationContext(), EditEvent.class);
                    i.putExtra("hastaid",dId);
                    startActivity(i);
                }
            });
        }
    }

    public void newBookAdd(View view){
        Intent ii = new Intent(getApplicationContext(),AddEvent.class);
        startActivity(ii);
    }
}