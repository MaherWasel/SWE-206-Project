package com.example.swe206project;

import java.util.ArrayList;

public class Student extends User {
    private String teamName;
    private boolean teamMemeberState;
    private ArrayList<Record> records; 

    // to make the name like Participant 1 ..
    private String formatedSoloParticipant;
    public Student(String name, String email, String userName) {
        super(name, email, userName);
        this.records = new ArrayList<Record>();
    }
    public String getFormatedSoloParticipant(){
        return this.formatedSoloParticipant;
    }
    public void setFormatedSoloParticipant(String formatedSoloParticipant){
        this.formatedSoloParticipant=formatedSoloParticipant;
    }


    // this will be used when we add a student to a team or he creats the team
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
 

    // this will be used to cahnge the condition of the student when he join a team
    public void setTeamMemeberStatue(boolean teamMemeberState) {
        this.teamMemeberState = teamMemeberState;
    }

    // this will return the current teamName that the student is registerd on
    public String getTeamName() {
        return teamName;
    }

    // this will be used when we need to know if the student is already on a team
    public boolean isTeamMemeber() {
        return teamMemeberState;
    }

    // this will be used to add a new record
    public void addNewRecord(Record record) {
        this.records.add(record);
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    // this method will replace an oldRecordObject with a newRecord without changing
    // the order of the records i putted like this cuz in the sequence diagram i saw
    // that the UI asks for tournamntName, profile and place so later on storing it
    // on varibles and creating new object then using this method is easier
    public void editOneRecord(Record oldRecord, Record newRecord) {
        int index = this.records.indexOf(oldRecord);
        this.records.remove(index);
        this.records.add(index, newRecord);
    }

    // this will completely remove the last added record
    public void removeLastRecord() {
        int indx = this.records.size();
        this.records.remove(indx - 1);
    }
    public String toString(){
        return super.getName();
    }
}
