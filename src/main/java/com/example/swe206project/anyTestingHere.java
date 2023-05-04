package com.example;

public class anyTestingHere {
    public static void main(String[] args) {
        Student x = new Student("Meshari", "s202173450@kfupm.edu.sa", "Ms123456@", 202173450);
        Student y = new Student("Ahmad", "s214124@kfupm.edu.sa", "Ms123", 214124);
        Student d = new Student("Ali", "s12345@kfupm.edu.sa", "123456", 214124);
        Student z = new Student("Ali", "s12345@kfupm.edu.sa", "123456", 12345);
        Record first = new Record(y, "first", 1);
        Record sec = new Record(y, "sec", 3);
        Record third = new Record(y, "third", 7);
        Record newer = new Record(y, "sec", 1);
      y.addNewRecord(first);
      y.addNewRecord(sec);
      y.addNewRecord(third);
      System.out.println(y.getRecords());
      y.editOneRecord(sec, newer);
      System.out.println(y.getRecords());
      y.removeLastRecord();
      System.out.println(y.getRecords());
        
    }
}
