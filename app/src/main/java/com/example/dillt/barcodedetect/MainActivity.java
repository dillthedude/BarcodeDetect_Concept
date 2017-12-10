package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    ListView myListView;
    ArrayAdapter<String> categoryAdapter;
    FloatingActionButton btn_toCamera;
    static final String TAG = "Main Activity";
    String[] groups = {"Home", "Food", "Bathroom", "Unsorted"};
    int pressedGroup = 0;

    static final String EXTRA_MESSAGE = "GROUPHASH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemList.loadItems(this); // Loads items from sharedPrefences
        btn_toCamera = (FloatingActionButton) findViewById(R.id.fab_toCamera);
        myListView = (ListView) findViewById(R.id.lv_itemList);
        //Item funnyItem = new Item("Apples");
        //ItemList.addItem(funnyItem);
        // Doritos Nacho Cheese Tortilla Chips 1.75 oz. Bag
        // Abound NEW Black Women's Size Small S Geometric Print Fitted Tank Cami Top
        // Darrell Lea Liquorice, Soft Eating, Blueberry Pomegranate Flavor


        categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        myListView.setAdapter(categoryAdapter);

        registerForContextMenu(myListView); // this registers the groups in the ListView as things that can bring up a context menu

        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        categoryAdapter.notifyDataSetChanged();

        ItemList.goShopping(getApplicationContext());

        // Jump to subGroupActivity by way of any list button
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pressedGroup = position; // save which group was pressed
                Object object = parent.getItemAtPosition(position); //Create object from position of KEYDOWN
                String i = object.toString();
                Intent groupIntent = new Intent(MainActivity.this, subGroupActivity.class);

                groupIntent.putExtra(EXTRA_MESSAGE, i); //
                startActivity(groupIntent);
            }
        });

//        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
//                Log.v("long clicked","pos: " + pos);
//                pressedGroup = pos;
//
//                registerForContextMenu(myListView); // this registers the groups in the ListView as things that can bring up a context menu
//                return true;
//            }
//        });
        registerForContextMenu(myListView); // this registers the groups in the ListView as things that can bring up a context menu
    }

    @Override
    protected void onPause(){
        super.onPause();
        ItemList.saveItems(this);
        categoryAdapter.notifyDataSetChanged();
    }

    public void openCamera(View view) {
        //Log.d(TAG, "entered openCamera() function.");
        Intent intent = new Intent(this, CameraActivity.class);
        //String message = "This is written in Main, but opened in Camera!";
        //intent.putExtra("Extra message", message);
        //Log.d(TAG, "now to start the CameraActivity");
        startActivity(intent);
    }

    // this function is called on a long press of a group name in the ListView
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.myfuncontextmenu, menu);
    }

    // this function is called when a context menu item is selected
    public boolean onContextItemSelected(MenuItem item) {
        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.option2:
                deleteAGroup();
                categoryAdapter.notifyDataSetChanged();
                return true;
            default:
                return false;
        }
    }

    public void deleteAGroup() {
        String[] myNewGroup = new String[3];
        String s0 = groups[0];
        String s1 = groups[1];
        String s2 = groups[2];
        String s3 = groups[3];

        if (pressedGroup == 0) {
            String[] newGroups = new String[3];
            newGroups[0] = s1;
            newGroups[1] = s2;
            newGroups[2] = s3;
            myNewGroup = newGroups;
        }
        else if (pressedGroup == 1) {
            String[] newGroups = new String[3];
            newGroups[0] = s0;
            newGroups[1] = s2;
            newGroups[2] = s3;
            myNewGroup = newGroups;
        }
        else if (pressedGroup == 2) {
            String[] newGroups = new String[3];
            newGroups[0] = s0;
            newGroups[1] = s1;
            newGroups[2] = s3;
            myNewGroup = newGroups;
        }
        else if (pressedGroup == 3) {
            String[] newGroups = new String[3];
            newGroups[0] = s0;
            newGroups[1] = s1;
            newGroups[2] = s2;
            myNewGroup = newGroups;
        }
        categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myNewGroup);
        myListView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

}
