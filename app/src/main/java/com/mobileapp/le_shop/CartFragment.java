package com.mobileapp.le_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobileapp.le_shop.databinding.FragmentCartBinding;

import java.io.IOException;
import java.util.ArrayList;

/**
 * POSSIBLE FEATURES TO ADD
 * TODO: REMOVE ALL CART ITEMS
 * TODO: WHEN ITEMS REMOVED, RESET THE VIEWS
 */

public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        TextView totalPriceTextView = binding.totalTextView;
        LinearLayout linearContainer = binding.scrollViewLinearLayout;

        // Debug Section
        DatabaseAdapter dbAdapter = new DatabaseAdapter(view.getContext());
        try {
            dbAdapter.createDatabase();
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }

        dbAdapter.openDatabase();
        /*ShopItem item1 = dbAdapter.getShopItemFromId(1);
        ShopItem item2 = dbAdapter.getShopItemFromId(10);

        item1.setSize("M");
        item2.setSize("L");

        dbAdapter.addCartItem(item1);
        dbAdapter.addCartItem(item2);
        dbAdapter.addCartItem(item2);
        dbAdapter.addCartItem(item2);
        dbAdapter.addCartItem(item2);*/

        // End debug

        try {
            Populate.populateViewWithCartItems(linearContainer, view.getContext());
        }catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }

        totalPriceTextView.setText(String.format("$%.2f", GetTotalPrice(dbAdapter)));


        return view;
    }

    private float GetTotalPrice(DatabaseAdapter db) {
        ArrayList<ShopItem> allCartItems =  db.getAllCartItems();
        if (allCartItems == null) {return 0;}
        float total = 0;
        for(ShopItem i : allCartItems) {
            int quantity = db.getCartItemQuantity(i.getId(), i.getSize());
            total += quantity * i.getPrice();
        }

        return total;
    }
}