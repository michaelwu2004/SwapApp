package com.example.swapapp.models;

import android.graphics.Bitmap;

public class Item {
  public String _id;
  private String name;
  private String description;
  private Bitmap image;

  public Item(String name, String description, Bitmap image) {
    this.name = name;
    this.description = description;
    this.image = image;
  }

  public Item(String id, String name, String description, Bitmap image) {
    this._id = id;
    this.name = name;
    this.description = description;
    this.image = image;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public Bitmap getImage() {
    return this.image;
  }

}
