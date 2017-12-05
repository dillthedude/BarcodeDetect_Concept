package com.example.dillt.barcodedetect;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import java.util.LinkedList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Trent on 12/4/2017.
 */

public class ItemList {
    private static final ItemList ourInstance = new ItemList();

    public static List<Item> items;

    public static ItemList getInstance() {
        return ourInstance;
    }
//NEED To call Load at start
    private ItemList() {
    }

    public static void addItem(Item item) {
        items.add(item);
    }

    public static void removeItem(Item item) {
        items.remove(item);
    }

    public static Item getItem(String name) {
        int i = items.indexOf(name);
        return items.get(i);
    }

    public static List getGroupList(String name) {

        List group = new LinkedList<Item>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getGroup() == name)
                group.add(items.get(i));
        }
        //group.sort(Comparator.<Item>naturalOrder());
        return group;

    }

    public static void saveItems(Context context) {
        //TODO sharad Pref update
        SharedPreferences save = context.getSharedPreferences("default_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();

        Gson gson = new Gson();
        String json = gson.toJson(items, List.class);

        editor.remove("savedItems");
        editor.putString("savedItems", json);
        editor.commit();

    }

    public static void loadItems(Context context) {
        //TODO Load Shared
        SharedPreferences load = context.getSharedPreferences("default_settings", Context.MODE_PRIVATE);
        String json = load.getString("savedItems", null);

        Gson gson = new Gson();
        items = gson.fromJson(json, List.class);
    }
}
