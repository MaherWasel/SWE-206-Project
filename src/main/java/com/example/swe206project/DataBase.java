package com.example.swe206project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<Tournamnet> listOfTournamnets;
    private List<String> listOfSports;
    private File tournamnetsFile =new File("savingTournamnetsFolder.io");
    private File sportsFile=new File("sport.io");
    public DataBase() {
        try{

        if (tournamnetsFile.exists()){
            ObjectInputStream readingTournamnets=new ObjectInputStream(new FileInputStream(tournamnetsFile));
            listOfTournamnets=(List<Tournamnet>)(readingTournamnets.readObject());
            readingTournamnets.close();
            }
        else {
            listOfTournamnets=new ArrayList<>();
            ObjectOutputStream writingT =new ObjectOutputStream(new FileOutputStream(tournamnetsFile));
            writingT.writeObject(listOfTournamnets);
            writingT.close();
        }
        if (sportsFile.exists()){
            ObjectInputStream readingSports=new ObjectInputStream(new FileInputStream(sportsFile));
            listOfSports=(List<String>)(readingSports.readObject());
            readingSports.close();
            }
        else {
            listOfSports=new ArrayList<>();
            ObjectOutputStream writingS =new ObjectOutputStream(new FileOutputStream(sportsFile));
            writingS.writeObject(listOfSports);
            writingS.close();
        }

        }
        catch(IOException e){
            System.out.println(e);
        }
        catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    public void addTournamnet(Tournamnet tournamnet){
        if (!this.tournamnetExist(tournamnet)){
        listOfTournamnets.add(tournamnet);
        try {
            ObjectOutputStream writingT =new ObjectOutputStream(new FileOutputStream(tournamnetsFile));
            writingT.writeObject(listOfTournamnets);
            writingT.close();
        }
        catch(IOException e){
            System.out.println(e);

        }}

    }
    public void addSport(String sport){
        if (!sportExist(sport)){
        listOfSports.add(sport);
        try {
            ObjectOutputStream writingT =new ObjectOutputStream(new FileOutputStream(sportsFile));
            writingT.writeObject(listOfSports);
            writingT.close();
        }
        catch(IOException e){
            System.out.println(e);

        }
    }
    }
    
    public List<String> getSports(){
        return listOfSports;
    }
    public List<Tournamnet> getTournamnets(){
        return listOfTournamnets;
    }
    public boolean tournamnetExist(Tournamnet tournamnet){
        for (int i=0;i<listOfTournamnets.size();i++){
            if (listOfTournamnets.get(i).equals(tournamnet)){
                return true;
            }
        }
        return false;
    }
    public boolean sportExist(String sport){
        for (int i=0;i<listOfSports.size();i++){
            if (listOfSports.get(i).equals(sport)){
                return true;
            }
        }
        return false;
    }
    
    
    }
