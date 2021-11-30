package com.example.swapapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.swapapp.models.Item;

import java.util.ArrayList;

public class DBItemHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Item.db";

    public DBItemHelper(Context context) {
        super(context, "Item.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table items(name TEXT, description TEXT,  user_id INT, item_id INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("drop Table if exists items");

    }

    public boolean insertData(String name, String description, int userid, int itemid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("user_id", userid);
        contentValues.put("item_id", itemid);

        long result = db.insert("items", null, contentValues);

        if(result==-1) return false;
        else
            return true;
    }

    public ArrayList<Item> findUserItems(String uuid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select name, description from items where user_id = ?", new String[]{uuid});

        ArrayList<Item> userItems = null;
        if(result.getCount() != 0)
        {
            while (result.moveToNext())
            {
                int id = result.getInt(0);
                String name = result.getString(1);
                String description = result.getString(2);
                userItems.add(new Item(name, description, 0));
            }
        }

        return userItems;

    }


}

