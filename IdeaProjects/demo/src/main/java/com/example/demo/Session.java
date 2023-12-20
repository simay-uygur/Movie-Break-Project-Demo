package com.example.demo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.ResourceBundle.Control;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Session{
    private static String movieID;
    private static Date start;
    private static Date finish;
    private static int quota;
    private static String name;
    private static String sessionID;
    private static Chat chat;
    ArrayList<Message> messages;
    ArrayList<User> users; 
    TimerTask timer;
    private String userName ;
    private DatabaseReference sessionDB ;

    public Session (String movieID, String name, Date start, Date finish, int quota, String sessionID , String userName)
    {
        this.userName = userName ;
        this.movieID = movieID;
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.quota = quota; 
        this.sessionID = sessionID;
        messages = new ArrayList<>();
        users = new ArrayList<>(); 
        chat = new Chat(sessionID , userName);
        createSession();    
    }

    public void join(User user, String sessionID)
    {
        this.users.add(user);
        this.users.get(users.indexOf(user)).getSessionIDs().add(sessionID);
    }

    public void createSession() 
    {
        sessionDB = FirebaseDatabase.getInstance().getReference("sessions").child(sessionID) ;
        sessionDB.child(movieID).setValueAsync(name) ;
        sessionDB.child("Finish").setValueAsync(finish) ;
        sessionDB.child("Start").setValueAsync(start) ;
    }

    public String toString() 
    {
        return movieID +"-"+ name +"-"+ start.toString() +"-"+ finish.toString() +"-"+ quota +"-"+ sessionID +"-"+ userName ;
    }
}
