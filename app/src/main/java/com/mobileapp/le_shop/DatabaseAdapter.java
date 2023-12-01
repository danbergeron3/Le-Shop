package com.mobileapp.le_shop;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class DatabaseAdapter {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private final Context context;

    private String tag = "DB_ADAPTER";

    /*
        Important Database Starting Functions
     */
    public DatabaseAdapter(Context c) {
        this.context = c;
        dbHelper = new DataBaseHelper(c);
    }

    public void createDatabase() throws IOException{
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            Log.e(tag, "Failed to create database");
            throw e;
        }

    }

    public void openDatabase() throws SQLException{
        try {
            dbHelper.openDataBase();
            db = dbHelper.getReadableDatabase();
            dbHelper.close();
        }catch (SQLException sqlE) {
            Log.e(tag, sqlE.toString());
            throw sqlE;
        }
    }

    public void close() {
        dbHelper.close();
    }

    /*
        Query Functions and Returns
     */



}
