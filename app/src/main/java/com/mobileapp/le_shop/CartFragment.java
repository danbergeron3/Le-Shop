package com.mobileapp.le_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
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
        Button purchaseButton = binding.purchaseButton;
        View snack = binding.cartSnackbar;

        // Debug Section
        DatabaseAdapter dbAdapter = new DatabaseAdapter(view.getContext());
        try {
            dbAdapter.createDatabase();
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }

        dbAdapter.openDatabase();

        try {
            Populate.populateViewWithCartItems(linearContainer, view.getContext());
        }catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }

        totalPriceTextView.setText(String.format("$%.2f", GetTotalPrice(dbAdapter)));

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar;
                if (dbAdapter.getAllCartItems() != null) { // At least one radio button is checked
                    snackbar = Snackbar.make(snack, "Thank you for your purchase!",
                            Snackbar.LENGTH_LONG);
                } else {
                    snackbar = Snackbar.make(snack, "ERROR - no cart items.",
                            Snackbar.LENGTH_LONG);
                }
                snackbar.show();
            }
        });

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