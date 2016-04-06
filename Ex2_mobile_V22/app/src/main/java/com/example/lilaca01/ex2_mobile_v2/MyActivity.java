package com.example.lilaca01.ex2_mobile_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.lilaca01.ex2_mobile.R;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by lilaca01 on 16/03/2016.
 */
public class MyActivity extends Activity implements
        View.OnClickListener {

    private Button OK;
    private Button CANCEL;

    /**
     * @param datePicker
     * @return a java.util.Date
     */
    public static Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    @Override
    public void onCreate(Bundle unused) {
        super.onCreate(unused);
        setContentView(R.layout.popwindow);
        setTitle("Add New Task");
        Intent intent = getIntent();
        OK = (Button) findViewById(R.id.ButtonOk);
        CANCEL = (Button) findViewById(R.id.ButtonCancel);
        OK.setOnClickListener(this);
        CANCEL.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        switch (v.getId()) {
            case R.id.ButtonCancel:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            case R.id.ButtonOk:
                DatePicker dateP = (DatePicker) findViewById(R.id.datePicker);
                EditText textV = (EditText) findViewById(R.id.editTask);
                Date date = getDateFromDatePicker(dateP);
                String task = textV.getText().toString();
                intent.putExtra("DATE", date);
                intent.putExtra("TASK", task);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                finish();
                break;
        }
    }
}
