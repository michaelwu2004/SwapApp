package com.example.swapapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swapapp.db.DBItemHelper;
import com.example.swapapp.models.Item;
import com.example.swapapp.ui.profile.ProfileViewModel;

import java.io.InputStream;

public class AddItem extends AppCompatActivity {
  ImageView imageView;
  EditText textName;
  EditText textDescription;
  Button mAddItemBtn;
  Button mAddImageBtn;

  public static final int PICK_IMAGE = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_item_activity);

    this.mAddItemBtn = findViewById(R.id.submit_btn);
    this.mAddImageBtn = findViewById(R.id.add_image);
    this.textName = findViewById(R.id.item_name);
    this.textDescription = findViewById(R.id.item_description);
    this.imageView = findViewById(R.id.itemimage);

    setOnClickListeners();

  }

  public void setOnClickListeners(){
    this.mAddImageBtn.setOnClickListener(new View.OnClickListener() {
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

    this.mAddItemBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Item newItem = new Item(textName.getText().toString(), textDescription.getText().toString(), bitmap);
        ProfileViewModel.addItem(newItem);

        DBItemHelper helper = new DBItemHelper(AddItem.this);

        if(helper.insertData(textName.getText().toString(), textDescription.getText().toString(), MainActivity.user_id, bitmap)){
          Toast.makeText(AddItem.this, "success", Toast.LENGTH_SHORT).show();

        } else {
          Toast.makeText(AddItem.this, "fix dis lol how it break T_T", Toast.LENGTH_SHORT).show();
        }
        finish();
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
