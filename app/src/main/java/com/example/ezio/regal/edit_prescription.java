package com.example.ezio.regal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;

public class edit_prescription extends AppCompatActivity {
    EditText name,dosage,start_date,end_date,interval;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prescription);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        name=(EditText)findViewById(R.id.edit_prescription_tablet_name);
        dosage=(EditText)findViewById(R.id.edit_prescription_dosage);
        start_date=(EditText)findViewById(R.id.edit_prescription_start_date);
        end_date=(EditText)findViewById(R.id.edit_prescription_end_date);
        interval=(EditText)findViewById(R.id.edit_prescription_interval);
        update=(Button)findViewById(R.id.edit_prescription_update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FetchData fwd = new FetchData();
                fwd.execute(name.getText().toString(), dosage.getText().toString(), start_date.getText().toString(), end_date.getText().toString(),interval.getText().toString());
            }
        });

    }
    public class FetchData extends AsyncTask<String,Void,Integer>{

        protected void onPostExecute(Integer param){
            if(param==1){
                Toast.makeText(edit_prescription.this,"Updation Complete",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(edit_prescription.this,"Updation Incomplete",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Integer doInBackground(String... strings){
            if(strings.length==0){
                return -1;
            }

            String url_string="http://cfd-embassy4051860.cloudapp.net/login";
            URL url;
            HttpURLConnection urlConnection=null;
            OutputStream out;

            try {

                url=new URL(url_string);
                urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setUseCaches(false);
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.setRequestProperty("cfd-embassy","$2b$12$BYDebu0UIJwb05N8BfwPEOZDJDxJGHrAx7JzqIC6NLP0GUrn1hBmO");
                urlConnection.setRequestMethod("POST");


                JSONObject obj = new JSONObject();
                obj.put("name", strings[0]);
                obj.put("dosage", strings[1]);
                obj.put("start_date", strings[2]);
                obj.put("end_date", strings[3]);
                obj.put("interval", strings[4]);

                out=urlConnection.getOutputStream();
                OutputStreamWriter ows=new OutputStreamWriter(out,"UTF-8");
                ows.write(obj.toString());
                ows.flush();
                ows.close();

                urlConnection.connect();

                InputStream inputStream=urlConnection.getInputStream();
                StringBuffer buffer=new StringBuffer();
                if(inputStream==null){
                    return  -1;
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String line="";
                while((line=br.readLine())!=null){
                    buffer.append(line+"\n");
                    Log.v("jsonoutput",line);
                }
                if(buffer.length()==0){
                    return -1;
                }
                else return 1;

            }catch (JSONException e){

            }catch (MalformedURLException e) {

            }catch (IOException e){

            }
            return 0;
        }

    }

}
