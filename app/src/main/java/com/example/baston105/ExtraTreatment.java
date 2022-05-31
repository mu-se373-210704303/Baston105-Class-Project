package com.example.baston105;

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

public class ExtraTreatment extends ListActivity {
    DBController3 controller = new DBController3(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_treatment);

        ArrayList<HashMap<String,String>> treatmentlist = controller.alltreatments();
        if(treatmentlist.size()!=0){
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(ExtraTreatment.this,treatmentlist,R.layout.treatment_info,new String[]{"id","medicine","method"},new int[]{R.id.hastaid,R.id.hastaadi,R.id.hastasoyadi});
            setListAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LinearLayout listitem = (LinearLayout)view;
                    TextView hastaid = (TextView) listitem.findViewById(R.id.hastaid);
                    String dId = hastaid.getText().toString();
                    Intent i = new Intent(getApplicationContext(), EditTreatment.class);
                    i.putExtra("hastaid",dId);
                    startActivity(i);
                }
            });
        }
    }

    public void newTreatmentAdd(View view){
        Intent ii = new Intent(getApplicationContext(),AddTreatment.class);
        startActivity(ii);
    }
}