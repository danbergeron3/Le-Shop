package com.mobileapp.le_shop;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;


/**
 *   Responsible for populating pages with item buttons
 */
public class Populate {

    /**
     * Takes Viewgroup to append too and the fragments context.
     * Will populate the ViewGroup with item buttons containing every item in the Database
     * @param parent
     * @param fragmentContext
     * @param action taken from nav_graph
     * @return
     */
    public static void populateViewAll(ViewGroup parent, Context fragmentContext, int action)
            throws IOException {
        Log.d("POPULATE_DEBUG", "populate all called");

        // dataBase adapter initialization
        DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
        dbPortal.createDatabase();
        dbPortal.openDatabase();
        ArrayList<ShopItem> allItems = dbPortal.getAllShopItems();
        for(ShopItem item: allItems) {
            Log.d("POPULATE_DEBUG", "making item" + item.getName());
            ItemButton itemButton = new ItemButton(parent.getContext(), null, 0,
                    item.getName(), Float.toString(item.getPrice()), item.getId(), action);
            parent.addView(itemButton);
        }
    }

    /**
     * Takes view group to append too and the fragments context.
     * Will populate the ViewGroup with item buttons containing every pant in the Database
     * @param parent
     * @param fragmentContext
     * @param action taken from nav_graph
     * @return
     */
    public static void populateViewWithPants(ViewGroup parent, Context fragmentContext, int action)
            throws IOException {
        Log.d("POPULATE_DEBUG", "populate with pants called");

        DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
        dbPortal.createDatabase();
        dbPortal.openDatabase();
        ArrayList<ShopItem> allPants = dbPortal.getAllPants();

        for(ShopItem item: allPants) {
            Log.d("POPULATE_DEBUG", "making item" + item.getName());
            ItemButton itemButton = new ItemButton(parent.getContext(), null, 0,
                    item.getName(), Float.toString(item.getPrice()), item.getId(), action);
            parent.addView(itemButton);
        }
    }

    /**
     * Takes view group to append too and the fragments context.
     * Will populate the ViewGroup item buttons containing every shirt in the Database
     * @param parent
     * @param fragmentContext
     * @param action taken from nav_graph
     * @return
     */
    public static void populateViewWithShirts(ViewGroup parent, Context fragmentContext, int action)
            throws IOException {
        DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
        dbPortal.createDatabase();
        dbPortal.openDatabase();
        ArrayList<ShopItem> allShirts = dbPortal.getAllPants();

        for(ShopItem item: allShirts) {
            Log.d("POPULATE_DEBUG", "making item" + item.getName());
            ItemButton itemButton = new ItemButton(parent.getContext(), null, 0,
                    item.getName(), Float.toString(item.getPrice()), item.getId(), action);
            parent.addView(itemButton);
        }
    }

    /**
     * Takes view group to append too and the fragments context.
     * Will populate the ViewGroup with item buttons containing every cart item in the Database
     * @param parent
     * @param fragmentContext
     * @param action taken from nav_graph
     * @return
     */
    public static void populateViewWithCartItems(ViewGroup parent, Context fragmentContext, int action)
            throws IOException {
            DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
            dbPortal.createDatabase();
            dbPortal.openDatabase();
            ArrayList<ShopItem> allShirts = dbPortal.getAllPants();

            for(ShopItem item: allShirts) {
                Log.d("POPULATE_DEBUG", "making item" + item.getName());
                ItemButton itemButton = new ItemButton(parent.getContext(), null, 0,
                        item.getName(), Float.toString(item.getPrice()), item.getId(), action);
                parent.addView(itemButton);
            }
    }
}
