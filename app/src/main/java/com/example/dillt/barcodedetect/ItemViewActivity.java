package com.example.dillt.barcodedetect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
 *
 * NOTE: Getting information from the Item and Setting information to the Item is disabled due to
 * an unknown NULL pointer Exception.
 */

public class ItemViewActivity extends Activity {

    EditText itemName;
    EditText itemDescription;
    EditText itemQuantity;
    EditText itemGroup;
    ImageView itemPicture;
    ToggleButton toggle;
    Button goToSite;
    Item item;

    static String TAG = "ItemViewActivity";

    /**
     * OnCreate launches the Activity and governs the display and editablity of Item class variables
     * @param savedInstanceState this is the saved instance maintained by the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        String i = intent.getStringExtra(subGroupActivity.EXTRA_MESSAGE);
        //Log.i(TAG, i); // Output the name of the Item, in case it's wrong
        item = ItemList.getItem(i);

        itemName = (EditText) findViewById(R.id.pt_itemName);
        itemDescription = (EditText) findViewById(R.id.et_itemDescription);
        itemQuantity = (EditText) findViewById(R.id.et_itemQuantity);
        itemGroup = (EditText) findViewById(R.id.pt_itemGroup);
        itemPicture = (ImageView) findViewById(R.id.iv_itemPicture);
        goToSite = (Button) findViewById(R.id.b_gotoSite);

        // If the value exists in Item, add to layout//
        if (item.getName() != null) {
            String name = item.getName();
            itemName.setText(name);
        }
        if (item.getShortDescription() != null) {
            itemDescription.setText(item.getShortDescription());
        }
        if (item != null) {
            itemQuantity.setText(item.getQuantity());
        }
        if (item.getGroup() != null) {
            itemGroup.setText(item.getGroup());
        }

        // Checks Item for a url and sends implicit Intent so User can visit the website//
        goToSite.setEnabled(true);
        final String finalUrl;
        if (item != null) {
            finalUrl = item.getProductUrl();
        } else {
            finalUrl = null;
        }

        /**
         * GoToSite references a button linked to the item website. If implemented, it would have
         * redirected the User to their preferred  web browser.
         */
        goToSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalUrl == null) {
                    Context context = getApplicationContext();
                    CharSequence text = "There is no website registered with this Item.";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else {
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                    websiteIntent.setData(Uri.parse(finalUrl));
                }
            }
        });

        ////////////////////////////////////////
        // Button Editing Control             //
        // NOTE: Accepts any input from USER  //
        ////////////////////////////////////////

        /**
         * toggle controls the editing of all EditText fields
         * setOnCheckedChangeListener() governs the editing of the fields; when the toggle is 'On',
         * all fields can be edited.
         */
        toggle = (ToggleButton) findViewById(R.id.tb_editFields);
        toggle.setChecked(true); // set editing to 'on'
        final String[] nameString = new String[1];
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    itemName.setEnabled(true);
                    itemQuantity.setEnabled(true);
                    itemDescription.setEnabled(true);
                    itemGroup.setEnabled(true);
                } else {
                    itemName.setEnabled(false);
                    itemQuantity.setEnabled(false);
                    itemDescription.setEnabled(false);
                    itemGroup.setEnabled(false);
                    /* Save new information to Item variables
                    nameString[0] = new String ("Unnamed");
                    nameString[0] = itemName.getText().toString();
                    Log.i("~~~~~~~~~", nameString[0]);
                    item.setName(nameString[0]);
                    itemDescription.setText(item.getShortDescription());
                    itemQuantity.setText(item.getQuantity());
                    itemGroup.setText(item.getGroup());
                    /**/
                }
            }
        });

        itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.isChecked()) {
                    //itemName.setEnabled(true);
                } else {
                    //itemName.setEnabled(false);
                    //String name = itemName.getText().toString();
                    //item.setName(name);
                }
            }
        });

        itemDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle.isChecked()) {
                    //itemDescription.setEnabled(true);
                } else {
                    //itemDescription.setEnabled(false);
                }
            }
        });

        itemGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.isChecked()) {
                    //itemGroup.setEnabled(true);
                } else {
                    //itemGroup.setEnabled(false);
                }
            }
        });

        itemQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle.isChecked()) {
                    //itemQuantity.setEnabled(true);
                } else {
                    //itemQuantity.setEnabled(false);
                }
            }
        });

    }

    /**
     * Saves values to Item when leaving Activity.
     * Not implemented. Throws a NULL exception.
     */
    @Override
    protected void onPause() {
        super.onPause();
        /*
        String[] nameString = new String[0];
        nameString[0] = "Unnamed";
        nameString[0] = itemName.getText().toString();
        Log.i("~~~~~~~~~", nameString[0]);
        item.setName(nameString[0]);
        itemDescription.setText(item.getShortDescription());
        itemQuantity.setText(item.getQuantity());
        itemGroup.setText(item.getGroup());
        /**/
    }
}