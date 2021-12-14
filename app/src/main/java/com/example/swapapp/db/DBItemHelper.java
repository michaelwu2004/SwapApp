package com.example.swapapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.swapapp.AddItem;
import com.example.swapapp.models.Item;
import com.example.swapapp.ui.profile.ProfileViewModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class DBItemHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Item.db";

    public DBItemHelper(Context context) {
        super(context, "Item.db", null, 1);
    }


    //if any of us can figure it out make img included for the db if not is ok
    //image BLOB
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table items(name TEXT, description TEXT,  user_id INT, item_id INT, image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("drop Table if exists items");

    }



    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public boolean insertData(String name, String description, String userid/*, Bitmap bitmap */){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("user_id", userid);
        String uuid = UUID.randomUUID().toString();
        contentValues.put("item_id", uuid);

//        byte[] imageAsBytes = getBitmapAsByteArray(imageBitMap);
//        contentValues.put("image", imageAsBytes);

        long result = db.insert("items", null, contentValues);

        if(result==-1) return false;
        else
            return true;
    }

    public ArrayList<Item> findUserItems(String uuid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select name, description from items where user_id = ?", new String[]{uuid});

        ArrayList<Item> userItems = new ArrayList<Item>();
        if(result.getCount() != 0)
        {
            while (result.moveToNext())
            {
                //String id = result.getString(3);
                String name = result.getString(0);
                String description = result.getString(1);
                //byte[] imageAsBytes = result.getBlob(5);

                //TO-DO ADD TO ITEMFIELD
                //Bitmap imageAsBitmap = getImage(imageAsBytes);

                userItems.add(new Item(/*id,*/ name, description, 0));

            }
            Log.d("Success", "Items have been put in");
            ProfileViewModel.items.setValue(userItems);
        }

        return userItems;

    }

    public Bitmap getImage(byte[] imageAsBytes){
            return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }


}