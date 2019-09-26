package com.example.uas_pwpb;

public class data_input {
    String date;
    String UserID;
    String title;
    String desription;

    public data_input(){

    }


    public data_input(String datentime, String userID, String title, String desription) {
        this.date = datentime;
        this.UserID = userID;
        this.title = title;
        this.desription = desription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
