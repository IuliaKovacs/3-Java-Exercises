package com.endava;

public class Person{
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  
  public Person(String firstName, String lastName, String dateOfBirth){
    this.firstName=firstName;
    this.lastName=lastName;
    this.dateOfBirth=dateOfBirth;
  }

  public String getFirstName(){
    return firstName;
  }
  public String getLastName(){
    return lastName;
  }
  public String getDateOfBirth(){
    return dateOfBirth;
  }

  @Override
  public String toString(){
    return "First Name: "+ this.firstName + ", Last Name (Family Name): " + this.lastName + ", Date of Birth: " + this.dateOfBirth+"\n";
  }

  public int getAge() {
    return 2022 - Integer.parseInt(dateOfBirth.substring(6));
  }
  
}