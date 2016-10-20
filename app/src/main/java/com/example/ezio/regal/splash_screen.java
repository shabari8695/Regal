package com.example.ezio.regal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        SharedPreferences preferences=getSharedPreferences("regal",MODE_PRIVATE);
        String temp=preferences.getString("email",null);
        if(temp==null){
            Intent intent=new Intent("android.intent.action.login_screen");
            startActivity(intent);
        }
        else{
            Intent intent=new Intent("android.intent.action.main_screen");
            startActivity(intent);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
