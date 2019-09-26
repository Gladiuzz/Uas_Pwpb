package com.example.uas_pwpb;

import java.util.Date;

public class data_input {
    String datentime;
    String UserID;
    String title;
    String desription;

    public data_input(){
        //method kosong
    }


    public data_input(String datentime, String userID, String title, String desription) {
        this.datentime = datentime;
        this.UserID = userID;
        this.title = title;
        this.desription = desription;
    }

    public String getDatentime() {
        return datentime;
    }

    public void setDatentime(String datentime) {
        this.datentime = datentime;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }
}
