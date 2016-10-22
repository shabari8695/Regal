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
        frequency=(EditText)findViewById(R.id.edit_prescription_frequency);
        update=(Button)findViewById(R.id.edit_prescription_update);

        calendar= java.util.Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

       /* start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
                start_date.setText(new StringBuilder().append(calendar.DATE).append("/")
                        .append(calendar.MONTH).append("/").append(calendar.YEAR));
            }
        });*/

    }
   /* @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            calendar.set(arg1,arg2,arg3);
        }
    };*/
}
