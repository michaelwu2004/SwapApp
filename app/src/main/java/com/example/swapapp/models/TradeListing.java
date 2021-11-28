package com.example.swapapp.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class TradeListing {
  public String _id;
  private User tradeInitiator;
  private Date createdAt;
  private boolean tradeFull;

  public TradeListing(User tradeInitiator) {
    // TODO: Create ID

    this.tradeInitiator = tradeInitiator;
    this.createdAt = new Date(Calendar.getInstance().getTime().getTime());
  }

  public User getTradeInitiator() {
    return this.tradeInitiator;
  }

  public Transaction createTransaction(User newTrader) {
    this.tradeFull = true;

    // create a transaction object
    Transaction transaction = new Transaction(this.tradeInitiator, newTrader);

    return transaction;
  }
}
