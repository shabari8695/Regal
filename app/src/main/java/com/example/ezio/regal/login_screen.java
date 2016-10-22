package com.example.ezio.regal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login_screen extends AppCompatActivity {

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

        //handle onClick for login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //write code to get userid and password form remote database


                //check for login details and set sharedPreference file "regal"
                String user_email=email.getText().toString();
                String user_password=passowrd.getText().toString();
                if(user_email.equals("") || user_password.equals(""))
                    display_error.setText("Enter E-Mail address and Password");
                //temporary test
                else if(user_email.equals("regal")&& user_password.equals("regal")){
                    Intent intent=new Intent("android.intent.action.patient_screen");
                    startActivity(intent);
                    finish();
                }

                else if(user_email.equals("embassy")&& user_password.equals("embassy")){
                    Intent intent=new Intent("android.intent.action.doctor_main_screen");
                    startActivity(intent);
                    finish();
                }
                else {
                    display_error.setText("invalid email or password");

                }
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
}
