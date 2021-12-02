package com.example.swapapp.models;

public class Item {
  public String _id;
  private String name;
  private String description;
  private int image;

  public Item(String id,String name, String description, int image) {
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

  public int getImage() {
    return this.image;
  }

}
