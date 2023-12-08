package com.mobileapp.le_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapp.le_shop.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        TextView totalPriceTextView = binding.totalTextView;

        return view;
    }
}