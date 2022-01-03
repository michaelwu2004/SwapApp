package com.example.swapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.swapapp.db.DBHelper;
import com.example.swapapp.db.DBItemHelper;
import com.example.swapapp.models.Item;
import com.example.swapapp.models.Transaction;
import com.example.swapapp.models.User;
import com.example.swapapp.ui.transaction.TransactionViewModel;

import java.util.ArrayList;

public class ExchangeActivity extends AppCompatActivity {

    Button add, remove, trade;
    EditText removeItem;

    DBItemHelper dbItemHelper;

    Transaction t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        add = findViewById(R.id.add_item_btn);
        remove = findViewById(R.id.remove_item_btn);
        trade = findViewById(R.id.trade_btn);

        removeItem = findViewById(R.id.remove_item_txt);

        dbItemHelper = new DBItemHelper(ExchangeActivity.this);
        dbItemHelper.findUserItems(MainActivity.user_id, "OTHER");

        //to get current user info ** not sure if needed for now
        DBHelper dbHelper = new DBHelper(ExchangeActivity.this);

        String firstName = dbHelper.findFirstName(MainActivity.user_id);
        String lastName = dbHelper.findLastName(MainActivity.user_id);

        t = new Transaction(new User(firstName, lastName, MainActivity.user_id) , MainActivity.otherUser);

        //ArrayList<Item> user1items = TransactionViewModel.items.getValue();
        ArrayList<Item> user1items = dbItemHelper.findUserItems(MainActivity.user_id, "SEARCH");
        for(int i = 0; i < user1items.size(); i++){
            t.addTraderOneItem(user1items.get(i));
        }

        ArrayList<Item> user2items = dbItemHelper.findUserItems(MainActivity.otherUser.getID(), "SEARCH");
        for(int i = 0; i < user2items.size(); i++){
            t.addTraderTwoItem(user2items.get(i));
        }

        //adapter for listview
        InventoryListingAdapter inventory = new InventoryListingAdapter(this, user1items);
        ListView display = findViewById(R.id.display);
        display.setAdapter(inventory);

        setOnClickListeners();

    }

    public void setOnClickListeners(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExchangeActivity.this, AddItem.class);
                startActivity(i);
            }
        });

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TRADE", "INITIATED");
                dbItemHelper.swapItems(MainActivity.user_id, MainActivity.otherUser.getID(), t);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toBeRemoved = removeItem.getText().toString();
                ArrayList<Item> userInventory = t.getTraderOneItems();
                for(int i = 0; i < userInventory.size(); i++){
                    if(userInventory.get(i).getName().equals(toBeRemoved)){
                        userInventory.remove(i);
                        break;
                    }
                }
            }
        });


    }
}