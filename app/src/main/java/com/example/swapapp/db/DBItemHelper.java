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
import com.example.swapapp.MainActivity;
import com.example.swapapp.models.Item;
import com.example.swapapp.models.Transaction;
import com.example.swapapp.ui.profile.ProfileViewModel;
import com.example.swapapp.ui.transaction.TransactionViewModel;

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

    public boolean insertData(String name, String description, String userid, Bitmap imageBitMap){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("user_id", userid);
        String uuid = UUID.randomUUID().toString();
        contentValues.put("item_id", uuid);

        byte[] imageAsBytes = getBitmapAsByteArray(imageBitMap);
        contentValues.put("image", imageAsBytes);

        long result = db.insert("items", null, contentValues);

        if(result==-1) return false;
        else
            return true;
    }

    public ArrayList<Item> findUserItems(String uuid, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select name, description, user_id, image, item_id from items where user_id = ?", new String[]{uuid});

        ArrayList<Item> userItems = new ArrayList<Item>();
        if(result.getCount() != 0)
        {
            while (result.moveToNext())
            {
                // TODO: FIX INDEXING

                String name = result.getString(0);
                String description = result.getString(1);
                String id = result.getString(2);
                byte[] imageAsBytes = result.getBlob(3);
                String item_id = result.getString(4);

                //TO-DO ADD TO ITEMFIELD
                Bitmap imageAsBitmap = getImage(imageAsBytes);

                userItems.add(new Item(id, name, description, item_id, imageAsBitmap));

            }
            Log.d("Success", "Items have been put in");
            if(type.equals("PROFILE")){
                ProfileViewModel.items.setValue(userItems);
            }

        }

        return userItems;

    }

    public int deleteItem(String uuid, Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DELETE FROM items WHERE user_id = '" + uuid + "' AND item_id = '" + item.getID() + "'");
        return db.delete("items", "user_id=?", new String[]{uuid});
    }

    public boolean addItem(String uuid, Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", item.getName());
        contentValues.put("description", item.getDescription());
        contentValues.put("user_id", uuid);
        contentValues.put("item_id", item.getID());

        byte[] imageAsBytes = getBitmapAsByteArray(item.getImage());
        contentValues.put("image", imageAsBytes);

        long result = db.insert("items", null, contentValues);

        if(result==-1) return false;
        else
            return true;

    }

    public void swapItems(String uuid1, String uuid2, Transaction t){
        ArrayList<Item> user1items = t.getTraderOneItems();
        ArrayList<Item> user2items = t.getTraderTwoItems();

        //remove items then add it in
        //user2items --> user1
        //user1items --> user2

        //adding for user 1 & 2
        for(int i = 0; i < user1items.size(); i++){
            Log.d("ADD", "ITEM1");
            addItem(MainActivity.otherUser.getID(), user1items.get(i));
        }

        for(int i = 0; i < user2items.size(); i++){
            Log.d("ADD", "ITEM2");
            addItem(MainActivity.user_id, user2items.get(i));
        }

        //deleting for user 1 & 2
        for(int i = 0; i < user1items.size(); i++){
            Log.d("DELETE", "ITEM1");
            if(deleteItem(MainActivity.user_id, user1items.get(i))==1){
                Log.d("DELETE", "SUCCESS");
            } else {
                Log.d("DELETE", "FAILURE");
            }
        }

        for(int i = 0; i < user2items.size(); i++){
            Log.d("DELETE", "ITEM2");
            if(deleteItem(MainActivity.otherUser.getID(), user2items.get(i))==1){
                Log.d("DELETE", "SUCCESS");
            } else {
                Log.d("DELETE", "FAILURE");
            }
        }


    }

    public Bitmap getImage(byte[] imageAsBytes){
            return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }


}