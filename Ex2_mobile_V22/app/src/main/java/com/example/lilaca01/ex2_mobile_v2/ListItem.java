package com.example.lilaca01.ex2_mobile_v2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lilaca01 on 16/03/2016.
 */
public class ListItem {
    private static int idCreator = 0;
    private Date _date;
    private String _task;
    private int _id;


    public ListItem(Date date, String task) {
        _date = date;
        _task = task;
        _id = idCreator++;
    }

    public String getStringDate() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(_date);
    }

    public int getId() {
        return _id;
    }

    public String getTask() {
        return _task;
    }

    public Date getDate() {
        return _date;
    }

}
