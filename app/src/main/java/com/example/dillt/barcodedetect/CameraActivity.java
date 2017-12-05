package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.JsonReader;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * This CameraActivity class allows the user to add items to the Item List.
 * Created by cwetzker on 11/8/2017.
 */

public class CameraActivity extends Activity {
    Button cameraButton;
    ImageView myImageView;
    TextView txtView;
    String cameraCode;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        txtView = (TextView) findViewById(R.id.txtContent);
        cameraButton = (Button) findViewById(R.id.button_cam);
        myImageView = (ImageView) findViewById(R.id.imgview);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);} // this calls onActivityResult() method
        });
    } // END OF ONCREATE()

    /**
     * Create folder if not yet created. Return file for camera to save with.
     * @return new File
     */
    private File getFile(){
        File folder = new File("sdcard/barcode");
        if (!folder.exists()) {
            folder.mkdir();
        }
        return new File(folder, "image.jpg");
    }

    /**
     * Necessary for camera activity.
     * @param requestCode IDK
     * @param resultCode IDK
     * @param data IDK
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String path = "sdcard/barcode/image.jpg";
        myImageView.setImageDrawable(Drawable.createFromPath(path)); // here the image should be drawn to the screen
    }

    /**
     * Put photograph on ImageView. Set up detector. Send barcode image and get back barcode number.
     * @param detectView IDK
     */
    public void detect(View detectView) {
        Bitmap myBitmap = BitmapFactory.decodeFile("sdcard/barcode/image.jpg");
        //myImageView.setImageBitmap(BitmapFactory.decodeFile("sdcard/barcode/image.jpg"));
        //Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ean13);
        myImageView.setImageBitmap(myBitmap);
        BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode.ALL_FORMATS).build();
        if (!detector.isOperational()) {
            txtView.setText(R.string.cannot_setup_detector); // display warning in txtView
            return;
        }

        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);
            Barcode thisCode = barcodes.valueAt(0);
            txtView.setText(thisCode.rawValue); // display info from barcode in txtView
        cameraCode = thisCode.rawValue;
    }

    /**
     * @author Trent Gillson
     * @param view
     * This class utilizes the Google Volley class to instantiate a HTTP request connection
     * to a Walmart API database. We provide the UPC code provided from @Link cameraCode
     * to search for a specific item and return a JSON object. We then Parse the JSON Object
     * into an item class.
     *
     */
    public void barCodeRequest(View view) {
        // General API URL code:  https://api.upcdatabase.org/search/{id}/{api_key}
        String OA = "kpf97zybaryzuhzjn7y7jx7s"; //API key
        String url;// = "http://api.walmartlabs.com/v1/items?apiKey=kpf97zybaryzuhzjn7y7jx7s&upc=035000521019"; // TESTING
        url = "http://api.walmartlabs.com/v1/items?apiKey=kpf97zybaryzuhzjn7y7jx7s&upc=" + cameraCode; //REAL
        // View for Testing
        final TextView mTextView = (TextView) findViewById(R.id.textView);
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Connection", "Successful Conect");

                        //SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
                        //SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        //prefsEditor.apply(); //must call commit() or apply() to save changes from edit()

                        JSONObject obj = null;
                        try {
                            obj = response.getJSONArray("items").getJSONObject(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Gson gson = new Gson();
                        Item i = gson.fromJson(obj.toString(), Item.class); //Casting into item
                        i.setQuantity(1);
                        i.setGroup("Unsorted");

                        String test = i.getName() + i.getUpc() + i.getBrandName() + i.getShortDescription();
                        mTextView.setText(test);

                        ItemList.addItem(i);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Connection", "Error Connecting");

                    }
                });


        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
        ItemList.saveItems(this);
    }
}
