package com.example.dillt.barcodedetect;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Trent on 12/4/2017.
 */

public class ItemList {
    private static final ItemList ourInstance = new ItemList();

    public static List<Item> items;
    public static boolean shop;
    public static ItemList getInstance() {
        return ourInstance;
    }

    private ItemList() { items = new LinkedList<>();
        shop = false;
    }

    public static void addItem(Item item) {
        if (!items.contains(item))
            items.add(item);
        else {
            int i = items.indexOf(item);
            items.get(i).quantity++;
            if (items.get(i).getQuantity() > 5)
                shop = true;
        }
        if (items.size() > 15)
            shop = true;
    }

    public static void removeItem(Item item) {
        items.remove(item);
    }

    public static Item getItem(String name) {
        for (int i = 0; i < items.size(); i++)
            if (items.get(i).getName().equals(name))
                return items.get(i);
        return null;
    }

    public static List getGroupList(String name) {

        List group = new LinkedList<Item>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getGroup().equals(name))
                group.add(items.get(i));
            if (group.size() > 10)
                shop = true;
        }
        // Should be an alphabetical sort. If app crashes at this function, comment out.
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return group;
    }

    public static void goShopping(Context context) {
        if (shop == true) {
            CharSequence text = "Time to go shopping!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    public static void saveItems(Context context) {
        SharedPreferences save = context.getSharedPreferences("default_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();

        Gson gson = new Gson();
        int size = items.size();

        editor.clear();
        for (int i = 0; i < size; i++) {
            String json = gson.toJson(items.get(i), Item.class);
            editor.putString(String.valueOf(i), json);
        }
        editor.putInt("size", size);

        editor.apply();
    }

    public static void loadItems(Context context) {
        SharedPreferences load = context.getSharedPreferences("default_settings", Context.MODE_PRIVATE);
        int size = 0;
        size = load.getInt("size",0);
        Gson gson = new Gson();
        items.clear();
        for (int i = 0; i < size; i++) {
            String json = load.getString(String.valueOf(i), null);
            Item n = gson.fromJson(json, Item.class);
            items.add(n);
        }
    }

    public static void clearSharedPref(Context context) {
        SharedPreferences load = context.getSharedPreferences("default_settings", Context.MODE_PRIVATE);
        load.edit().clear();
    }
}
