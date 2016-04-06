package com.example.lilaca01.ex2_mobile;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomListAdapter listAdapter;
    public static ArrayList<String> arrayOfItems = new ArrayList<String>();
    ListView ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView = (ListView) findViewById(R.id.listView);
        listAdapter = new CustomListAdapter(MainActivity.this , R.layout.custom_list , arrayOfItems);
        ListView.setAdapter(listAdapter);
        registerForContextMenu(ListView);
        myDataBase = new todo_db(this);
    }

    private  void addToList(){
        EditText toAdd = (EditText) findViewById(R.id.editText);
        String newItemString = toAdd.getText().toString();
        if(newItemString.equals("")){
            return;
        }
        arrayOfItems.add(newItemString);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch ((item.getItemId())){
            case  R.id.item:
                addToList();
                listAdapter.notifyDataSetChanged();

        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listView) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(arrayOfItems.get(info.position));
            String[] menuItems = getResources().getStringArray(R.array.str_array);
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.str_array);
        String menuItemName = menuItems[menuItemIndex];
        String listItemName = arrayOfItems.get(info.position);

        if(menuItemName.equals("DELETE")) {
            for(int i=0; i < arrayOfItems.size(); i++){
                if(arrayOfItems.get(i).equals(listItemName)){
                    arrayOfItems.remove(i);
                }
            }
            listAdapter.notifyDataSetChanged();
        }
        return true;
    }
}
