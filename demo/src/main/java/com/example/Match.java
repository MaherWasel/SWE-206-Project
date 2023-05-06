package com.example;
import java.util.*;

import javafx.scene.chart.PieChart.Data;
class Match{
    private Object contentender1;
    private Object contentender2;
    int contentender1Score;
    int contentender2Score;
    Date date;
    
    
    
    public Match(Object a,Object b, Date date) {
        this.contentender1=a;
        this.contentender2=b;
      this.date= date;
    }

    public void editMatchScore(int a, int b){
        if (a>=0 && b>=0){
            this.contentender1Score=a;
            this.contentender2Score=b;
        }
    }

    public String getContentender1Name() {
        return contentender1.toString();
    }
    public String getContentender2Name() {
        return contentender2.toString();
    }

    public String showWinner(){
        if (contentender1Score>contentender2Score)
            return this.getContentender1Name();
        else if(contentender1Score==contentender2Score)
            return "tie";
        else
            return this.getContentender2Name();

    }

    // need to be added to the class diagram
    public int getScore1() {return contentender1Score;}
    // need to be added to the class diagram
    public int getScore2() {return contentender2Score;}



    




    
    //TOSTRING 
    @Override
    public String toString() {     return this.getContentender1Name() + " vs " + this.getContentender2Name();}
    
    
    }
