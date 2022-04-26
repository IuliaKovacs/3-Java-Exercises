package com.endava;


import java.util.ArrayList;
import java.util.List;

class Main {

  public static List<Student> generateData(){
    List<Student> myList = new ArrayList<Student>();

    for(int i=0;i<15;i++){
      boolean hasPreviousJavaKnowledge = i % 2 == 0 ? true : false;
      Student s = new Student("StudFirstName"+i,"StudLastName" + ((char)('Z'-i)), (10+i) + ".05." + (1990+i),hasPreviousJavaKnowledge);
      myList.add(s);
    }
    return myList;
  }

  public static List<Group> generateGroups(){
    List<Group> myList = new ArrayList<Group>();
    for(int i=0;i<4;i++){
      Group g = new Group("Group" + i,null,new ArrayList<>());
      myList.add(g);
    }
    return myList;
  }

  public static List<Trainer> generateTrainers(){
    List<Trainer> myTrainersList = new ArrayList<>();
    for(int i=0;i<3;i++){
      boolean isAuthorized = i % 2 == 0 ? true : false;
      Trainer t = new Trainer("TrainerFirst" + i, "TrainerLast" + i,(10+i) + ".10." + (1975+i), isAuthorized);
      myTrainersList.add(t);
    }
    return myTrainersList;
  }

  public static void printGroupList(List<Group> groupList){
    for(Group g:groupList){
      System.out.println(g);
    }
    System.out.println();
  }

  //displaying the group with the maximum number of students
  public static void displayGroupWithMostStudents(List<Group> groupList) {
    int maximum=0;
    Group aux=null;
    System.out.println("The group with the maximum number of students:");
    for(Group g:groupList) {
      if (g.getNumberOfStudents()>maximum){
         maximum=g.getNumberOfStudents();
         aux=g;
      }
    }
    System.out.println(aux + "The group has " + maximum + " students\n");
  }

  //displaying all students younger than 25, from all groups
  public static void displayStudentsYoungerThan25(List<Group> groupList) {
    System.out.println("Students Younger Than 25:");
    for(Group g:groupList){
       for(Student s:g.getStudentsList()){
         if (s.getAge() < 25)
           System.out.println(s);
       }
    }
  }

  //displaying all students grouped by the trainer that teaches to them
  public static void displayStudentsByTrainer(List<Trainer> trainerList, List<Group> groupList) {
    System.out.println("\nStudents grouped by the trainer that teaches to them:");
    for (Trainer t:trainerList){
      System.out.println("\nTrainer: "+t);
      for (Group g:groupList){
        if (g.getTrainer().equals(t)){
          for (Student s:g.getStudentsList()){
            System.out.println(s);
          }
        }
      }
    }
  } // if I were to store the information in a data structure, I would choose a List<Person> because it can contain both students and trainers


  //displaying all students with previous java knowledge
  public static void displayStudentsWithJavaKnowledge(List<Student> studentList) {
    System.out.println("Students with previous Java knowledge:");
    for (Student s:studentList){
      if (s.getHasPreviousJavaKnowledge()){
        System.out.println(s);
      }
    }
  }

  //displaying the group with the highest number of students with no previous java knowledge
  public static void displayGroupNoPrevKnowledge(List<Group> groupList) {
    Group aux=null;
    int maximum=0;
    System.out.println("The group with the highest number of students with no previous java knowledge:");
    for (Group g:groupList){
       int maxi=0;
       for (Student s:g.getStudentsList()) {
         if (!s.getHasPreviousJavaKnowledge()) {
           maxi++;
         }
       }
       if (maxi>maximum){
         aux=g;
         maximum=maxi;
       }
    }
    System.out.println(aux);
  }

  //removing all the students younger than 20 from all groups
  public static void removeStudentsYoungerThan20 (List<Group> groupList) {
    for (Group g: groupList) {
      for (int i=0; i<g.getStudentsList().size(); i++) {
        Student s=g.getStudentsList().get(i);
        if (s.getAge()<20) {
          g.getStudentsList().remove(i);
        }
      }
    }
  }

  public static void main(String[] args) throws MaximumNumberOfStudentsReached {

    List<Student> myStudentList = generateData();
    for(Student s:myStudentList){
      System.out.println(s.toString());
    }
    System.out.println("\n");

    List<Group> myGroupList = generateGroups();

    List<Trainer> myTrainersList = generateTrainers();
    for(Trainer t:myTrainersList){
      System.out.println(t);
    }
    System.out.println("\n");

    //assigning a trainer to each group
    for(int i=0;i<myGroupList.size();i++){
      Trainer t = myTrainersList.get(i%myTrainersList.size());
      myGroupList.get(i).setTrainer(t);
    }

    //assigning 3 different students to each group
    int indexGroup = -1;

    for(int i=0;i<myStudentList.size();i++){
      if(i%3==0){
        indexGroup++;
      }
      //the process of adding students is stopped because if we continue to add students in the last group,
      // the MaximumNumberOfStudentsReached exception will be raised
      if (indexGroup == myGroupList.size()){
        break;
      }
      Student stud = myStudentList.get(i);
      myGroupList.get(indexGroup).addStudent(stud);
    }

    myGroupList.get(2).addStudent(myStudentList.get(12));
    myGroupList.get(2).addStudent(myStudentList.get(13));
    //myGroupList.get(2).addStudent(myStudentList.get(14)); //if we add this student to the same group the MaximumNumberOfStudentsReached exception will be raised
    myGroupList.get(3).addStudent(myStudentList.get(14));
    printGroupList(myGroupList);

    //displaying students from group alphabetically
    myGroupList.get(0).displayStudentsAlphabetically();

    displayGroupWithMostStudents(myGroupList);
    displayStudentsYoungerThan25(myGroupList);
    displayStudentsByTrainer(myTrainersList, myGroupList);
    displayStudentsWithJavaKnowledge(myStudentList);
    displayGroupNoPrevKnowledge(myGroupList);
    removeStudentsYoungerThan20(myGroupList);
    printGroupList(myGroupList);



  }

}