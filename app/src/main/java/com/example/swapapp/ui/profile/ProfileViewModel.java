package com.example.swapapp.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.swapapp.models.Item;

import java.util.ArrayList;

public class  ProfileViewModel extends ViewModel {
  public static MutableLiveData<ArrayList<Item>> items;

  public ProfileViewModel() {
    this.items = new MutableLiveData<>();

    ArrayList<Item> tempItems = new ArrayList<>();
    // fish out of database here
    tempItems.add(new Item("PC", "A new computesdjadkasnndakjdnskdnsdjjdksdasjdskdjsdjsbdsiodbasiuasbduisidusabduibdasbuiasx`r", 0));
    tempItems.add(new Item("PC", "A new computesdjadkasnndakjdnskdnsdjjdksdasjdskdjsdjsbdsiodbasiuasbduisidusabduibdasbuiasx`r", 0));
    tempItems.add(new Item("PC", "A new computesdjadkasnndakjdnskdnsdjjdksdasjdskdjsdjsbdsiodbasiuasbduisidusabduibdasbuiasx`r", 0));
    this.items.setValue(tempItems);
  }

  public static LiveData<ArrayList<Item>> getItems() {
    return items;
  }

  public static boolean addItem(Item item) {
    ArrayList<Item> cloneItems = getItems().getValue();

    cloneItems.add(item);
    items.setValue(cloneItems);

    return true;
  }
}
