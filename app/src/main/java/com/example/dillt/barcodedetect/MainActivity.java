package com.example.dillt.barcodedetect;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
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

import java.io.File;

public class MainActivity extends Activity {

    Button cameraButton;
    ImageView cameraImage;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Button btn = (Button) findViewById(R.id.button); // our button is named btn
        btn.setOnClickListener(new View.OnClickListener() { // btn does nothing right now
            @Override
            public void onClick(View v) {
            }
        });

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
            txtView.setText(thisCode.rawValue); // display info from barcode in txtView*/
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
}
