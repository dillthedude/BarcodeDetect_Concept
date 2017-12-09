package com.example.dillt.barcodedetect;

import android.support.annotation.NonNull;

/**
 * Created by cwetzker on 11/1/2017.
 * Creates an Item instance from UPC String and/or name String.
 * Variables can be left null or empty, usage should check for null pointers.
 * Manipulation of Items usually happens through ItemList class.
 */

public class Item {
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

    /** Class Variable upc is declared an empty string. Cannot be changed later.
     * Class Variable group is declared Unsorted. Can be changed later.
     * @param _name the name of the item can be any legal string.
     */
    public Item(String _name) {
        name = _name;
        upc = "";
        group = "Unsorted";
        quantity = 1;
    }

    /** Class Variable group is declared Unsorted. Can be changed later.
     * @param name can be any legal String
     * @param upc can be any legal string; however, is provided by bar code detecter as a String of numbers
     */
    public Item(String name, String upc) {
        this.name = name;
        this.upc = upc;
        group = "Unsorted";
        quantity = 1;
    }

    /** @return name of class, can be NULL **/
    public String getName() { return name; }
    /** @param name is reset by outside user. No restrictions on contents of String **/
    public void setName(String name) { this.name = name; }

    /** @param upc is set by User. Not implemented. No restrictions on contents of String **/
    public void setUpc(String upc) {
        upc = upc;
    }
    /** @return the upc String. Can be an empty String, but should never be NULL **/
    public  String getUpc() {
        return upc;
    }

    /** @param _quantity of Items to be bought. No restrictions of value. **/
    public void setQuantity(int _quantity) { quantity = _quantity; }
    /** @return quantity of Items. Should never be NULL **/
    public int getQuantity() { return quantity; }

    /** @param brandName is set by User. Not implemented. No restrictions on content of String. **/
    public void setBrandName(String brandName) {this.brandName = brandName;}
    /** @return brandName String, no restrictions on contents of String. **/
    public String getBrandName() {return brandName;}

    public void setMediumImage(String mediumImage) {this.mediumImage = mediumImage;}
    public String getMediumImage() {return mediumImage;}

    /** @param shortDescription is set by User. Not implemented. No restrictions on content of String. **/
    public void setShortDescription(String shortDescription) {this.shortDescription = shortDescription;}
    /** @return shortDescription. No restrictions on content of String. **/
    public String getShortDescription() {
        return shortDescription;
    }

    /** @return group.  **/
    public String getGroup() {return group;}
    /** @param group is set by User.  WARNING: No restrictions on content of String, but limited options. **/
    public void setGroup(String group) {this.group = group;}

    public float getMsrp() {return msrp;}
    public void setMsrp(float msrp) {this.msrp = msrp;}

    public float getSalePrice() {return salePrice;}
    public void setSalePrice(float salePrice) {this.salePrice = salePrice;}

    public String getProductUrl() {return productUrl;}
    public void setProductUrl(String productUrl) {this.productUrl = productUrl;}

    public boolean isClearance() {return clearance;}
    public void setClearance(boolean clearance) {this.clearance = clearance;}

    /** @return name in place of Item ID. Called to populate lists.**/
    @Override
    public String toString() {
        return getName();
    }
    public void refresh() {}
}
