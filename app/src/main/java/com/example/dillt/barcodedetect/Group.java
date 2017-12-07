package com.example.dillt.barcodedetect;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by cwetzker on 11/10/2017.
 */

public class Group {
    LinkedList<Item> allItems;
    String _name;
    int namelessGroupNumber = 1;

    public Group() {
        _name = ("Nameless_Group_" + namelessGroupNumber);
        namelessGroupNumber++;
    }
    public Group(String name) {
        _name = name;
    }
    public Group(Item item) {
        allItems.add(item);
        _name = ("Nameless_Group_" + namelessGroupNumber);
    }

    public String getName() {return _name;}

    public void getItem() {}

    public void add(Item e) {
        allItems.add(e);
    }

    public void delete(Item e) {
        allItems.remove(e);
    }

    public void move() {}

    public void sort() {
        Collections.sort(allItems, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }


}
