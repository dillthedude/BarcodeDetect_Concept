package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        itemName = (EditText) findViewById(R.id.pt_itemName);
        itemDescription = (EditText) findViewById(R.id.et_itemDescription);
        itemQuantity = (EditText) findViewById(R.id.et_itemQuantity);
        itemGroup = (EditText) findViewById(R.id.pt_itemGroup);
        itemPicture = (ImageView) findViewById(R.id.iv_itemPicture);
        toggle = (ToggleButton) findViewById(R.id.tb_editFields);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    canEdit = true; //The toggle is enabled
                } else {
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