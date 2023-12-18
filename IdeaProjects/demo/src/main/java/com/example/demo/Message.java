package com.example.demo;
import java.time.LocalTime;

/**
 * Message
 */
public class Message {
    Controller controller;
    String text;
    LocalTime time;
    int userID;
    public Message (String text, int userID, LocalTime time)
    {
        this.text = text;
        this.userID = userID;
        setTime();
    }
    /*
     *  it returns the current hour and the minute in which this 
     * message is sent, and it sets the time variable of this class.
     */
    public void setTime()
    {
        LocalTime time = LocalTime.now();
        this.time = time;
    }

    public String getTime()
    {
        int hour = time.getHour();
        int minute = time.getMinute();
        return hour + ":" + minute;
    }

    public int getUserID()
    {
        return this.userID;
    }

    public String getText()
    {
        return this.text;
    }
}