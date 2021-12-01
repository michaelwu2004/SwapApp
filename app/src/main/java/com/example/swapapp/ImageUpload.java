package com.example.swapapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swapapp.db.DBItemHelper;

import java.io.ByteArrayOutputStream;

public class ImageUpload extends AppCompatActivity {

    TextView textView, textView1;
    EditText editText;
    ImageView imageView;
    Button submit;

    DBItemHelper itemDB;

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
        submit = findViewById(R.id.submit);
        itemDB = new DBItemHelper(this);





    }


}