package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by cwetzker on 11/8/2017.
 */

public class CameraActivity extends Activity {
    Button cameraButton;
    ImageView cameraImage;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        cameraButton = (Button) findViewById(R.id.button_cam);
        cameraImage = (ImageView) findViewById(R.id.capturedimage);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });

        TextView txtView = (TextView) findViewById(R.id.txtContent);//our textView is named txtView

        ImageView myImageView = (ImageView) findViewById(R.id.imgview); // image of included barcode
        Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.ean13);
        myImageView.setImageBitmap(myBitmap); // puts the image of the barcode on the screen

        BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode.ALL_FORMATS).build();
        if (!detector.isOperational()) {
            txtView.setText("Could not set up the detector!"); // display warning in txtView
            return;
        }

        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);
        //Log.w("-----------",barcodes.valueAt(0).toString());
        //System.out.print(barcodes.valueAt(0));

        if (!barcodes.equals("{}")) { // this will be able to differentiate between valid barcodes and non-barcodes in the future
            Barcode thisCode = barcodes.valueAt(0);
            txtView.setText(thisCode.rawValue); // display info from barcode in txtView
        }
        else{
            txtView.setText("Barcode could not be read");
        }
        // place image that was taken into the image view to replace the other image
        //myImageView.setImage???
    }
    private File getFile(){
        File folder = new File("sdcard/barcode_detect");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File image_file = new File(folder, "cam_image.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String path = "sdcard/barcode_detect/cam_image.jpg";
        cameraImage.setImageDrawable(Drawable.createFromPath(path));
    }

    public void barCodeRequest(View view) {
        // https://api.upcdatabase.org/search/{id}/{api_key}
        String cameraCode = "035000521019"; // for testing
        String OA = "kpf97zybaryzuhzjn7y7jx7s";
        String url;// = ""http://api.walmartlabs.com/v1/items?apiKey=" + OA + "&upc=" + cameraCode;
        url = "http://api.walmartlabs.com/v1/items?apiKey=kpf97zybaryzuhzjn7y7jx7s&upc=" + cameraCode; //Testing

        final TextView mTxtDisplay;
        ImageView mImageView;
        mTxtDisplay = (TextView) findViewById(R.id.textView);


        final TextView mTextView = (TextView) findViewById(R.id.textView);
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        prefsEditor.putString("Item", response);
                        prefsEditor.commit();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });*/
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTxtDisplay.setText("Response: " + response.toString()); //Testing display

                        //RESPONSE is JSON, turn into ITEM
                        // Shared Prefernces Save test
                        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        prefsEditor.putString("Item", response.toString());
                        prefsEditor.commit();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
// Add the request to the RequestQueue.
        //queue.add(stringRequest);
        queue.add(jsObjRequest);

        //Shared Prefernce Loading Test
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("Item", "");
        Item i = gson.fromJson(json, Item.class);
        mTextView.setText(json);
    }
}
