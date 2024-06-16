package com.example.demo;
import java.time.LocalTime;

/**
 * Message
 */
public class Message {
    Controller controller;
    String text;
    LocalTime time;
    String userID;

    public Message (String text, String userID)
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

    public String toString()
    {
        int hour = time.getHour();
        int minute = time.getMinute();
        if (minute < 10) 
        {
            return "Sent at: " + hour + ":0" + minute + "\n" + text;
        }
        return "Sent at: " + hour + ":" + minute + "\n" + text;
    }

    /*public String getUserID()
    {
        return this.userID;
    }*/

    /*public String getText()
    {
        return this.text;
    }*/
    /*public String displayHeadTitle(){
        return getUserID()+" "+getTime();
    }*/
    /*public String displayText(){
        return getText();
    }*/

    /*public String toString() 
    {
        return getUserID() + " at " + getTime() + ":\n"+ getText() ;
    }*/
}