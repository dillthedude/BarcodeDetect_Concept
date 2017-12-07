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
    ArrayAdapter<String> adapter;
    String[] items;
    ListView listOfItems;
    FloatingActionButton to_camera;

    public static final String EXTRA_MESSAGE = "com.exampe.dillt.Message";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_subgroup);

        Intent intent = getIntent(); //Create Intent and extract info from the intent that brought us here

        to_camera = (FloatingActionButton) findViewById(R.id.fab_toCamera2);
        listOfItems = (ListView) findViewById(R.id.lv_items);
        items = new String[] {"apple, sunscreen, chair, frozen chicken, shampoo, razor"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items); // <Item> // ItemList.Item
        listOfItems.setAdapter(adapter);

        listOfItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = parent.getItemAtPosition(position); //Create Object from that position
                Item item = (Item) object; //cast Object to Item
                Intent groupIntent = new Intent(subGroupActivity.this, ItemViewActivity.class);
                groupIntent.putExtra("com.example.dillt.barcodedectect.MESSAGE", item.getName()); // Add item name to Intent
                startActivity(groupIntent); //Pass intent
            }
        });
    }

    public void openCamera(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

}
