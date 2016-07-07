package com.example.zver.myadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String log = "loh";
    ListView listName;
    Button delete;
    Button checkAll;
    ImageButton add;
    TextView text;
    ArrayList<Item> arrayName = new ArrayList<Item>();
    AdapterItem adapter;
    int indexItemSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listName = (ListView) findViewById(R.id.listName);
        delete = (Button) findViewById(R.id.deleteCheck);
        checkAll = (Button) findViewById(R.id.checkAll);
        add = (ImageButton) findViewById(R.id.add);
        text = (TextView) findViewById(R.id.text);


        registerForContextMenu(listName);
        adapter = new AdapterItem(this, arrayName, listName);
        listName.setAdapter(adapter);


        ///ADD ELEMENT IN LIST
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayName.add(new Item(text.getText().toString(), false));
                text.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        ///DELETE ELEMENT ON SELECT ITEMS
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = arrayName.size() - 1; i >= 0; i--) {
                    if (arrayName.get(i).isCheckStatus()) {
                        arrayName.remove(i);
                    }
                }
                listName.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        ///CHECK ALL
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = arrayName.size() - 1; i >= 0; i--) {
                    arrayName.get(i).setCheckStatus(true);
                }
                listName.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        ///CONTEXT MENU "DELETE ONE ITEM"
        listName.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(Menu.NONE, 0, 0, "Delete");
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        int menuItemIndex = item.getItemId();
        if (menuItemIndex == 0) {
            arrayName.remove(info.position);
            adapter.notifyDataSetChanged();
        }

        return true;
    }


}
