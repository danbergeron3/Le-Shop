package com.mobileapp.le_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.le_shop.databinding.FragmentItemBinding;

public class ItemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentItemBinding binding =
                FragmentItemBinding.inflate(inflater,container, false);
        View view = binding.getRoot();
        Log.d("ITEMFRAGMENT_DEBUG", "in item container");
        // Retrieve the bundle from arguments
        Bundle bundle = getArguments();

        if (bundle != null) {
            // Retrieve the ITEM_ID from the bundle
            int itemId = bundle.getInt("ITEM_ID", -1); // -1 is the default value if the key is not found

            Log.d("ITEMFRAGMENT_DEBUG", "Id: " + itemId);
        }else {
            Log.d("ITEMFRAGMENT_DEBUG", "bundle is null");
        }
        return view;
    }
}