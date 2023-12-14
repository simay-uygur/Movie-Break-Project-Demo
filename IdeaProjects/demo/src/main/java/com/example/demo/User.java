package com.example.demo;

import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

public class User {
    Controller controller;
    String userName;
    String password;
    String userID;
    Firebase fb ;
    ArrayList<String> favMoviesIDs;
    ArrayList<String> friendsIDs;
    ArrayList<String> chatIDs;
    ArrayList<String> sessionIDs;

    public User(String userName, String password, String userID , Firebase fb)
    {
        setFirebase(fb);
        setUserName(userName);
        setPassword(password);
        setID(userID);
        addFriend(""+2);
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setFirebase(Firebase fb)
    {
        this.fb = fb ;
    }

    public String getName()
    {
        return userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setID(String userID)
    {
        this.userID = userID;
    }

    public String getID()
    {
        return userID;
    }

    public ArrayList<String> getFriendsIDs()
    {
        return friendsIDs;
    }

    public void addFriendID(String userID)
    {
        friendsIDs.add(userID);
    }

    public void addFriend(String friendID)
    {
        //addFriendID(friendID);
        System.out.println(userID);
        fb.add(userID, "Friends", friendID);
        //controller.createChat();
    }

    public void removeFriend(String userID)
    {
        friendsIDs.remove(userID);
    }

    public ArrayList<String> getFavMoviesIDs()
    {
        return this.favMoviesIDs;
    }

    public void addFavMovie(String movieID)
    {
        favMoviesIDs.add(movieID);
    }

    public void removeFavMovie(String movieID)
    {
        favMoviesIDs.remove(movieID);
    }

    public ArrayList<String> getChatIDs()
    {
        return this.chatIDs;
    }

    public ArrayList<String> getSessionIDs()
    {
        return this.sessionIDs;
    }

    public void changeUserName (String newUserName)
    {
        setUserName(newUserName);
    }

    public void changePassword (String newPassword)
    {
        setPassword(newPassword);
    }

    /* 
    public ArrayList<Integer> search(String input)
    {
        return;
    }*/
}