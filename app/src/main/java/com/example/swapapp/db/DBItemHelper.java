package com.example.swapapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    public String findItem(int user_id, int item_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from items where user_id="+user_id+" AND item_id="+item_id, null );

        String itemName="";
        if (res.moveToFirst()){
            itemName = res.getString(res.getColumnIndex("name"));
        }
        return itemName;
    }


}

