package com.example.ezio.regal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class doctor_main_screen extends AppCompatActivity {
    JSONArray jarray;
    JSONObject jobj;
    Button add,update;
    EditText patient_email;
    String doctor_email="";
    String val;
    String patient_list[]={};
    ArrayList<String> list;
    ListView l;
    HashMap<String,String>key_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main_screen);
        l=(ListView)findViewById(R.id.patient_list);

        //getting jsonObject
        Intent intent=getIntent();doctor_email=intent.getStringExtra("doc_email");
        FetchData fwd = new FetchData();
        fwd.execute(doctor_email);




        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                val=adapterView.getItemAtPosition(i).toString();
                Intent intent =new Intent("android.intent.action.doctor_second_screen");
                startActivity(intent);
            }
        });

        add=(Button)findViewById(R.id.doctor_add_paitent);
        update=(Button)findViewById(R.id.doctor_update_list);
        patient_email=(EditText)findViewById(R.id.patient_email);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update.setVisibility(View.VISIBLE);
                patient_email.setVisibility(View.VISIBLE);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String new_user_to_add=patient_email.getText().toString();
              if(!new_user_to_add.equals("")){

              }
            }
        });

    }

    public class FetchData extends AsyncTask<String,Void,JSONArray> {
        protected void onPostExecute(JSONArray parm){
            list=new ArrayList<String>();
            try {
                for (int i = 0; i < parm.length(); i++) {
                    JSONObject temp = parm.getJSONObject(i);
                    if (!temp.getBoolean("doctor")) {
                        list.add(temp.getString("name"));
                    }
                }
            }catch (JSONException e){

            }
            final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(doctor_main_screen.this,R.layout.list_item,list);
            l.setAdapter(arrayAdapter);
        }
        @Override
        protected JSONArray doInBackground(String... strings) {
            if (strings == null) {
                return null;
            }
            String url_string="http://cfd-embassy4051860.cloudapp.net/user";
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
