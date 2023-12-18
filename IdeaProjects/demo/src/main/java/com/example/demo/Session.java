package com.example.demo;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

public class Session{
    Controller controller;
    String movieID;
    Date start;
    Date finish;
    int quota;
    String name;
    String sessionID;
    Chat chat;
    ArrayList<Message> messages;
    ArrayList<User> users; 
    TimeTask timer;

    public Session (String movieID, String name, Date start, Date finish, int quota, String sessionID)
    {
        this.movieID = movieID;
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.quota = quota; 
        this.sessionID = sessionID;
        messages = new ArrayList<>();
        users = new ArrayList<>(); 
        this.chat = new Chat(messages, sessionID, users);
    }

    public void join(User user, String sessionID)
    {
        this.users.add(user);
        this.users.get(users.indexOf(user)).getSessionIDs().add(sessionID);
    }

    public ArrayList<Integer> search(String input)
    {
        int length = input.length();
        if (name.substring(length).equals(input))
        {
            
        }
    }
}
