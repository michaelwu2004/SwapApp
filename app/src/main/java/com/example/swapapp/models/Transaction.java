package com.example.swapapp.models;

import com.example.swapapp.db.DBItemHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Transaction {
  public String _id;
  private User[] traders;
  private ArrayList<Item> traderOneItems;
  private ArrayList<Item> traderTwoItems;
  private Date createdAt;
  private DBItemHelper Itemdb;

  public Transaction(User trader1, User trader2) {
    // TODO: Create ID

    this.traders = new User[] {trader1, trader2};
    traderOneItems = new ArrayList<>();
    traderTwoItems = new ArrayList<>();

    this.createdAt = new Date(Calendar.getInstance().getTime().getTime());

  }

  public void addTraderOneItem(Item item) {
    if (this.traderOneItems.contains(item)) return;

    this.traderOneItems.add(item);
  }

  public void addTraderTwoItem(Item item) {
    if (this.traderTwoItems.contains(item)) return;

    this.traderTwoItems.add(item);
  }

  public void removeTraderOneItem(Item item) {
    if (!this.traderOneItems.contains(item)) return;

    this.traderTwoItems.remove(item);
  }

  public void removeTraderTwoItem(Item item) {
    if (!this.traderTwoItems.contains(item)) return;

    this.traderTwoItems.remove(item);
  }

  public ArrayList<Item> getTraderOneItems(){
    return traderOneItems;
  }

  public ArrayList<Item> getTraderTwoItems(){
    return traderTwoItems;
  }
}
