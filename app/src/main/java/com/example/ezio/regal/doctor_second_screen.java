package com.example.ezio.regal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class doctor_second_screen extends AppCompatActivity {
    JSONArray jarray;
    ArrayList<String> list;
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_second_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        l=(ListView)findViewById(R.id.doctors_patient_list);

        FetchData fwd = new FetchData();
        fwd.execute("hwllo");

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
    public class FetchData extends AsyncTask<String,Void,JSONArray> {
        protected void onPostExecute(JSONArray parm){
            list=new ArrayList<String>();
            try {
                for (int i = 0; i < parm.length(); i++) {
                    JSONObject temp = parm.getJSONObject(i);
                        list.add(temp.getString("name"));
                }
            }catch (JSONException e){

            }
            final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(doctor_second_screen.this,R.layout.list_item,list);
            l.setAdapter(arrayAdapter);
        }
        @Override
        protected JSONArray doInBackground(String... strings) {
            if (strings == null) {
                return null;
            }
            String url_string="http://cfd-embassy4051860.cloudapp.net/medicine";
            URL url;
            HttpURLConnection urlConnection=null;
            try {

                url=new URL(url_string);
                urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.setRequestProperty("cfd-embassy","$2b$12$BYDebu0UIJwb05N8BfwPEOZDJDxJGHrAx7JzqIC6NLP0GUrn1hBmO");
                urlConnection.setRequestMethod("GET");



                urlConnection.connect();

                Log.v("bb","connection Established");

                InputStream inputStream=urlConnection.getInputStream();

                StringBuffer buffer=new StringBuffer();
                if(inputStream==null){
                    return  null;
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while((line=br.readLine())!=null){
                    buffer.append(line+"\n");
                    Log.v("jsonoutput",line);
                }
                if(buffer.length()==0){
                    return null;
                }
                jarray=new JSONArray(buffer.toString());
                return jarray;

            }catch (MalformedURLException e) {
                Log.v("res","a");
            }catch (IOException e){
                Log.v("resp","b");
            }catch(JSONException e){
                Log.v("respone","c");
            }
            return null;
        }

    }

}
