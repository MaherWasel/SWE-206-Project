package com.example.swe206project;

import java.io.Serializable;
import java.util.*;

public class Tournamnet implements Serializable{
    private String name;
    private  String sport;
    // numOfParticipants is the maximum number of participants 
    private int numOfParticipants;
    private int DurationBetweenMatches;
    private Date startDate;
    private Date fnishDate;
    private  boolean finshed;
    private boolean teamBased;
    private boolean isElemination;
    //need to be added to the class diga
    private boolean openRegsiteration;
    private LinkedHashMap<Integer,Match[]> stageMatches;
    //change in the class diagram {object[] --> ArrayList<Object> }
    private ArrayList<Object> participants;





    public Tournamnet(String name,  String sport ,boolean teamBased,boolean isElemination , int  numOfParticipants,
     int DurnationBetweenMatches ,Date startDate,Date finshDate) {
        this.sport=sport;
        this.name=name;
        this.teamBased=teamBased;
        this.isElemination=isElemination;
        this.numOfParticipants=numOfParticipants;
        this.DurationBetweenMatches=DurnationBetweenMatches;
        this.fnishDate=finshDate;
        this.startDate=startDate;
        this.finshed=false;
        this.openRegsiteration=true;
        this.stageMatches =new LinkedHashMap<Integer ,Match[]>();
        this.participants=new ArrayList<Object>();

        }
    
    public String toString(){
        return name;
    }

    public String getName() {
        return this.name;
    }

    public int getDuration() {
        //not sure 
        return this.DurationBetweenMatches;
    }

    public ArrayList<Object> getParticipants() {
        return (ArrayList<Object>)this.participants.clone();
    }

    public Match[] getStageMatches( int stage) {
        return stageMatches.get(stage);
    }
    
    public int getNumOfParticipants(){
        return numOfParticipants;
    }

    public void stopRegsteration(){
        this.openRegsiteration=false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tournamnet){
            Tournamnet other=(Tournamnet) obj;
            //check later 
            return (this.getName()).equals(other.getName());

        }
        else 
            return false;

        
    }
    //add to the class diagram
    public void updateRegisterationStatus(){
        if (participants.size()>=numOfParticipants)
            this.stopRegsteration();
    }


    //add to the class diagram
    public void addParticipant(Object newParticipant)throws  Exception{
        //make sure they are from the same class
        if (((newParticipant instanceof Team) && !teamBased)
        ||  ((newParticipant instanceof Student) && teamBased))
            throw new Exception("wrong type");

        //the tournament is open and we did not reatch the maxium number of participant
        if (participants.size()<numOfParticipants && openRegsiteration){
            participants.add(newParticipant);
            this.updateRegisterationStatus();

        }

        else
            throw new Exception("reigesteration is closed");



    }










    
}
