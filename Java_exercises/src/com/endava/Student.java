package com.endava;


public class Student extends Person{
  private boolean hasPreviousJavaKnowledge;

  public Student(String firstName, String lastName, String dateOfBirth, boolean hasPreviousJavaKnowledge){
    super(firstName, lastName, dateOfBirth);
    this.hasPreviousJavaKnowledge=hasPreviousJavaKnowledge;
  }
  public boolean getHasPreviousJavaKnowledge(){
    return hasPreviousJavaKnowledge;
  }

}