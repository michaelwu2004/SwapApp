package com.example.swapapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.swapapp.MainActivity;
import com.example.swapapp.models.Item;
import com.example.swapapp.models.User;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT, password TEXT, firstname TEXT, lastname TEXT, user_id TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("drop Table if exists users");

    }

    public boolean insertData(String username, String password, String firstname, String lastname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String uuid = UUID.randomUUID().toString();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        //make a uiud section over here or smth
        contentValues.put("user_id", uuid);

        MainActivity.user_id = uuid;

        long result = db.insert("users", null, contentValues);

        if(result==-1) return false;
        else
            return true;
    }

    public boolean insertDummyData(User other, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //insert data
        contentValues.put("username", "123");
        contentValues.put("password", "123");
        contentValues.put("firstname", other.getFirstName());
        contentValues.put("lastname", other.getLastName());
        contentValues.put("user_id", other.getID());

        long result = db.insert("users", null, contentValues);

        if(result==-1) return false;
        else
            return true;
    }

    public boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[]{username});
        if(cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    public String checkusernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select user_id from users where username = ? and password = ?", new String[]{username, password});

        if(cursor.getCount() > 0){
            String res = "";
            if(cursor.moveToNext()) res = cursor.getString(cursor.getColumnIndex("user_id"));
            // broken
            return res;
        } else {
            return null;
        }
    }

    public String findFirstName(String uuid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select firstname from users where user_id = ?", new String[]{uuid});
        return cursor.toString();
    }

    public String findLastName(String uuid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select lastname from users where user_id = ?", new String[]{uuid});
        return cursor.toString();
    }

    public String findUser(String uuid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where uuid = ?", new String[]{uuid});
        return cursor.toString();
    }

    public String getUuidFromUser(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select user_id from users where username = ?", new String[]{user});
        return cursor.toString();
    }

    //template for data you want to retrieve
    public void find(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users ", new String[]{});
    }



}

