package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by cwetzker on 12/4/2017.
 * subGroupActivity looks very similar to MainActivity. The main difference is that the list is full
 * of items instead of categories. Almost all the same functionality exists.
 */

public class subGroupActivity extends Activity {
    ArrayAdapter<Item> adapter;
    ListView listOfItems;
    FloatingActionButton to_camera;
    String nameOfGroup;

    public static final String EXTRA_MESSAGE = "com.example.dillt.Message";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_subgroup);
        nameOfGroup = "Food"; // <------ we will need to change this depending on the intent that accesses this activity

        Intent intent = getIntent(); //Create Intent and extract info from the intent that brought us here
        nameOfGroup = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        to_camera = (FloatingActionButton) findViewById(R.id.fab_toCamera2);
        listOfItems = (ListView) findViewById(R.id.lv_items);
        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, ItemList.getGroupList(nameOfGroup));
        listOfItems.setAdapter(adapter);

        listOfItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = parent.getItemAtPosition(position); //Create Object from that position
                Item item = (Item) object; //cast Object to Item
                Intent groupIntent = new Intent(subGroupActivity.this, ItemViewActivity.class);
                groupIntent.putExtra(EXTRA_MESSAGE, item.getName()); // Add item name to Intent
                startActivity(groupIntent); //Pass intent
            }
        });
    }

    public void openCamera(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
}
