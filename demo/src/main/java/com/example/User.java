package com.example;

public class User {
    private String name;
    private String Email;
    private int id;
    private String password;
    public User(String name,String Email,int id,String password){
        this.name=name;
        this.Email=Email;
        this.id=id;
        this.password=password;
    }
    public boolean equals(User i){
        return this.name.equals(i.name);
    }


}
