package com.example.lilaca01.ex2_mobile_v2;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.lilaca01.ex2_mobile.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lilaca01 on 09/03/2016.
 */
class CustomListAdapter extends ArrayAdapter {

    private Context mContext;
    private int id;
    private ArrayList<ListItem> items;
    //private String item;

    public CustomListAdapter(Context context, int textViewResourceId, ArrayList<ListItem> list) {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View mView = v;
        if (mView == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        Object p = getItem(position);

        TextView taskText = (TextView) mView.findViewById(R.id.txtTodoTitle);
        TextView taskDate = (TextView) mView.findViewById(R.id.txtTodoDueDate);
        String pos = String.valueOf(items.get(position));

        if (pos != null) {
            //get current date time with Date()
            Date date = new Date();
            //Check if the date of the task has passed
            if (isTaskPast(date, items.get(position).getDate())) {
                taskText.setTextColor(Color.RED);
                taskDate.setTextColor(Color.RED);

                taskText.setText(items.get(position).getTask());
                taskDate.setText(items.get(position).getStringDate());
            } else {
                taskText.setTextColor(Color.BLUE);
                taskDate.setTextColor(Color.BLUE);
                taskText.setText(items.get(position).getTask());
                taskDate.setText(items.get(position).getStringDate());
            }
        }
        return mView;
    }


    /**
     * This function checks if the task has passed, if it does it returns true
     * else return flase.
     *
     * @param currentDate - the current date
     * @param taskDate    - the date of the task
     * @return true if the task passed else it reurns false
     */
    public boolean isTaskPast(Date currentDate, Date taskDate) {
        int biffBetweenYears = currentDate.getYear() - taskDate.getYear();
        int biffBetweenMonth = currentDate.getMonth() - taskDate.getMonth();
        int biffBetweenDays = currentDate.getDay() - taskDate.getDay();
        if (biffBetweenYears > 0) { // the current year is bigger
            return true;
        }
        if (biffBetweenYears == 0) { //if its the same year
            if (biffBetweenMonth > 0) {// the current mounth is bigger
                return true;
            }
            if (biffBetweenMonth == 0) { //if its the same month
                if (biffBetweenDays > 0) {// the current day is bigger
                    return true;
                }
            }
        }
        return false;
    }

}