package com.mobileapp.le_shop;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.mobileapp.le_shop.databinding.FragmentItemBinding;

import java.io.IOException;
import java.util.ArrayList;

public class ItemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentItemBinding binding =
                FragmentItemBinding.inflate(inflater,container, false);
        View view = binding.getRoot();

        TextView itemLabel = binding.itemLabel;
        ImageView itemImage = binding.itemImage;
        TextView itemDescription = binding.itemDescription;
        TextView itemPrice = binding.itemPrice;
        Button submit = binding.btnAddToCart;
        RadioGroup radioGroup = binding.radioGroup;
        RadioButton rbSmall = binding.rbSmall;
        RadioButton rbMedium = binding.rbMedium;
        RadioButton rbLarge = binding.rbLarge;
        RadioButton rbXl = binding.rbXl;
        View snack = binding.vsnackbar;

        Log.d("ITEMFRAGMENT_DEBUG", "in item container");
        // Retrieve the bundle from arguments
        Bundle bundle = getArguments();
        int itemId = -1;

        DatabaseAdapter dbAdapter = new DatabaseAdapter(view.getContext());
        try {
            dbAdapter.createDatabase();
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }

        dbAdapter.openDatabase();

        if (bundle != null) {
            // Retrieve the ITEM_ID from the bundle
            itemId = bundle.getInt("ITEM_ID", -1); // -1 is the default value if the key is not found

            Log.d("ITEMFRAGMENT_DEBUG", "Id: " + itemId);
        }else {
            Log.d("ITEMFRAGMENT_DEBUG", "bundle is null");
        }

        if (itemId != -1)
        {
            ShopItem item = dbAdapter.getShopItemFromId(itemId);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (rbSmall.isChecked()){
                        item.setSize("S");
                    }
                    if(rbMedium.isChecked()){
                        item.setSize("M");
                        Log.d("MediumCheck","Medium");
                    }
                    if (rbLarge.isChecked()){
                        item.setSize("L");
                    }
                    if(rbXl.isChecked()){
                        item.setSize("Xl");
                    }
                }
            });

            itemLabel.setText(item.getName());
            int res = item.getImageResourceId(binding.getRoot().getContext());
            itemImage.setImageResource(res);
            itemDescription.setText(item.getDescription());
            itemPrice.setText(String.format("$%.2f",item.getPrice()));

            ArrayList<String> sizeList = dbAdapter.getAllSizesFromId(itemId);
            if (sizeList == null || !sizeList.contains("XL")) {
                String soldOut = "XL (sold out)";
                int textColor = Color.argb(128, 255, 0, 0);
                rbXl.setTextColor(textColor);
                rbXl.setText(soldOut);
                rbXl.setClickable(false);
            }

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar snackbar;
                    int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                    if (selectedRadioButtonId != -1) { // At least one radio button is checked
                        dbAdapter.addCartItem(item);
                        snackbar = Snackbar.make(snack, "Added to cart!",
                                Snackbar.LENGTH_LONG);
                    } else {
                        snackbar = Snackbar.make(snack, "Please select a size.",
                                Snackbar.LENGTH_LONG);
                    }
                    snackbar.show();
                }
            });
        }



        return view;
    }
}