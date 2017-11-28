package com.example.dillt.barcodedetect;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cwetzker on 11/10/2017.
 * There should only be one category throughout the life of the app, persistent across launches.
 * Regardless, this is not a singleton.
 */

public class Category extends Object{
    LinkedList<Group> allGroups;

    // Original Object has pre-selected groups
    public Category() {
        allGroups.add(new Group("Home"));
        allGroups.add(new Group("Food"));
        allGroups.add(new Group("Bathroom"));
        allGroups.add(new Group("Incidentals"));
    }

    public void getGroup() {}

    public void addGroup(String name) {
        // Check if name exists
        allGroups.add(new Group(name));
    }

    public void delete(Group group) {
        allGroups.remove(group);
    }

    //This is probably called by an activity, so maybe it should be moved?
    public void move() {}

    //Alphabetically sort allGroups
    public void sort() {
        Collections.sort(allGroups, new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
