package com.example.demo;

import com.example.demo.HelloController;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.firebase.messaging.Message;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Firebase {
    private boolean accExists = false;
    HashMap<String, String> users;
    private int userID;
    private int chatID;
    private User u;
    private Chat c;
    DatabaseReference userDB;
    DatabaseReference chatDB;
    
    public Firebase()
    {
        userDB = FirebaseDatabase.getInstance().getReference("users");
        chatDB = FirebaseDatabase.getInstance().getReference("chats");
    }

    public void userPush()
    {
        DatabaseReference user = userDB.child(u.getID());
        user.child("Username").setValueAsync(u.getName());
        user.child("Password").setValueAsync(u.getPassword());
        DatabaseReference userIDs = userDB.child("ID-Counter");
        userIDs.setValueAsync(Integer.parseInt(u.getID()) + 1);
    }

    public void chatPush()
    {
        DatabaseReference chat = chatDB.child(c.getID());
        chat.child("messages").setValueAsync(c.getMessages());
        DatabaseReference chatIDs = chatDB.child("ID-Counter");
        chatIDs.setValueAsync(Integer.parseInt(c.getID()) + 1);
    }

    public void hasAcc(String name, String pass)
    {
        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren())
                {
                    if  (
                            userSnapshot.child("Username").getValue().equals(name) &&
                            userSnapshot.child("Password").getValue().equals(pass)
                        )
                    {
                        setAcc();
                        return;
                    }
                }
                System.out.println("New account?");
                return;
            }

            @Override
            public void onCancelled(DatabaseError error) {

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
        u = new User(userName, pass, ID);
    }

    public void createChat(ArrayList<Message> messages, String ID, ArrayList<User> users)
    {
        c = new Chat(messages, ID, users);
    }
}