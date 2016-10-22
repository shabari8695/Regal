package com.example.ezio.regal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class doctor_main_screen extends AppCompatActivity {
    String val;
    String patient_list[]={"patient1","patient2","patient3","patient4","patient5","patient6"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main_screen);

        ListView l=(ListView)findViewById(R.id.patient_list);
        final ArrayList<String> list=new ArrayList<String>();
        for(int i=0;i<patient_list.length;i++){
            list.add(patient_list[i]);
        }
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.list_item,list);
        l.setAdapter(arrayAdapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String val=adapterView.getItemAtPosition(i).toString();
                Intent intent=new Intent("android.intent.action.doctor_second_screen");
                startActivity(intent);
            }
        });


    }
}
