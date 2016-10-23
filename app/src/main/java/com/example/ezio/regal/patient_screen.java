package com.example.ezio.regal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
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

public class patient_screen extends AppCompatActivity {
    JSONArray jarray;
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

        FetchData fwd = new FetchData();
        fwd.execute("hwllo");
    }




    public class FetchData extends AsyncTask<String,Void,JSONArray> {
        protected void onPostExecute(JSONArray parm){
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();
            try {
                for (int i = 0; i < parm.length(); i++) {
                    JSONObject jobj = parm.getJSONObject(i);
                    listDataHeader.add(jobj.getString("name"));
                    List<String>temp=new ArrayList<String>();
                    temp.add("Name: "+jobj.get("name").toString()+"\nDosage: "+jobj.getInt("dosage")+"\nzColor:"+jobj.getString("color"));
                    listDataChild.put(listDataHeader.get(i),temp);
                }
            }catch (JSONException e){

            }
            listAdapter = new ExpandableListAdapter(patient_screen.this, listDataHeader, listDataChild);

            // setting list adapter
            expListView.setAdapter(listAdapter);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
            notificationIntent.addCategory("android.intent.category.DEFAULT");

            PendingIntent broadcast = PendingIntent.getBroadcast(patient_screen.this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 10);
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
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
