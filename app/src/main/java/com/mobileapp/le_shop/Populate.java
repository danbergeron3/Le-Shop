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
        ArrayList<ShopItem> allpants = dbPortal.getAllPants(); // (TODO): Repalce with all items

        for(ShopItem item: allpants) {
            // (TODO): Implement item button here
            TextView text = new TextView(parent.getContext());
            text.setText("All");
            parent.addView(text);
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
            // (TODO): Implement item button here
            TextView text = new TextView(parent.getContext());
            text.setText("pants");
            parent.addView(text);
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
            // (TODO): Implement item button here
            TextView text = new TextView(parent.getContext());
            text.setText("shirts");
            parent.addView(text);
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
                TextView text = new TextView(parent.getContext());
                text.setText("cart");
                parent.addView(text);
            }
    }


}
