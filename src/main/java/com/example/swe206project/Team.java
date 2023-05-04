package com.example.swe206project;

import java.util.ArrayList;

public class Team {
    private String name;
    private int numberOfMembers;
    private ArrayList<Student> membersList;

    public Team(String name) {
        this.name = name;
        this.numberOfMembers = 0;
        this.membersList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }


    public ArrayList<Student> getMembersList() {
        return membersList;
    }

    public void addMembers(Student member) {
        this.membersList.add(member);
        this.numberOfMembers += 1; 
    }

    public void removeMember(Student member) {
        this.membersList.remove(member);
        this.numberOfMembers -= 1; 
    }
}
