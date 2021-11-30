package com.example.swapapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swapapp.db.DBItemHelper;
import com.example.swapapp.models.Item;
import com.example.swapapp.ui.profile.ProfileViewModel;

public class AddItem extends AppCompatActivity {
  EditText textName;
  EditText textDescription;
  Button mAddItemBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_item_activity);

    this.mAddItemBtn = findViewById(R.id.submit_btn);
    this.textName = findViewById(R.id.item_name);
    this.textDescription = findViewById(R.id.item_description);

    this.mAddItemBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Item newItem = new Item(textName.getText().toString(), textDescription.getText().toString(), 0);
        ProfileViewModel.addItem(newItem);

        DBItemHelper helper = new DBItemHelper(AddItem.this);
//        helper.insertData(textName.getText().toString(), textDescription.getText().toString(), 0, 0);
        finish();
      }
    });
  }
}
