package com.example.demo;

import com.example.demo.GUIController;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.firebase.messaging.Message;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Firebase {
    private boolean accExists = false;
    ArrayList<String> users ;
    ArrayList<Movie> movies ;
    DatabaseReference films ;
    private int userID;
    private int chatID;
    private User u;
    private Chat c;
    DatabaseReference userDB;
    DatabaseReference chatDB;
    Query userQ ;
    public Firebase()
    {
        films = FirebaseDatabase.getInstance().getReference("movies");
        userDB = FirebaseDatabase.getInstance().getReference("users");
        chatDB = FirebaseDatabase.getInstance().getReference("chats");
        users = new ArrayList<>() ;
        movies = new ArrayList<>() ;
        takeAllMovieData();
        takeAllData();
    }

    public boolean userPush(String name , String password , int id)
    {
        if (!accountExists(name)) 
        {
            DatabaseReference user = userDB.child(""+id);
            user.child("Username").setValueAsync(name);
            user.child("Password").setValueAsync(password);
            DatabaseReference userIDs = userDB.child("ID-Counter");
            userIDs.setValueAsync(id + 1);
            return true ;
        }
        return false ;
    }

    public boolean accountExists(String name) 
    {
        for (String toCheck : users) 
        {
            if (toCheck.substring(toCheck.indexOf("|")+1, toCheck.lastIndexOf("|")).equals(name)) return true ;
        }
        return false ;
    }

    public void chatPush()
    {
        DatabaseReference chat = chatDB.child(c.getID());
        chat.child("messages").setValueAsync(c.getMessages());
        DatabaseReference chatIDs = chatDB.child("ID-Counter");
        chatIDs.setValueAsync(Integer.parseInt(c.getID()) + 1);
    }

    public boolean hasAcc(String name, String pass)
    {
        int ID = indexOf(name, pass) ;
        if (ID > -1) 
        {
            createUser(name, pass, ""+ID);
            return true ;
        }
        return false ;
    }

    public int indexOf(String name , String pass)
    {
        for (String toCheck : users) 
        {
            if (toCheck.substring(toCheck.indexOf("|")+1).equals(name+"|"+pass)) 
                return Integer.parseInt(toCheck.substring(0, toCheck.indexOf("|"))) ;
        }
        return -1 ;
    }

    public void takeAllData()
    {
        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren())
                {
                    String personalData = userSnapshot.getKey() + "|" + userSnapshot.child("Username").getValue() + "|" + userSnapshot.child("Password").getValue() ;
                    users.add(personalData) ;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    public void takeAllMovieData() 
    {
        films.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
               for (DataSnapshot movie : snapshot.getChildren()) 
               {
                    movies.add(new Movie(
                                    Integer.parseInt(movie.getKey()),
                                    ""+movie.child("title").getValue(), 
                                    ""+movie.child("genre").getValue(), 
                                    ""+movie.child("path").getValue())
                                ) ;
               }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Something went wrong :(");
            }
            
        });
    }

    public void setAcc()
    {
        accExists = true;
    }

    public boolean getB()
    {
        return accExists;
    }

    public void initUserID()
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("users/ID-Counter");
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();
                userID = Integer.parseInt("" + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });
    }

    public void initChatID()
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("chats/ID-Counter");
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();
                chatID = Integer.parseInt("" + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });
    }

    public void createUser(String userName, String pass, String ID)
    {
        u = new User(userName, pass , ID , this);
    }

    /*public void createChat(ArrayList<Message> messages, String ID, ArrayList<User> users)
    {
        c = new Chat(messages, ID, users);
    }*/

    public void add(String userId , String path , String id) 
    {
        DatabaseReference user = userDB.child(userId).child(path) ;
        user.child(id).setValueAsync("") ;
    }

    public User getUser(){
        System.out.println(u);
        return u;
    }
    public String getCurrentID(){
        return u.getID();
    }
}