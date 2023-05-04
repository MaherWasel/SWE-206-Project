package com.example.swe206project;

public class anyTestingHere {
    public static void main(String[] args) {
      // record test
        Student y = new Student("Ahmad", "s214124@kfupm.edu.sa", "Ms123", 214124);
        
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
        
      // 
    }
}
