package com.endava;
import java.util.ArrayList;
import java.util.List;

public class Group{
  private String name;
  private Trainer trainer;
  private List<Student> studentsList;
  
  public Group(String name, Trainer trainer, List<Student> studentsList){
    this.name = name;
    this.trainer = trainer;
    this.studentsList = studentsList;
  }

  public void setTrainer(Trainer trainer) {
    this.trainer = trainer;
  }

  public void addStudent(Student student) throws MaximumNumberOfStudentsReached {
    if (studentsList.size()==5){
      throw new MaximumNumberOfStudentsReached("The students list already has 5 students! You can't add the 6th one!");
    }

    this.studentsList.add(student);
  }

  public int getNumberOfStudents() {
    return studentsList.size();
  }

  public List<Student> getStudentsList() {
    return studentsList;
  }

  public Trainer getTrainer() {
    return trainer;
  }

  @Override
  public String toString() {
    return name + "\nTrainer: " + trainer + "Students List:\n" + studentsList+"\n";
  }


  //displaying all students in a group sorted alphabetically by lastName
  public void displayStudentsAlphabetically() {
    System.out.println("Students form " + name + " displayed alphabetically:");
    ArrayList<Student> aux = new ArrayList<Student>();
    for (Student s:studentsList){
      aux.add(s);
    }
    //sorting the arraylist with the bubble sort algorithm
    boolean sorted;
    do { sorted = true;
      for(int i = 0; i<aux.size()-1; i++)
        if((aux.get(i).getLastName().compareToIgnoreCase(aux.get(i+1).getLastName()))>0)
        { Student tmp = aux.get(i);
          aux.set(i,aux.get(i+1));
          aux.set(i+1,tmp);;
          sorted = false;
        }
    }
    while(!sorted);

    System.out.println(aux+"\n\n");
  }


}