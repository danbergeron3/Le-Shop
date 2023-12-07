package com.mobileapp.le_shop;

import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import android.content.Context;
import android.util.Log;

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

    /**
     * Makes a string out of the item name that relates to the item's
     * image file.
     * @return
     */
    private String getStringForImageFile() {
        String ret = this.name;
        ret = ret.toLowerCase();
        ret = ret.replace(' ', '_');
        ret = ret.replace('-', '_');

        return ret;
    }
    /**
     * Gets the id of the image resource for this item.
     * @param activity
     * @return The id of the image. 0 if not found.
     */
    public int getImageResourceId(AppCompatActivity activity) {
        String filename = getStringForImageFile();
        return activity.getResources().getIdentifier(filename, "drawable", activity.getPackageName());
    }

    /**
     * Gets the id of the image resource for this item.
     * @param activity
     * @return The id of the image. 0 if not found.
     */
    public int getImageResourceId(FragmentActivity activity) {
        String filename = getStringForImageFile();
        return activity.getResources().getIdentifier(filename, "drawable", activity.getPackageName());
    }

    /**
     * Gets the id of the image resource for this item.
     * @param context
     * @return The id of the image. 0 if not found.
     */
    public int getImageResourceId(Context context) {
        String filename = getStringForImageFile();
        int id_to_ret = context.getResources().getIdentifier(filename, "drawable", context.getPackageName());
        Log.d("ShopItem", filename);
        return id_to_ret;
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
