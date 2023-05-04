package com.example.swe206project;

public class User {
    private String name;
    private String email;
    private String password;
    private int iD;

    public User(String name, String email, String password, int iD) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getiD() {
        return iD;
    }
    // this will check if this is the same user excactly 
    public boolean equals(User obj) {
        if (obj.getiD() == this.iD) {
            if (obj.getName().equals(this.name)) {
                if (obj.getEmail().equals(this.email)) {
                    if (obj.getPassword().equals(obj.getPassword())) {
                        return true;
                    } else
                        return false;
                } else
                    return false;
            } else
                return false;
        } else
            return false;
    }

}
