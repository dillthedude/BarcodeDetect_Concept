package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONObject;

import java.io.File;

public class MainActivity extends Activity {

    Button cameraButton;
    ImageView cameraImage;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_toCamera = (Button) findViewById(R.id.fab_toCamera);

    }
    public void openCamera(View view) {
        Log.d("Clicked on camera", "entered the function.");
        Intent intent = new Intent(this, CameraActivity.class);
        String message = "This is written in Main, but opened in Camera!";
        intent.putExtra("Extra message", message);
        Log.d("Created the intent", "now to start the Activity");
        startActivity(intent);
        Log.d("Does this code execute?", "if so, Hello!");

    }
}
