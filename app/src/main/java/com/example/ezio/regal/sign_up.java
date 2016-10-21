package com.example.ezio.regal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sign_up extends AppCompatActivity {
    TextView display_sign_up_error;
    EditText first_name,last_name,email,password,confirm_password;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        display_sign_up_error=(TextView)findViewById(R.id.display_sign_up_errors);
        first_name=(EditText)findViewById(R.id.sign_up_first_name);
        last_name=(EditText)findViewById(R.id.sign_up_last_name);
        email=(EditText)findViewById(R.id.sign_up_email);
        password=(EditText)findViewById(R.id.sign_up_password);
        confirm_password=(EditText)findViewById(R.id.sign_up_confirm_password);
        submit=(Button)findViewById(R.id.sign_up_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(first_name.getText().toString().equals("")||last_name.getText().toString().equals("")||email.getText().toString().equals("")||password.getText().toString().equals("")||confirm_password.getText().toString().equals(""))
                    display_sign_up_error.setText("Form Incomplete");
                else if(!password.getText().toString().equals(confirm_password.getText().toString()))
                    display_sign_up_error.setText("Password do not match");

                //check if email id is right

                //check is password is only number+digit_special characters and no space

                else{
                    //add new user details to remote database
                    Intent intent=new Intent("android.intent.action.login_screen");
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
