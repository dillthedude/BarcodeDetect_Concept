package com.example.dillt.barcodedetect;

import android.support.annotation.NonNull;

/**
 * Created by cwetzker on 11/1/2017.
 */

public class Item /*implements Comparable<Item>*/ {
    private String name;
    private String upc;
    public int quantity;
    private String shortDescription;
    private String brandName;
    private String mediumImage;
    private String group;
    private float msrp;
    private float salePrice;
    private String productUrl;
    private boolean clearance;

    public Item(String _name) {
        name = _name;
        upc = "";
        group = "Food";
    }
    public Item(String name, String upc) {
        this.name = name;
        this.upc = upc;
        group = "Food";
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

    public float getMsrp() {return msrp;}
    public void setMsrp(float msrp) {this.msrp = msrp;}

    public float getSalePrice() {return salePrice;}
    public void setSalePrice(float salePrice) {this.salePrice = salePrice;}

    public String getProductUrl() {return productUrl;}
    public void setProductUrl(String productUrl) {this.productUrl = productUrl;}

    public boolean isClearance() {return clearance;}
    public void setClearance(boolean clearance) {this.clearance = clearance;}

    @Override
    public String toString() {
        return getName();
    }
    public void refresh() {}
}
