package com.mobileapp.le_shop;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME ="SaharaDB.db";
    private static String DB_PATH = "/data/data/com.mobileapp.le_shop/databases/";
    private static int DB_VERSION = 1;
    private final File DB_FILE;
    private SQLiteDatabase dataBase;
    private final Context context;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_FILE = context.getDatabasePath(DB_NAME);
        this.context = context;
    }

    public void createDataBase() throws IOException {
        // If the database does not exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist) { //!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Copy the database from assets
                copyDataBase();
                Log.d("Database", "Database Copied!");
            } catch (IOException mIOException) {
                Log.d("Database Error:", mIOException.getMessage());
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }


    // Check that the database file exists in databases folder
    private boolean checkDataBase() {
        return DB_FILE.exists();
    }

    // Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput;
        try {
            mInput = context.getAssets().open(DB_NAME);
        } catch (IOException e) {
            Log.d("GB Debug", e.getMessage());
            throw e;
        }
        Log.d("DATABASE COPY", mInput.toString());
        OutputStream mOutput = new FileOutputStream(DB_FILE);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    // Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        //dataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_FILE, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        dataBase = SQLiteDatabase.openDatabase(DB_FILE.getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return dataBase != null;
    }

    @Override
    public synchronized void close() {
        if(dataBase != null) {
            dataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // no need to do this in prebuild database
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}