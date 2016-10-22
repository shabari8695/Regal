package com.example.ezio.regal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class patient_screen extends AppCompatActivity {
    TextView t;
    String previous_selected_item="";
    String active_prescriptions[]={"tablet1","tablet2","tablet3","tablet4","tablet6","tablet7"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);
        t=(TextView)findViewById(R.id.tablet_details);
        ListView l=(ListView)findViewById(R.id.active_prescription);
        ArrayList<String>list=new ArrayList<String>();
        for(int i=0;i<active_prescriptions.length;i++){
            list.add(active_prescriptions[i]);
        }
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,R.layout.list_item,list);
        l.setAdapter(arrayAdapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                t.setText("");
               String val=adapterView.getItemAtPosition(i).toString();
                String tablet_details="Name: "+val+"\nDosage: whatever"+""+"\n";
                t.setText(tablet_details);
            }
        });

    }
}
