package com.example.ezio.regal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class doctor_second_screen extends AppCompatActivity {
    String [] current_prescription={"tablet1","tablet2","tablet3","tablet4","tablet6","tablet7"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_second_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        ListView l=(ListView)findViewById(R.id.doctors_patient_list);
        ArrayList<String> list=new ArrayList<String>();
        for(int i=0;i<current_prescription.length;i++){
            list.add(current_prescription[i]);
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.list_item,list);
        l.setAdapter(arrayAdapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String val=adapterView.getItemAtPosition(i).toString();
                Intent intent =new Intent("android.intent.action.edit_prescription");
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent("android.intent.action.edit_prescription");
                startActivity(intent);
            }
        });


    }

}
