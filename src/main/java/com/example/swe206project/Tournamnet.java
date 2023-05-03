package com.example.swe206project;

import java.io.Serializable;

public class Tournamnet implements Serializable{
    String name;
    public Tournamnet(String name){
        this.name=name;
    }
    public String toString(){
        return name;
    }
    
}
