package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ListView;

/**
 * Created by cwetzker on 12/4/2017.
 * subGroupActivity looks very similar to MainActivity. The main difference is that the list is full
 * of items instead of categories. Almost all the same functionality exists.
 */

public class subGroupActivity extends Activity {

    String[] items;
    ListView listOfItems;
    FloatingActionButton to_camera;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_subgroup);

        to_camera = (FloatingActionButton) findViewById(R.id.fab_toCamera2);
        listOfItems = (ListView) findViewById(R.id.lv_items);
        items = new String[] {"apple, sunscreen, chair, frozen chicken, shampoo, razor"};
    }

    public void openCamera(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
}
