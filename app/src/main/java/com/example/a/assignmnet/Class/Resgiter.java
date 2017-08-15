package com.example.a.assignmnet.Class;

/**
 * Created by A on 8/15/2017.
 */

public class Resgiter {
    private String user;
    private String pass;

    public Resgiter(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public Resgiter() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "ID: " +user+"\n"+"Pass: "+pass+"\n"+
                "----------------------------------\n";
    }
}
