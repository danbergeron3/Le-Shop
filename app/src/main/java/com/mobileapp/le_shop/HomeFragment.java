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
//        db.openDataBase();
//        Log.d("Database Name", db.getDatabaseName());
//        SQLiteDatabase dbRead = db.getReadableDatabase();
//
//        Cursor cr = dbRead.rawQuery("Select name from Items", null);
//        cr.moveToFirst();
//        for(int i = 0; i < cr.getCount(); i++) {
//            cr.moveToPosition(i);
//            Log.d("DB Output", cr.getString(0));
//        }
        ArrayList<ShopItem> shirts = adapter.getAllShopItems();




        return view;
    }
}