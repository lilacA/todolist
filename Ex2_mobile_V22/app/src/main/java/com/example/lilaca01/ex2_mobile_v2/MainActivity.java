package com.example.lilaca01.ex2_mobile_v2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lilaca01.ex2_mobile.R;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int TASK_ACTIVITY = 1;
    public static ArrayList<ListItem> arrayOfItems = new ArrayList<ListItem>();
    CustomListAdapter listAdapter;
    ListView ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView = (ListView) findViewById(R.id.listView);
        listAdapter = new CustomListAdapter(MainActivity.this, R.layout.list_item, arrayOfItems);
        ListView.setAdapter(listAdapter);
        registerForContextMenu(ListView);
    }

    private void addToList(Date itemDate, String itemTaskString) {
        if (itemTaskString.equals("")) {
            return;
        }
        ListItem item = new ListItem(itemDate, itemTaskString);
        arrayOfItems.add(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.item:
                Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                startActivityForResult(intent, TASK_ACTIVITY);
                listAdapter.notifyDataSetChanged();
        }
        return true;
    }


    @Override
    public void onActivityResult(int requestNum, int resultNum, Intent data) {
        if (requestNum != TASK_ACTIVITY) { //if its not the expected activity
            return;
        }
        if (resultNum == RESULT_OK) { //check if the result is valid

            Date itemDate = (Date) data.getSerializableExtra("DATE");
            String itemTaskString = data.getStringExtra("TASK");

            //add to the list
            addToList(itemDate, itemTaskString);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        int i = 0;
        if (v.getId() == R.id.listView) {
//            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//            String infoText= arrListTodolist.get(info.position).getInfo();
//            menu.setHeaderTitle(infoText);


            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            String infoText = arrayOfItems.get(info.position).getTask();
            menu.setHeaderTitle(arrayOfItems.get(info.position).getTask());
            String[] menuItems = getResources().getStringArray(R.array.str_array);
            for (i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
            if (infoText.startsWith("Call ")) {
                menu.add(Menu.NONE, i, i, infoText);
            }

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.str_array);

        String listItemName = arrayOfItems.get(info.position).getTask();

        if (menuItemIndex >= menuItems.length) { // task of calling

            String number = arrayOfItems.get(info.position).getTask().substring(5);
            Uri call = Uri.parse("tel:" + number);
            Intent surf = new Intent(Intent.ACTION_DIAL, call);
            startActivity(surf);
            return false;

        }
        String menuItemName = menuItems[menuItemIndex];
        if (menuItemName.equals("DELETE")) {
            arrayOfItems.remove(arrayOfItems.get(info.position));
            listAdapter.notifyDataSetChanged();
        }
        return true;
    }
}
