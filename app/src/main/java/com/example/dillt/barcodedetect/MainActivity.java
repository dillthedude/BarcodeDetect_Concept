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

public class MainActivity extends Activity {
    ListView myListView;
    ArrayAdapter<Item> categoryAdapter;
    FloatingActionButton btn_toCamera;
    static final String TAG = "Main Activity";

    static final String EXTRA_MESSAGE = "com.example.dillt.barcodedetect.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ItemList.loadItems(this); // Loads items from sharedPrefences
        btn_toCamera = (FloatingActionButton) findViewById(R.id.fab_toCamera);
        myListView = (ListView) findViewById(R.id.lv_itemList);
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ItemList.items);
        myListView.setAdapter(categoryAdapter);

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
                Object object = parent.getItemAtPosition(position);
                String i = (String) object;
                Intent groupIntent = new Intent(MainActivity.this, subGroupActivity.class);
                groupIntent.putExtra(EXTRA_MESSAGE, i);
                startActivity(groupIntent);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        ItemList.saveItems(this);
        categoryAdapter.notifyDataSetChanged();
    }

    public void openCamera(View view) {
        Log.d(TAG, "entered openCamera() function.");
        Intent intent = new Intent(this, CameraActivity.class);
        String message = "This is written in Main, but opened in Camera!";
        intent.putExtra("Extra message", message);
        Log.d(TAG, "now to start the CameraActivity");
        startActivity(intent);
    }


}
