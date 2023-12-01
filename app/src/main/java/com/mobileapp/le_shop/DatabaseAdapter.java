package com.mobileapp.le_shop;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private final Context context;

    private String tag = "DB_ADAPTER";

    /*
        Important Database Starting Functions
     */
    public DatabaseAdapter(Context c) {
        this.context = c;
        dbHelper = new DataBaseHelper(c);
    }

    /**
     * Must be called after instantiating the Adapter,
     * creates the database to be opened.
     * @throws IOException
     */
    public void createDatabase() throws IOException{
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            Log.e(tag, "Failed to create database");
            throw e;
        }

    }

    /**
     * Must be called after createDatabase(), if successful
     * will open the database and it will be ready to be read.
     * @throws SQLException
     */
    public void openDatabase() throws SQLException{
        try {
            dbHelper.openDataBase();
            db = dbHelper.getReadableDatabase();
        }catch (SQLException sqlE) {
            Log.e(tag, sqlE.toString());
            throw sqlE;
        }
    }

    /**
     * Closes the database, must be opened again
     * after.
     */
    public void close() {
        dbHelper.close();
    }

    /*
        Query Functions and Returns
        Many of these functions could fail if the table columns change
     */

    /**
     * Will query the database for a list of all the pants and their sizes.
     * Could fail if table columns change.
     * @return ArrayList of ShopItems
     */
    public ArrayList<ShopItem> getAllPants() {
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = "Select * from Items Natural Join Pants";
        /* COLUMNS: item_id name description image price size */
        Cursor cr = db.rawQuery(sql, null);
        for (int i = 0; i < cr.getCount(); i++) {
            cr.moveToPosition(i);
            int item_id = cr.getInt(0);
            String name = cr.getString(1);
            String desc = cr.getString(2);
            float price = cr.getFloat(4);
            String size = cr.getString(5);

            ShopItem item = new ShopItem(item_id, name, desc, price, size);
            list.add(item);
        }
        return list;

    }

    public ArrayList<ShopItem> getAllShirts() {
        //TODO: Similar to getAllPants
        return null;
    }

    public ArrayList<ShopItem> getAllShopItems() {
        // TODO: Get all of the items from the Items table, sizes will be null.
        return null;
    }

    public ShopItem getShopItemFromId(int id) {
        // TODO: Size will return null from this one, use getShopItemAndSizesFromId
        return null;
    }

    public ArrayList<ShopItem> getShopItemAndSizesFromId(int id) {
        // TODO: Return shop item with same id and all of its sizes
        return null;
    }

    public ArrayList<String> getAllSizesFromId(int id) {
        // TODO: Natural Join Shirts and Pants and match the id, get all sizes
        return null;
    }

    public ArrayList<ShopItem> getAllFeaturedItems() {
        // TODO: Return all the items from the Featured_Items table, will have a null size
        return null;
    }

    public void getAllCartItems() {
        // TODO: Return all of the items in the Cart_Items table
        // TODO: Want to make a Cart Item class to store quantity
    }

    /*
        Shopping Cart Updates
        NOTE: These are not as important since the Cart is our extra, please focus on the above
     */

    public void addCartItem(ShopItem item) {
        // TODO: Add the item to the cart, size must NOT BE NULL
    }

    public void removeCartItem(int id, String size) {
        // TODO: Remove item from the cart with this id and size
        // TODO: Might need more logic to reduce quantity and such
    }

}
