package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable {

    String email;
    int day;
    int month;

    public User() {}
    public User(String userEmail, int userDay, int userMonth){
        email = userEmail;
        day = userDay;
        month = userMonth;
    }


    public String getemail() { return email; }
    public int getday() { return day;}
    public int getmonth() { return month;}

}





