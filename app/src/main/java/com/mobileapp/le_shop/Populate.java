package com.mobileapp.le_shop;

import android.content.Context;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


/**
 *   Responsible for populating pages with item buttons
 */
public class Populate {

    /**
     * Takes view group to append too and the fragments context.
     * Will populate the ViewGroup with item buttons containing every item in the Database
     * @param parent
     * @param fragmentContext
     * @return
     */
    public static void populateViewAll(ViewGroup parent, Context fragmentContext)
            throws IOException {
        Log.d("POPULATE_DEBUG", "populate all called");

        // dataBase adapter initialization
        DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
        dbPortal.createDatabase();
        dbPortal.openDatabase();
        ArrayList<ShopItem> allItems = dbPortal.getAllShopItems();

        for(ShopItem item: allItems) {
            // (TODO): Implement item button here
            Log.d("POPULATE_DEBUG", "making item" + item.getName());
            ItemButton itemButton = new ItemButton(parent.getContext());
            itemButton.setItemIDNum(item.getId());
            itemButton.setItemName(item.getName());
            itemButton.setItemPrice(Float.toString(item.getPrice()));
            parent.addView(itemButton);
        }
    }

    /**
     * Takes view group to append too and the fragments context.
     * Will populate the ViewGroup with item buttons containing every pant in the Database
     * @param parent
     * @param fragmentContext
     * @return
     */
    public static void populateViewWithPants(ViewGroup parent, Context fragmentContext)
            throws IOException {
        Log.d("POPULATE_DEBUG", "populate with pants called");

        DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
        dbPortal.createDatabase();
        dbPortal.openDatabase();
        ArrayList<ShopItem> allPants = dbPortal.getAllPants();

        for(ShopItem pants: allPants) {
            Log.d("POPULATE_DEBUG", "making item" + pants.getName());
            ItemButton itemButton = new ItemButton(parent.getContext());
            itemButton.setItemIDNum(pants.getId());
            itemButton.setItemName(pants.getName());
            itemButton.setItemPrice(Float.toString(pants.getPrice()));
            parent.addView(itemButton);
        }
    }

    /**
     * Takes view group to append too and the fragments context.
     * Will populate the ViewGroup item buttons containing every shirt in the Database
     * @param parent
     * @param fragmentContext
     * @return
     */
    public static void populateViewWithShirts(ViewGroup parent, Context fragmentContext)
            throws IOException {
        DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
        dbPortal.createDatabase();
        dbPortal.openDatabase();
        ArrayList<ShopItem> allShirts = dbPortal.getAllPants();

        for(ShopItem shirts: allShirts) {
            Log.d("POPULATE_DEBUG", "making item" + shirts.getName());
            ItemButton itemButton = new ItemButton(parent.getContext());
            itemButton.setItemIDNum(shirts.getId());
            itemButton.setItemName(shirts.getName());
            itemButton.setItemPrice(Float.toString(shirts.getPrice()));
            parent.addView(itemButton);
        }
    }

    /**
     * Takes view group to append too and the fragments context.
     * Will populate the ViewGroup with item buttons containing every cart item in the Database
     * @param parent
     * @param fragmentContext
     * @return
     */
    public static void populateViewWithCartItems(ViewGroup parent, Context fragmentContext)
            throws IOException {
            DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
            dbPortal.createDatabase();
            dbPortal.openDatabase();
            ArrayList<ShopItem> allShirts = dbPortal.getAllPants();

            for(ShopItem shirts: allShirts) {
                // (TODO): Implement item button here
                Log.d("POPULATE_DEBUG", "making item" + shirts.getName());
                ItemButton itemButton = new ItemButton(parent.getContext());
                itemButton.setItemIDNum(shirts.getId());
                itemButton.setItemName(shirts.getName());
                itemButton.setItemPrice(Float.toString(shirts.getPrice()));
                parent.addView(itemButton);
            }
    }


}
