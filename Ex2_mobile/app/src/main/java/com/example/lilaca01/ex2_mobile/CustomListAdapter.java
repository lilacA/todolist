package com.example.lilaca01.ex2_mobile;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lilaca01 on 09/03/2016.
 */
class CustomListAdapter extends ArrayAdapter {

    private Context mContext;
    private int id;
    private List<String> items ;
    //private String item;

    public CustomListAdapter(Context context, int textViewResourceId , List<String> list )
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        Object p = getItem(position);

        TextView text = (TextView) mView.findViewById(R.id.textView);

        String pos = items.get(position);

        if(pos != null)
        {
           if(position%2 == 0){
               text.setTextColor(Color.RED);
               text.setText(items.get(position));
           }else{
               text.setTextColor(Color.BLUE);
               text.setText(items.get(position));
           }
        }
        return mView;
    }

}