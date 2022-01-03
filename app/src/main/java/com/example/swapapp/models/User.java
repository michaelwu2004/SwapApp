package com.example.swapapp.models;

public class User {
  public String _id;

  private String firstName;
  private String lastName;

  private int image;

  public User(String firstName, String lastName, String id) {
    // TODO: Create _id
    this._id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }



  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }

  public String getFirstName() { return this.firstName; }

  public String getLastName() { return this.lastName; }

  public String getID() { return this._id; }

  public int getImage() {
    return this.image;
  }

}
