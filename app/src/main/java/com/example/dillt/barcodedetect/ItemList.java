package com.example.dillt.barcodedetect;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * A singleton class implementation which contains a list of items @see items
 * loaded from the database. The class also implements other functions concerning
 * item manipulation and can give access to sharing and saving from SharedPrefernces
 *
 * @author Trent Gillson
 */
public class ItemList {
    private static final ItemList ourInstance = new ItemList();

    public static List<Item> items;
    public static boolean shop;

    public static ItemList getInstance() {
        return ourInstance;
    }

    private ItemList() {
        items = new LinkedList<>();
        shop = false;
    }

    /**
     * @param item
     * Adds the specified @see item to the list @link items
     * after checking condtions of the list. Can change @link shop
     * to be true.
     */
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

    /**
     * @param item
     * Removes the specified @see item from @link items
     */
    public static void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * @param name
     * Returns the specified item from @link items
     * by searching the getName variable of items to match @see name
     * @return item
     */
    public static Item getItem(String name) {
        for (int i = 0; i < items.size(); i++)
            if (items.get(i).getName().equals(name))
                return items.get(i);
        return null;
    }

    /**
     * @param name
     * Searchs the @link items for items with group set to @see name
     * and adds them into a list @see group to be returned
     * @return group
     */
    public static List getGroupList(String name) {
        List group = new LinkedList<Item>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getGroup().equals(name))
                group.add(items.get(i));
            if (group.size() > 10)
                shop = true;
        }
        // Sorts items alphabetically into the group
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return group;
    }

    /**
     * @param context
     * Displays a toast to tell the user to shop.
     * Occurs when @link shop is true, which happens if @link items has more than
     * 20 items, a group has more than 10 items, or an item has a quantity
     * of more than 5
     */
    public static void goShopping(Context context) {
        if (shop) {
            CharSequence text = "Time to go shopping!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * @param context
     * Saves the list of items @link items into sharedPreferences
     */
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

    /**
     * @param context
     * Loads from sharedPreferences to populate the list @link items
     */
    public static void loadItems(Context context) {
        SharedPreferences load = context.getSharedPreferences("default_settings", Context.MODE_PRIVATE);
        int size = 0;
        size = load.getInt("size", 0);
        Gson gson = new Gson();
        items.clear();
        for (int i = 0; i < size; i++) {
            String json = load.getString(String.valueOf(i), null);
            Item n = gson.fromJson(json, Item.class);
            items.add(n);
        }
    }

    /**
     * @param context
     * clears the shared prefernces database, mainly used for testing and debugging
     */
    public static void clearSharedPref(Context context) {
        SharedPreferences load = context.getSharedPreferences("default_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = load.edit();
        editor.clear();
        editor.apply();
    }
}
