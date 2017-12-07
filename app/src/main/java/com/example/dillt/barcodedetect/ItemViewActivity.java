package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by cwetzker on 11/10/2017.
 * ItemViewActivity governs the behavior and editing of an individual Item in the app. Relevant
 * information is displayed, and with the toggle button, can be edited manually by the user.
 */

public class ItemViewActivity extends Activity {

    EditText itemName;
    EditText itemDescription;
    EditText itemQuantity;
    EditText itemGroup;
    ImageView itemPicture;
    ToggleButton toggle;
    Boolean canEdit = false;
    Button goToSite;
    Item item;

    static String TAG = "ItemViewACtivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        String i = intent.getStringExtra(subGroupActivity.EXTRA_MESSAGE);
        Log.i(TAG, i);
        Item item = ItemList.getItem(i);

        itemName = (EditText) findViewById(R.id.pt_itemName);
        itemDescription = (EditText) findViewById(R.id.et_itemDescription);
        itemQuantity = (EditText) findViewById(R.id.et_itemQuantity);
        itemGroup = (EditText) findViewById(R.id.pt_itemGroup);
        itemPicture = (ImageView) findViewById(R.id.iv_itemPicture);
        goToSite = (Button) findViewById(R.id.b_gotoSite);

        itemName.setText(item.getName());
        itemDescription.setText(item.getShortDescription());
        itemQuantity.setText(item.getQuantity());
        itemGroup.setText(item.getGroup());

        // Checks Item for a url and sends implicit Intent so User can visit the website
        goToSite.setEnabled(true);
        final String finalUrl = item.getProductUrl();
        goToSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(finalUrl == null)) {
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                    websiteIntent.setData(Uri.parse(finalUrl));
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "There is no website registered with this Item.";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        // toggle controls the editing of all EditText fields
        toggle = (ToggleButton) findViewById(R.id.tb_editFields);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    canEdit = true; //The toggle is enabled
                } else {
                    itemName = (EditText) findViewById(R.id.pt_itemName);
                    itemDescription = (EditText) findViewById(R.id.et_itemDescription);
                    itemQuantity = (EditText) findViewById(R.id.et_itemQuantity);
                    itemGroup = (EditText) findViewById(R.id.pt_itemGroup);
                    itemPicture = (ImageView) findViewById(R.id.iv_itemPicture);
                    canEdit = false; // The toggle is disabled
                }
            }
        });

        //////////////////////////////
        //  Button Editing Control  //
        //////////////////////////////

        itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canEdit) {
                    itemName.setEnabled(true);
                } else {
                    itemName.setEnabled(false); //ignore and don't edit
                }
            }
        });

        itemDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canEdit) {
                    itemDescription.setEnabled(true);
                } else {
                    itemDescription.setEnabled(false);
                }
            }
        });

        itemGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canEdit) {
                    itemGroup.setEnabled(true);
                } else {
                    itemGroup.setEnabled(false);
                }
            }
        });

        itemQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canEdit) {
                    itemQuantity.setEnabled(true);
                } else {
                    itemQuantity.setEnabled(false);
                }
            }
        });

    }
}