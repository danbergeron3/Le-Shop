package com.mobileapp.le_shop;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapp.le_shop.databinding.FragmentCatalogBinding;

public class Populate {
    public static void populateView(FragmentCatalogBinding binding, int amountOfViews) {
        Log.d("Populate", "Populate Online");
        ViewGroup parent = binding.linearLayout1;

        for(int i = 0; i < amountOfViews; i++) {
            TextView text = new TextView(binding.getRoot().getContext());
            text.setText("Hello World!");
            parent.addView(text);
        }
    }
}
