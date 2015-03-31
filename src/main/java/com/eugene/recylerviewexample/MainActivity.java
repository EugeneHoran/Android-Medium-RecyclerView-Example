package com.eugene.recylerviewexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    Button btnSave;
    EditText etItem;
    RecyclerView recyclerView;

    private void findViewsById() {
        btnSave = (Button) findViewById(R.id.btnSave);
        etItem = (EditText) findViewById(R.id.etItem);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        registerForContextMenu(recyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setAdapter();
        addItemToList();
        listClick();
    }

    LogAdapter logAdapter;
    ArrayList<Log> itemList = new ArrayList<>();

    private void setAdapter() {
        logAdapter = new LogAdapter(itemList, R.layout.log_row);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(logAdapter);
    }

    String strItem = "";

    private void addItemToList() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etItem.getText().toString().equals("")) {
                    strItem = "No Title";
                } else {
                    strItem = etItem.getText().toString();
                }
                Log log = new Log();
                log.setItem(strItem);
                logAdapter.add(log);
                logAdapter.notifyDataSetChanged();
            }
        });
    }

    private void listClick() {
        logAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Log>() {
            @Override
            public void onItemClick(View view, Log log) {
                Toast.makeText(MainActivity.this, log.getItem(), Toast.LENGTH_SHORT).show();
            }
        });
        logAdapter.setOnItemLongClickListener(new OnRecyclerViewLongItemClickListener<Log>() {
            @Override
            public void onItemLongClick(View view, Log log) {
                LogItem.setLog(log);
                openContextMenu(view);
            }
        });
    }

  /*
   NOTE: Below handles the context menu for when the list item is long pressed
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { // Add Items to menu
        if (v.getId() == R.id.recyclerView) {
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int menuItemIndex = item.getItemId(); // context menu list position
        if (menuItemIndex == 0) {
            logAdapter.remove(LogItem.getLog()); // Delete from adapter
            logAdapter.notifyDataSetChanged(); // Update Adapter
        }
        if (menuItemIndex == 1) {
            // Do nothing
        }
        return true;
    }

}
