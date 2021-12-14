package com.example.swapapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swapapp.db.DBItemHelper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ImageUpload extends AppCompatActivity {

    TextView textView, textView1;
    EditText editText;
    ImageView imageView;
    Button insert, submit;
    DBItemHelper itemDB;

    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
        PackageManager.PERMISSION_GRANTED);

        textView = findViewById(R.id.textview);
        textView1 = findViewById(R.id.textview1);
        editText = findViewById(R.id.imageName);
        imageView = findViewById(R.id.image);
        insert = findViewById(R.id.insert);
        submit = findViewById(R.id.submit);
        itemDB = new DBItemHelper(this);

        setOnClickListeners();

    }

    public void setOnClickListeners(){
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = findViewById(R.id.imageName).toString();
                String imageDesc = findViewById(R.id.imageDesc).toString();

                //turns image into bitmap and then we can store it as a blob
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                if(itemDB.insertData(imageName, imageDesc, MainActivity.user_id ,bitmap)){
                    Toast.makeText(ImageUpload.this, "success", Toast.LENGTH_SHORT).show();

                } else {    
                    Toast.makeText(ImageUpload.this, "fix dis lol how it break T_T", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            try{

                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                //turn the image chosen as input into a bitmap and set imageview to that
                //now we are able to access the image
                Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                imageView.setImageDrawable(bitmapDrawable);



            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }



}