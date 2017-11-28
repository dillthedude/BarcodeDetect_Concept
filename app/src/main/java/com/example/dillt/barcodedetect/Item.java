package com.example.dillt.barcodedetect;

/**
 * Created by cwetzker on 11/1/2017.
 */

public class Item {
    private String _name;
    private String _upc;
    private int _quantity;
    private String _shortDescription;
    private String _brandName;
    private String _mediumImage; //image link

    public Item(String name) {
        _name = name;
        _upc = "";
        _quantity = 1;

        // Make fragment popup to assign to a group list

    }
    public Item(String name, String upc) {
        _name = name;
        _upc = upc;
        _quantity = 1;

        // Make fragment popup to assign to a group list
    }

    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    public void setUpc(String upc) {
        _upc = upc;
    }
    public  String getUpc() {
        return _upc;
    }

    public void setQuantity(int quantity) { _quantity = quantity; }
    public int getQuantity() { return _quantity; }

    public void setBrandName(String brandName) {_brandName = brandName;}
    public String getBrandName() {return _brandName;}

    public void setMediumImage(String mediumImage) {_mediumImage = mediumImage;}
    public String getMediumImage() {return _mediumImage;}

    public void setShortDescription(String shortDescription) {_shortDescription = shortDescription;}
    public String getShortDescription() {
        return _shortDescription;
    }

    public void refresh() {}

}
