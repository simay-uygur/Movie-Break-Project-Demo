package com.example.demo;

import java.util.ArrayList;

public class Chat {
    
    Controller controller;
    String chatID;
    ArrayList<User> users;
    ArrayList<Message> messages;

    public Chat (ArrayList<Message> messages, String ID, ArrayList<User> users)
    {
        this.chatID = ID;
        this.messages = messages;
        this.users = users;
        for (int i = 0; i < this.users.size(); i++)
        {
            this.users.get(i).getChatIDs().add(ID);
        }
    }

    public String getID()
    {
        return this.chatID;
    }

    public ArrayList<User> getUsers()
    {
        return this.users;
    }

    public ArrayList<Message> getMessages()
    {
        return this.messages;
    }
}
