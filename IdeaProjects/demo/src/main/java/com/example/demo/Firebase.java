package com.example.demo;

import com.example.demo.GUIController;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Firebase {
    private boolean accExists = false ;
    HashMap<String, String> users ;
    private  User u ;
    DatabaseReference db ;
    
    public Firebase()
    {
        db = FirebaseDatabase.getInstance().getReference("users") ;
    }

    public void push(String username , String password , int id)
    {
        DatabaseReference user = db.child(""+id);
        user.child("Username").setValueAsync(username) ;
        user.child("Password").setValueAsync(password) ;
        DatabaseReference idS = db.child("ID-Counter") ;
        idS.setValueAsync(id+1) ;
    }

    public boolean hasAcc(String name , String pass)
    {
        db.addValueEventListener(new ValueEventListener() {
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
        return accExists;
    }

    public void setAcc()
    {
        accExists = true ;
    }

    public boolean getB()
    {
        return  accExists ;
    }

    public void createUser(String userName , String pass , String id)
    {
        u = new User(userName , pass , id , this) ;
    }

    public void addFriend(String userId , String friendID) 
    {
        DatabaseReference friend = db.child(userId) ;
        friend.child("Friends").setValueAsync(friendID) ;
    }
}