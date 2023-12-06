package com.mobileapp.le_shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.le_shop.databinding.FragmentAboutBinding;
import com.mobileapp.le_shop.databinding.FragmentCatalogBinding;

public class AboutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        @NonNull FragmentAboutBinding binding
                = FragmentAboutBinding.inflate(inflater,container, false);
        View view = binding.getRoot();

        return view;
    }
}