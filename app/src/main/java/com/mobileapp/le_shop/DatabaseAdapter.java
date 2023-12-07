package com.mobileapp.le_shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

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
     * Must be called after createDatabase(), if successful
     * will open the database and it will be ready to be write.
     * @throws SQLException
     */
    public void openWriteableDatabase() throws SQLException {
        try {
            dbHelper.openDataBase();
            db = dbHelper.getWritableDatabase();
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
        db = null;
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
     * Returns all Shop Items from the Items table that
     * are UNIQUE shirts.
     * Returns null if no results.
     * @return ArrayList of ShopItem
     */
    public ArrayList<ShopItem> getAllUniqueShirts() {
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = "Select * from Items where item_id in (select item_id from Shirts)";
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
     * Returns all Shop Items from the Items table that
     * are UNIQUE pants.
     * Returns null if no results.
     * @return ArrayList of ShopItem
     */
    public ArrayList<ShopItem> getAllUniquePants() {
        ArrayList<ShopItem> list = new ArrayList<ShopItem>();
        String sql = "Select * from Items where item_id in (select item_id from Pants)";
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
        String sql = "Select * from Cart_Items natural join Items";
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
        String sql = String.format(Locale.US,
                "select * from Cart_Items where item_id = %d and size = '%s'", id, size);
        Cursor cr = db.rawQuery(sql, null);
        if(cr.getCount() == 0) {
            return 0;
        }
        cr.moveToFirst();
        return cr.getInt(2);
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
        String sql = String.format(Locale.US,
                "select * from Cart_Items where item_id = %d and size = '%s'",
                item.getId(), item.getSize());
        Cursor cr = db.rawQuery(sql, null);
        String modify_sql;
        int quantity;
        ContentValues values;

        values = new ContentValues();
        values.put("item_id",item.getId());
        values.put("size", item.getSize());
        values.put("total_price", item.getPrice());

        // Check if Empty
        if(cr.getCount() == 0) {
            close();
            openWriteableDatabase();
            try {
                quantity = 1;
                values.put("quantity", quantity);
                db.insert("Cart_Items", null, values);
                close();
                openDatabase();
            } catch (SQLException sqlE) {
                Log.e(tag, sqlE.toString());
            }
        } else {
            try {
                quantity = getCartItemQuantity(item.getId(), item.getSize());
                String key = String.format("item_id=%d AND size='%s'", item.getId(), item.getSize());
                values.put("quantity", quantity);
                db.update("Cart_Items", values, key, null );
                close();
                openDatabase();
            } catch (SQLException sqlE) {
                Log.e(tag, sqlE.toString());
            }
        }
    }

    /**
     * Removes an item from the cart
     * @param id
     * @param size
     */
    public void removeCartItem(int id, String size) {
        String sql = String.format(Locale.US,
                "select * from Cart_Items where item_id = %d and size = '%s'", id, size);
        Cursor cr = db.rawQuery(sql, null);
        String selection = String.format("item_id=%d AND size='%s'", id, size);

        // Check if there is at least one item
        if(cr.getCount() != 0) {
            close();
            openWriteableDatabase();
            int quantity = getCartItemQuantity(id, size);
            if (quantity > 1) {
                try {
                    cr.moveToFirst();
                    ContentValues values = new ContentValues();
                    values.put("item_id", cr.getInt(0));
                    values.put("size", cr.getString(1));
                    values.put("quantity", cr.getInt(2) - 1);
                    values.put("total_price", cr.getFloat(3));
                    db.update("Cart_Items", values, selection, null );
                    close();
                    openDatabase();
                } catch (SQLException sqlE) {
                    Log.e(tag, sqlE.toString());
                }
            } else {
                try {
                    db.delete("Cart_Items", selection, null );
                    close();
                    openDatabase();
                } catch (SQLException sqlE) {
                    Log.e(tag, sqlE.toString());
                }
            }
        }
    }

}
