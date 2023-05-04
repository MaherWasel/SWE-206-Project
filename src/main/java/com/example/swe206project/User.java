package com.example.swe206project;

import java.io.Serializable;

public class User implements Serializable{
    private String name;
    private String email;
    private String userName;

    public User(String name, String email, String userName) {
        this.name = name;
        this.email = email;
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public String getUserName() {
        return userName;
    }
    // this will check if this is the same user excactly 
    public boolean equals(User obj) {
        if (obj.getUserName() == this.userName) {
            if (obj.getName().equals(this.name)) {
                if (obj.getEmail().equals(this.email)) {
                    return true;
                   
                } else
                    return false;
            } else
                return false;
        } else
            return false;
    }

}
