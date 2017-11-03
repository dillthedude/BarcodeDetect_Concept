package com.example.dillt.barcodedetect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtView = (TextView) findViewById(R.id.txtContent);//our textView is named txtView
        Button btn = (Button) findViewById(R.id.button); // our button is named btn
        btn.setOnClickListener(new View.OnClickListener() { // btn does nothing right now
            @Override
            public void onClick(View v) {
            }
        });

        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
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

        if (!barcodes.equals("{}")) { // this will be able to differentiate between valid barcodes and non-barcodes in the future
            Barcode thisCode = barcodes.valueAt(0);
            txtView.setText(thisCode.rawValue); // display info from barcode in txtView*/
        }
        else{
            txtView.setText("Barcode could not be read");
        }
    }
}
