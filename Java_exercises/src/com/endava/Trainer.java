package com.endava;

public class Trainer extends Person {
  private boolean isAuthorized;

  public Trainer(String firstName, String lastName, String dateOfBirth, boolean isAuthorized){
    super(firstName, lastName, dateOfBirth);
    this.isAuthorized=isAuthorized;
  }
  public boolean getIsAuthorized(){
    return isAuthorized;
  }
}