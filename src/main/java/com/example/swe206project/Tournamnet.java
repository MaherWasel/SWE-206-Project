package com.example.swe206project;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

import javafx.scene.control.DatePicker;

public class Tournamnet implements Serializable{
    private String name;
    private  String sport;
    // numOfParticipants is the maximum number of participants 
    private int numOfParticipants;
    private int DurationBetweenMatches;
    private LocalDate startDate;
    private LocalDate fnishDate;
    private  boolean finshed;
    private boolean teamBased;
    private boolean isElemination;
    //need to be added to the class diga
    private boolean openRegsiteration;
    private LinkedHashMap<Integer,Match[]> stageMatches;
    //change in the class diagram {object[] --> ArrayList<Object> }
    private ArrayList<Object> participants;
    private int currentStage=1;

    private boolean random=false;
    private Object Winner;


    private boolean allMatchesArePlayed=false;

    public Tournamnet(String name, String sport ,boolean teamBased,boolean isElemination , int  numOfParticipants,
     int DurnationBetweenMatches , LocalDate startDate, LocalDate closingDate) {
        this.sport=sport;
        this.name=name;
        this.teamBased=teamBased;
        this.isElemination=isElemination;
        this.numOfParticipants=numOfParticipants;
        this.DurationBetweenMatches=DurnationBetweenMatches;
        this.fnishDate=closingDate;
        this.startDate=startDate;
        this.finshed=false;
        this.openRegsiteration=true;
        this.stageMatches =new LinkedHashMap<Integer ,Match[]>();
        this.participants=new ArrayList<Object>();

        }

        // boolean methods
    public boolean isteamBased(){
        return this.teamBased;
    } 
    public boolean isEleminationType(){
        return this.isElemination;
    } 

    public boolean isFinished(){
        return this.finshed;
    }
    public boolean isRandomed(){
        return this.random;
    }
    public boolean isFinishedScoring(){
        return allMatchesArePlayed;
    }
    public void allMatchesArePlayed(){
        allMatchesArePlayed=true;
    }

    public String toString(){
        return name;
    }
    public void randomizeTheParticipants(){
        Collections.shuffle(participants);
        random=true;
    }
    public Object getWinner(){
        return Winner;
    }


    //the getters

    public String getName() {
        return this.name;
    }

    public int getDuration() {
        //not sure 
        return this.DurationBetweenMatches;
    }
    public String getSport(){
        return this.sport;
    }

    public ArrayList<Object> getParticipants() {
        return (ArrayList<Object>)this.participants.clone();
    }

    public Match[] getStageMatches( int stage) {
        if (stage==1){
            if (random==false){
                randomizeTheParticipants();
                int j=0;
                Match[] list=new Match[participants.size()/2];
                for (int i=0;i<participants.size();i=i+2){
                    list[j]=new Match(participants.get(i), participants.get(i+1));
                    System.out.println(list[j]+" dd"+participants.size());
                    j++;
                }
                stageMatches.put(1, list);
            }
        }
        return stageMatches.get(stage);
    }
    
    public int getNumOfParticipants(){
        return numOfParticipants;
    }
    public int getNumOfRegistredParticipants(){
        return participants.size();
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
  


    public void addNewStageMatches(Match[] matches){

         if (!stageMatches.isEmpty()){
            Integer lastKey = new LinkedList<>(stageMatches.keySet()).getLast();
            this.stageMatches.put(lastKey+1,matches);

        }

        else
            this.stageMatches.put(1,matches);
    }
    public void addNewStageMatches(int stage,Object[] list){

      if (this.isElemination){
        if (list.length==1){
            allMatchesArePlayed=true;
            Winner=list[0];
        }
        Match[] _list=new Match[list.length/2];
        int j=0;
        for (int i=0;i<_list.length;i=i+2){
            _list[j]=new Match(list[i], list[i+1]);
        }
        stageMatches.put(stage, _list);

      }
    }


    //add to the class diagram
    public void updateRegisterationStatus(){
        if (participants.size()>=numOfParticipants)
            this.stopRegsteration();
    }
    public int getCurrentStage(){
        return currentStage;
    }
    public void nextStage(){
        currentStage++;
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
    public void confirmMatches(int stage, Match[] list){
        stageMatches.replace(stage, list);
    }



    //add to the class diagram
        public  void RoundRobinRoundsGenerator(){
            ArrayList<Object> conTemp=this.getParticipants();
            Object teamL = conTemp.remove(numOfParticipants-1);
    
            ArrayList<Team> conR=(ArrayList<Team>) conTemp.clone();
    
            for (int i = 1; i < numOfParticipants; i++) {
                //the list of matches
               Match[] temparr=new Match[numOfParticipants/2];
               //the first match ------ e.g.-----(7 v 6)
               temparr[0]=new Match(conR.remove(numOfParticipants-2),conR.remove(numOfParticipants-3));
    
               int num=(conR.size()-1)/2;
    
                    for(int j=1;  j<temparr.length-1      ;j++){
                        temparr[j]=new Match(conR.remove(conR.size()-1),conR.remove(0));
                    }
            //the last match ------ e.g.-----(8 v 3)
            temparr[temparr.length-1]=new Match(teamL,conR.remove(0));
    
            this.addNewStageMatches(temparr);
        
            this.shiftForword(conTemp);
            conR=(ArrayList<Team>) conTemp.clone();
        }
    
    
        }
        //need to be addedto the class diagram
        private  void shiftForword(ArrayList<Object> arr){    arr.add(0, arr.remove(arr.size()-1));
        }





    
}
