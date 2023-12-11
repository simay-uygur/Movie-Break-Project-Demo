package com.example.demo;

import com.example.demo.HelloController;
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
    private int id ;
    private  User u ;
    DatabaseReference db ;
    
    public Firebase()
    {
        db = FirebaseDatabase.getInstance().getReference("users") ;
    }

    public void push()
    {
        DatabaseReference user = db.child(u.getID());
        user.child("Username").setValueAsync(u.getName()) ;
        user.child("Password").setValueAsync(u.getPass()) ;
        DatabaseReference idS = db.child("ID-Counter") ;
        idS.setValueAsync(Integer.parseInt(u.getID())+1) ;
    }

    public void hasAcc(String name , String pass)
    {
        users = new HashMap<>() ;
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
        HelloController j = new HelloController() ;
        j.showUsers(users);
    }

    public void setAcc()
    {
        accExists = true ;
    }

    public boolean getB()
    {
        return  accExists ;
    }

    public void initId()
    {
        System.out.println("runned");
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("users/ID-Counter");
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();

                id = Integer.parseInt(""+value) ;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });

    }


    public void createUser(String userName , String pass , String id)
    {
        u = new User(userName , pass , id) ;
    }
}