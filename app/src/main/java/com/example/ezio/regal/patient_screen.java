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
import java.util.HashMap;
import java.util.List;

public class patient_screen extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvep);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Tablet 1");
        listDataHeader.add("Tablet 2");
        listDataHeader.add("Tablet 3");

        // Adding child data
        List<String> tablet1 = new ArrayList<String>();
       tablet1.add("\nName\ninterval\ncolor");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("\n" +
                "Name\n" +
                "interval\n" +
                "color");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("\n" +
                "Name\n" +
                "interval\n" +
                "color");

        listDataChild.put(listDataHeader.get(0), tablet1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
