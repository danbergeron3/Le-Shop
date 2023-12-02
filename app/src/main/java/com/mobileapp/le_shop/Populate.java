package com.mobileapp.le_shop;

import android.content.Context;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class Populate {
    public static void populateView(ViewGroup parent, Context fragmentContext) throws IOException {
        Log.d("Populate", "Populate Online");

        // dataBase adapter initialization
        DatabaseAdapter dbPortal = new DatabaseAdapter(fragmentContext);
        dbPortal.createDatabase();
        dbPortal.openDatabase();
        ArrayList<ShopItem> allpants = dbPortal.getAllPants();

        for(ShopItem pants: allpants) {
            TextView text = new TextView(parent.getContext());
            text.setText("Hello World!");
            parent.addView(text);
        }
    }
}
