package com.example.swe206project;

import java.io.Serializable;
import  java.util.Date;
public class Match implements Serializable{
    private Object contentender1;
    private Object contentender2;
    int contentender1Score;
    int contentender2Score;
    Date finshed;
    private Boolean isPlayed=false;
    
    
    
    public Match(Object a,Object b) {
        this.contentender1=a;
        this.contentender2=b;
    }

    public void editMatchScore(int a, int b){
      
            this.contentender1Score=a;
            this.contentender2Score=b;
            this.isPlayed=true;
       
        
        
    }

    public String getContentender1Name() {
        return contentender1.toString();
    }
    public String getContentender2Name() {
        return contentender2.toString();
    }

    public  Object showWinner(){
        if (contentender1Score>contentender2Score)
            
            return this.contentender1;
        else if(contentender1Score==contentender2Score)
            return "tie";
        else
            return this.contentender2;

    }


    // need to be added to the class diagram
    public int getScore1() {
        return contentender1Score;
    }
    // need to be added to the class diagram
    public int getScore2() {
        return contentender2Score;
    }
    public boolean isPlayed(){
        return this.isPlayed;
    }
    public Object get1(){
        return this.contentender1;
    }
    public Object get2(){
        return this.contentender2;
    }


    




    
    //TOSTRING 
    @Override
    public String toString() {return this.getContentender1Name() + " vs " + this.getContentender2Name();
    }
    
    
    }
