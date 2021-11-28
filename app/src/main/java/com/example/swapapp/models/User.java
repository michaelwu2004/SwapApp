package com.example.swapapp.models;

public class User {
  public String _id;

  private String firstName;
  private String lastName;

  private int image;

  public User(String firstName, String lastName) {
    // TODO: Create _id
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }

  public int getImage() {
    return this.image;
  }
}
