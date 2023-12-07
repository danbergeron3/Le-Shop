package com.mobileapp.le_shop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

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
        return view;
    }
}