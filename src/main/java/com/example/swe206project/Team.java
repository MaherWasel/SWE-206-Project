package com.example.swe206project;

import java.io.Serializable;
import java.util.ArrayList;

public class Team  implements Serializable{
    private String name;
    private int numberOfMembers;
    private ArrayList<Student> membersList;

    public Team(String name,ArrayList<Student> membersList) {
        this.name=name;
        this.membersList=membersList;
        this.numberOfMembers = 0;
        this.membersList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }
    public String toString(){
        return this.name;
    }


    public ArrayList<Student> getMembersList() {
        return (ArrayList<Student>) membersList.clone();
    }

    public void addMembers(Student member) {
        this.membersList.add(member);
        this.numberOfMembers += 1; 
    }

            //need to be added to the class diagram
//the method will retrun true if they have the same member on both teams
public boolean noMembersConflect(Team other){
    ArrayList<Student> thisList=this.getMembersList();
    ArrayList<Student> otherList=other.getMembersList();
//  the following will only keep the comman elements between the lists
    thisList.retainAll(otherList);

    return (thisList.isEmpty());
}

    public void removeMember(Student member) {
        this.membersList.remove(member);
        this.numberOfMembers -= 1; 
    }
}
