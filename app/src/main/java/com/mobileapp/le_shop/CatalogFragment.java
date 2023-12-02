package com.mobileapp.le_shop;

import static com.mobileapp.le_shop.Populate.populateView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        try {
            Populate.populateView(binding.linearLayout1, binding.getRoot().getContext());
        } catch (IOException e) {
            Log.e("DB_ADAPTER", "Failed to create database");
        }

       return view;
    }
}