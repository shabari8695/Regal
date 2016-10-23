package com.example.ezio.regal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class login_screen extends AppCompatActivity {
    //JSONObject pass_to_intend;
    EditText email;
    EditText passowrd;
    Button login;
    TextView signup;
    TextView display_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        email=(EditText)findViewById(R.id.email);
        passowrd=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        signup=(TextView)findViewById(R.id.sign_up);
        display_error=(TextView)findViewById(R.id.display_error);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FetchData fwd=new FetchData();
                fwd.execute(email.getText().toString(),passowrd.getText().toString());

            }
        });

        //handle onClick for sign up
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.sign_up");
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        email.setText("");passowrd.setText("");

    }


    public class FetchData extends AsyncTask<String,Void,Integer> {
        JSONObject jsonObject;

        protected void onPostExecute(Integer param){
            Log.v(""," request completed");
            if(param==1) {
                Intent intent = new Intent("android.intent.action.doctor_main_screen");
                intent.putExtra("doc_email",email.getText().toString());
                startActivity(intent);
                finish();
            }
            else if(param==0){
                Intent intent=new Intent("android.intent.action.patient_screen");
                intent.putExtra("patient_email",email.getText().toString());
                startActivity(intent);
                finish();
            }
            else{
                display_error.setText("Invalid E-Mail address and Password");
            }

        }

        @Override
        protected Integer doInBackground(String... string){
            if(string.length==0){
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

                jsonObject=new JSONObject();
                jsonObject.put("email",string[0]);
                jsonObject.put("password",string[1]);

                out=urlConnection.getOutputStream();
                OutputStreamWriter ows=new OutputStreamWriter(out,"UTF-8");
                ows.write(jsonObject.toString());
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

                JSONObject jobj=new JSONObject(buffer.toString());
                String success=jobj.getString("success");
                String doctor=jobj.getString("doctor");
                //pass_to_intend=jobj;

                if(success.equals("true")){
                    if(doctor.equals("true"))
                        return 1;
                    else if(doctor.equals("false"))
                        return 0;
                    else
                        return -1;
                }
                else
                    return -1;


            }catch (MalformedURLException e){
                Log.v("","malformedurl");

            }catch (IOException e){
                Log.v("","ioexception");
            }catch(JSONException e){
                Log.v("","jsonexception");
            }finally {
                urlConnection.disconnect();
            }
            return -1;
        }

    }
}
