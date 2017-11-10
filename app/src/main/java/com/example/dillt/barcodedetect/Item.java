package com.example.dillt.barcodedetect;

/**
 * Created by cwetzker on 11/1/2017.
 */

public class Item {
    private String name;
    private static String bar_number;
    private int quantity;
    private boolean isUPC;

    public Item(String _name) {
        name = _name;
        bar_number = "0";
    }
    public Item(String _name, String barcode) {
        name = _name;
        bar_number = barcode;
    }
    public String[] getImage() {
        return new String[0];
    }
    public String[] getBrand() {
        return new String[0];
    }
    public void refresh() {}
    public String getName() { return name; }
    public void setName(String _name) { name = _name; }
    public static String getBarNumber() { return bar_number; }
    public void setBarNumber(String barcode) { bar_number = barcode; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int _quantity) { quantity = _quantity; }

}
