package com.mobileapp.le_shop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mobileapp.le_shop.databinding.FragmentCatalogBinding;
import com.mobileapp.le_shop.databinding.FragmentHomeBinding;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        @NonNull FragmentHomeBinding binding =
                FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        LinearLayout featured = binding.linearLayoutHome1;
        // DataBaseHelper db = new DataBaseHelper(view.getContext());
        DatabaseAdapter adapter = new DatabaseAdapter(view.getContext());

        try{
            adapter.createDatabase();
            //db.createDataBase();
        } catch(IOException mIOException) {
            Log.d("Error", mIOException.getMessage());
        }

        adapter.openDatabase();

        ShopItem item1 = new ShopItem(1, "Polo", null, 18, "M" );
        ShopItem item2 = new ShopItem(1, "Polo", null, 18, "L" );
        ShopItem item3 = new ShopItem(1, "Polo", null, 18, "L" );
        ShopItem item4 = new ShopItem(2, "Slacks", null, 32, "S" );

        adapter.addCartItem(item1);
        adapter.addCartItem(item2);
        adapter.addCartItem(item3);
        adapter.addCartItem(item4);

        adapter.removeCartItem(1, "L");

        ArrayList<ShopItem> please_work = adapter.getAllCartItems();
        ArrayList<ShopItem> shirts = adapter.getAllUniqueShirts();
        ArrayList<ShopItem> pants = adapter.getAllUniquePants();
        try {
            Populate.populateViewWithFeaturedItems(featured, binding.getRoot().getContext(),
                    R.id.action_homeFragment_to_itemFragment);
        } catch (IOException e){
            Log.d("HOME_DEBUG", "Failed to feature populate");
        }


        return view;
    }
}