package com.mobileapp.le_shop;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapp.le_shop.databinding.FragmentCatalogBinding;

public class Populate {
    public static void populateView(ViewGroup parent, int amountOfViews) {
        Log.d("Populate", "Populate Online");


        for(int i = 0; i < amountOfViews; i++) {
            TextView text = new TextView(parent.getContext());
            text.setText("Hello World!");
            parent.addView(text);
        }
    }
}
