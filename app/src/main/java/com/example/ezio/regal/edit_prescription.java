package com.example.ezio.regal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class edit_prescription extends AppCompatActivity {
    EditText name,dosage,start_date,end_date,frequency;
    Button update;
    java.util.Calendar calendar;
    DatePicker datePicker;
    int year, month, day;
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
        frequency=(EditText)findViewById(R.id.edit_prescription_interval);
        update=(Button)findViewById(R.id.edit_prescription_update);

        calendar= java.util.Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);



    }
}
