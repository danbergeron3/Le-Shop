package com.mobileapp.le_shop;

import android.graphics.Bitmap;

public class ShopItem {
    // TODO: Images will be stored in the app, use the name to get the image
    private String name, description, size;
    private float price;
    private int id;

    /*
        Constructors
     */
    public ShopItem(int id, String n) {
        this.id = id;
        name = n;
    }

    public ShopItem(int id, String n, String d) {
        this.id = id;
        name = n;
        description = d;
    }

    public ShopItem(int id, String n, String d, float p) {
        this.id = id;
        name = n;
        description = d;
        price = p;
    }

    public ShopItem(int id, String n, String d, float p, String s) {
        this.id = id;
        name = n;
        description = d;
        price = p;
        size = s;
    }

    /*
        Gets and Sets
     */
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String n) {
        name = n;
    }

    public void setDescription(String d) {
        description = d;
    }

    public void setPrice(float p) {
        price = p;
    }

    public void setSize(String s) {
        size = s;
    }

}
