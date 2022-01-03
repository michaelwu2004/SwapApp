package com.example.swapapp.ui.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.swapapp.AddItem;
import com.example.swapapp.InventoryListingAdapter;
import com.example.swapapp.MainActivity;
import com.example.swapapp.databinding.FragmentFindBinding;
import com.example.swapapp.databinding.FragmentTransactionBinding;
import com.example.swapapp.db.DBHelper;
import com.example.swapapp.db.DBItemHelper;
import com.example.swapapp.models.Item;
import com.example.swapapp.models.Transaction;
import com.example.swapapp.models.User;


import java.util.ArrayList;

public class TransactionFragment extends Fragment {
  private TransactionViewModel mFindViewModel;
  private FragmentTransactionBinding binding;

  DBItemHelper dbItemHelper;

  Button trade, removeItem, addItem, back;
  EditText removeEdit;

  Transaction t;


  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    this.mFindViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
    binding = FragmentTransactionBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    //reference to objects in layout
    ListView inventory = binding.profileInventoryList;
    trade = binding.tradeBtn;
    addItem = binding.addItemBtn;
    back = binding.backBtn;
    removeItem = binding.removeBtn;
    removeEdit = binding.removeText;

    //find the items of the other user
    dbItemHelper = new DBItemHelper(getActivity());
    dbItemHelper.findUserItems(MainActivity.user_id, "OTHER");

//    //to get current user info ** not sure if needed for now
    DBHelper dbHelper = new DBHelper(getActivity());

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

    setOnClickListeners();

    this.mFindViewModel.items.observe(getViewLifecycleOwner(), new Observer<ArrayList<Item>>() {
      @Override
      public void onChanged(ArrayList<Item> items) {
        if (items.size() > 0) {
          InventoryListingAdapter inventoryListingAdapter = new InventoryListingAdapter(getActivity(), items);
          inventory.setAdapter(inventoryListingAdapter);
        }
      }
    });

    return root;
  }

  public void setOnClickListeners(){
    addItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(getActivity(), AddItem.class);
        startActivity(i);
      }
    });

    //removes the item
    removeItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //iterate through user items
        String toBeRemoved = removeEdit.getText().toString();
        ArrayList<Item> userInventory = TransactionViewModel.getItems().getValue();
        for(int i = 0; i < userInventory.size(); i++){
          if(userInventory.get(i).getName().equals(toBeRemoved)){
            userInventory.remove(i);
            break;
          }
        }
      }
    });

    trade.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("TRADE", "INITIATED");
        dbItemHelper.swapItems(MainActivity.user_id, MainActivity.otherUser.getID(), t);
      }
    });

    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //getActivity().getFragmentManager().popBackStack();
        getActivity().onBackPressed();
      }
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
