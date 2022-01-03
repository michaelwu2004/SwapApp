package com.example.swapapp.ui.transaction;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.swapapp.models.Item;
import com.example.swapapp.models.User;

import java.util.ArrayList;

public class TransactionViewModel extends ViewModel {
    //shows inventory of the other person
    public static MutableLiveData<ArrayList<Item>> items;

    public TransactionViewModel(){
        this.items = new MutableLiveData<>();

        ArrayList<Item> tempItems = new ArrayList<>();

        this.items.setValue(tempItems);
    }

    public static LiveData<ArrayList<Item>> getItems() {
        return items;
    }



}
