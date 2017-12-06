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
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
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

public class MainActivity extends Activity {
    ArrayAdapter<String> categoryAdapter;
    String[] categories;
    ListView myListView;
    FloatingActionButton btn_toCamera;
    static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_toCamera = (FloatingActionButton) findViewById(R.id.fab_toCamera);
        myListView = (ListView) findViewById(R.id.lv_itemList);
        categories = new String[] {"Groceries", "Kitchen", "Auto", "Toys/Games", "Unsorted"};
        categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        myListView.setAdapter(categoryAdapter);

        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        ItemList.loadItems(this);
    }

    protected void onPause(){
        super.onPause();
        ItemList.saveItems(this);
    }
    public void openCamera(View view) {
        Log.d(TAG, "entered the function.");
        Intent intent = new Intent(this, CameraActivity.class);
        String message = "This is written in Main, but opened in Camera!";
        intent.putExtra("Extra message", message);
        Log.d(TAG, "now to start the Activity");
        startActivity(intent);
        Log.d(TAG, "if so, Hello!");

    }
}
