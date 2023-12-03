package com.mobileapp.le_shop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mobileapp.le_shop.databinding.FragmentCatalogBinding;

import java.io.IOException;


public class CatalogFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       //View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        @NonNull FragmentCatalogBinding binding
                = FragmentCatalogBinding.inflate(inflater,container, false);
        View view = binding.getRoot();

        // filter system
        // add spinner adapter
        Spinner spinnerItemType = binding.itemSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                binding.getRoot().getContext(),
                R.array.item_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinnerItemType.setAdapter(adapter);

        spinnerItemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // Perform actions when an item is selected
                String selectedItemType = parentView.getItemAtPosition(position).toString();
                switch (selectedItemType) {
                    case "Shirts":
                        try {
                            binding.linearLayout1.removeAllViews();
                            Populate.populateViewWithShirts(binding.linearLayout1, binding.getRoot().getContext());
                        } catch (IOException e) {
                            Log.e("DB_ADAPTER", "Failed to create database");
                        }
                        return;
                    case "Pants":
                        try {
                            binding.linearLayout1.removeAllViews();
                            Populate.populateViewWithPants(binding.linearLayout1, binding.getRoot().getContext());
                        } catch (IOException e) {
                            Log.e("DB_ADAPTER", "Failed to create database");
                        }
                        return;
                    default:
                        try {
                            binding.linearLayout1.removeAllViews();
                            Populate.populateViewAll(binding.linearLayout1, binding.getRoot().getContext());
                        } catch (IOException e) {
                            Log.e("DB_ADAPTER", "Failed to create database");
                        }
                        return;
                }
            }
            // required but not needed
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
       return view;
    }
}