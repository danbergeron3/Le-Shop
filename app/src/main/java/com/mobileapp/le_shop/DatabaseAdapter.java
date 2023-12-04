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
     * Returns null if no results.
     * @return ArrayList of ShopItems
     */
    public ArrayList<ShopItem> getAllPants() {
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = "Select * from Items Natural Join Pants";
        /* COLUMNS: item_id name description image price size */
        Cursor cr = db.rawQuery(sql, null);

        // Check if Empty
        if(cr.getCount() == 0) {
            return null;
        }

        // Comb the List and make the Shop Items
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

    /**
     * Returns all Shop Items from the Items table that
     * are shirts.
     * Returns null if no results.
     * @return ArrayList of ShopItem
     */
    public ArrayList<ShopItem> getAllShirts() {
        //TODO: Similar to getAllPants
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = "Select * from Items Natural Join Shirts";
        /* COLUMNS: item_id name description image price size */
        Cursor cr = db.rawQuery(sql, null);

        if (cr.getCount() == 0) {
            return null;
        }

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

    /**
     * Returns all Shop Items from the Items table, does not
     * return distinct sizes and size field will be NULL.
     * Returns null if no results.
     * @return ArrayList of ShopItem
     */
    public ArrayList<ShopItem> getAllShopItems() {
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = "Select * from Items";
        Cursor cr = db.rawQuery(sql, null);

        // Check if Empty
        if(cr.getCount() == 0) {
            return null;
        }

        // Comb the list and make the ShopItems
        for (int i = 0; i < cr.getCount(); i++) {
            cr.moveToPosition(i);
            int item_id = cr.getInt(0);
            String name = cr.getString(1);
            String desc = cr.getString(2);
            float price = cr.getFloat(4);
            String size = null;

            ShopItem item = new ShopItem(item_id, name, desc, price, size);
            list.add(item);
        }
        return list;
    }

    /**
     * Returns a single Shop Item from the Items table with the given id.
     * Does not return distinct sizes and size field will be NULL.
     * Returns null if no results.
     * @param id
     * @return
     */
    public ShopItem getShopItemFromId(int id) {
        String sql = String.format("select * from Items where item_id = %d",id);
        Cursor cr = db.rawQuery(sql, null);

        // Check if Empty
        if(cr.getCount() == 0) {
            return null;
        }

        // Create Shop Item and Return
        cr.moveToFirst();
        int item_id = cr.getInt(0);
        String name = cr.getString(1);
        String desc = cr.getString(2);
        float price = cr.getFloat(4);
        String size = null;

        ShopItem item = new ShopItem(item_id, name, desc, price, size);
        return item;
    }

    /**
     * Returns Shop Items from the Items table with the given id.
     * Returns all sizes of the shop item id.
     * Returns null if no results.
     * @param id
     * @return
     */
    public ArrayList<ShopItem> getShopItemAndSizesFromId(int id) {
        // TODO: Return shop item with same id and all of its sizes
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = String.format("select * from Items natural join Shirts natural join Pants where item_id = %d", id);
        Cursor cr = db.rawQuery(sql, null);

        // Check if Empty
        if(cr.getCount() == 0) {
            return null;
        }

        // Comb the list and make the ShopItems
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

    /**
     * Returns ArrayList of sizes of a given id
     * Returns null if no results.
     * @param id
     * @return
     */
    public ArrayList<String> getAllSizesFromId(int id) {
        // TODO: Natural Join Shirts and Pants and match the id, get all sizes
        ArrayList<String> list = new ArrayList<String>();
        String sql = String.format("select * from Items natural join Shirts natural join Pants where item_id = %d", id);
        Cursor cr = db.rawQuery(sql, null);

        // Check if Empty
        if(cr.getCount() == 0) {
            return null;
        }

        for (int i = 0; i < cr.getCount(); i++) {
            cr.moveToPosition(i);
            String size = cr.getString(5);
            list.add(size);
        }

        return list;
    }

    /**
     * Returns all Shop Items from the Featured Items table, does not
     * return distinct sizes and size field will be NULL.
     * Returns null if no results.
     * @return ArrayList of ShopItem
     */
    public ArrayList<ShopItem> getAllFeaturedItems() {
        // TODO: Return all the items from the Featured_Items table, will have a null size
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = String.format("select * from Featured_Items natural join Items");
        Cursor cr = db.rawQuery(sql, null);

        // Check if Empty
        if(cr.getCount() == 0) {
            return null;
        }

        // Comb the list and make the ShopItems
        for (int i = 0; i < cr.getCount(); i++) {
            cr.moveToPosition(i);
            int item_id = cr.getInt(0);
            String name = cr.getString(1);
            String desc = cr.getString(2);
            float price = cr.getFloat(4);
            String size = null;

            ShopItem item = new ShopItem(item_id, name, desc, price, size);
            list.add(item);
        }
        return list;
    }

    /**
     * Returns all Shop Items from the Cart Items table, does not
     * return distinct sizes and size field will be NULL.
     * Quantity is not included; there is a quantity adapter function.
     * these items hold TOTAL price (price * quantity).
     * Returns null if no results.
     * @return ArrayList of ShopItem
     */
    public ArrayList<ShopItem> getAllCartItems() {
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = "Select * from Cart_Items";
        Cursor cr = db.rawQuery(sql, null);

        // Check if Empty
        if(cr.getCount() == 0) {
            return null;
        }

        // Comb the list and make the ShopItems
        for (int i = 0; i < cr.getCount(); i++) {
            cr.moveToPosition(i);
            int item_id = cr.getInt(0);
            String name = cr.getString(4);
            String desc = cr.getString(5);
            float price = cr.getFloat(7);
            String size = cr.getString(1);

            ShopItem item = new ShopItem(item_id, name, desc, price, size);
            list.add(item);
        }
        return list;
    }

    /**
     * Returns quantity of shop item from Cart Item table.
     * Returns -1 if no results.
     * @param id
     * @param size
     * @return quantity
     */
    public int getCartItemQuantity(int id, String size) {
        String sql = String.format("select * from Cart_Items in (%d) and size = '%s'", id, size);
        Cursor cr = db.rawQuery(sql, null);

        if(cr.getCount() == 0) {
            return -1;
        }

        cr.moveToFirst();
        int quantity = cr.getInt(2);

        return quantity;
    }

    /*
        Shopping Cart Updates
        NOTE: These are not as important since the Cart is our extra, please focus on the above
     */

    /**
     * Add item to the Cart Item table
     * @param item
     */
    public void addCartItem(ShopItem item) {
        // TODO: Add the item to the cart, size must NOT BE NULL
        String sql = String.format("select * from Cart_Items in (%d) and size = '%s'", item.getId(), item.getSize());
        Cursor cr = db.rawQuery(sql, null);
        String modify_sql;

        // Check if Empty
        if(cr.getCount() == 0) {
            modify_sql = String.format("insert into Cart_Items(" + item.getId() + item.getSize() + 1 + item.getPrice() + ")");
        } else {
            int quantity = getCartItemQuantity(item.getId(), item.getSize());
            modify_sql = String.format("update Cart_Items set quantity = %d where id = %d", quantity, item.getId());
        }
        db.execSQL(modify_sql);
    }

    /**
     * Removes an item from the cart
     * @param id
     * @param size
     */
    public void removeCartItem(int id, String size) {
        // TODO: Remove item from the cart with this id and size
        // TODO: Might need more logic to reduce quantity and such
        String sql = String.format("select * from Cart_Items in (%d) and size = '%s'", id, size);
        Cursor cr = db.rawQuery(sql, null);
        String modify_sql;

        // Check if there is at least one item
        if(cr.getCount() != 0) {
            int quantity = getCartItemQuantity(id, size);
            if (quantity == 1) {
                modify_sql = String.format("delete from Cart_Items where id = %d", id);
            } else {
                modify_sql = String.format("update Cart_Items set quantity = %d where id = %d", quantity - 1, id);
            }
            db.execSQL(modify_sql);
        }

    }

}
