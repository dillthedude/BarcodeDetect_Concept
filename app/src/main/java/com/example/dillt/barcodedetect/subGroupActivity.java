package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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

/**
 * Created by cwetzker on 12/4/2017.
 */

public class subGroupActivity extends Activity {

    String[] items; //Should rewrite for real Item objects
    ListView myListView;
    FloatingActionButton btn_toCamera;
    static final String TAG = "subGroup Activity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_subgroup);
        btn_toCamera = (FloatingActionButton) findViewById(R.id.fab_toCamera);
        myListView = (ListView) findViewById(R.id.lv_items);
        items = new String[] {"Milk", "Bread", "Cheese", "Mt. Dew", "Pasta"};
    }

    public void openCamera(View view) {
        Log.d(TAG, "entered the function.");
        Intent intent = new Intent(this, CameraActivity.class);
        String message = "This is written in Main, but opened in Camera!";
        intent.putExtra("Extra message", message);
        Log.d(TAG, "now to start the Activity");
        startActivity(intent);
    }
}
