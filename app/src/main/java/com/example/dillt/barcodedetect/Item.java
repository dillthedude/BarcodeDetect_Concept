package com.example.dillt.barcodedetect;

import android.support.annotation.NonNull;

/**
 * Created by cwetzker on 11/1/2017.
 */

public class Item implements Comparable<Item> {
    public String name;
    public String upc;
    public int quantity;
    public String shortDescription;
    public String brandName;
    public String mediumImage;
    public String group;

    public Item(String _name) {
        name = _name;
        upc = "";
    }
    public Item(String name, String upc) {
        this.name = name;
        this.upc = upc;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void setUpc(String upc) {
        upc = upc;
    }
    public  String getUpc() {
        return upc;
    }

    public void setQuantity(int _quantity) { quantity = _quantity; }
    public int getQuantity() { return quantity; }

    public void setBrandName(String brandName) {this.brandName = brandName;}
    public String getBrandName() {return brandName;}

    public void setMediumImage(String mediumImage) {this.mediumImage = mediumImage;}
    public String getMediumImage() {return mediumImage;}

    public void setShortDescription(String shortDescription) {this.shortDescription = shortDescription;}
    public String getShortDescription() {
        return shortDescription;
    }

    public String getGroup() {return group;}
    public void setGroup(String group) {this.group = group;}

    public void refresh() {}


    @Override
    public int compareTo(@NonNull Item o) {
        //TODO add compare
        return 0;
    }
}
