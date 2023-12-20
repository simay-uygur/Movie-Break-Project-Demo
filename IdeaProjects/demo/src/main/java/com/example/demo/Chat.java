package com.example.demo;
import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Chat {
    private static String counter = "1" ; 
    String chatID;
    DatabaseReference chat ;
    private static String userID ;
    private static String userName ;
    private Timer timer ;
    private static String friendID ;
    private TextArea friend ;
    public Chat (String ID , String userID , String friendID , TextArea friend)
    {
        this.friend = friend ;
        this.friendID = friendID ;
        this.userID = userID ;
        this.chatID = ID;
        timer = new Timer() ;
        chat = FirebaseDatabase.getInstance().getReference("chats").child(ID);
        setCounter();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                friend.setText("");
                chat.child(friendID).addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    String frMsg = "" ;
                    for (DataSnapshot mess : snapshot.getChildren())
                    {
                        if (!mess.getKey().equals("Counter"))
                        {
                            frMsg += "\n"+mess.getValue()+"\n" ;
                        }
                    }
                    friend.appendText(frMsg);
                }
                
                @Override
                public void onCancelled(DatabaseError error) {
                }
            
                });
            }
            
        }, 0, 5000);
    }
    
    public String getID()
    {
        return this.chatID;
    }

    public void setMessages(TextArea u) 
    {
        setMyMessages(u);
    }

    public void setMyMessages(TextArea me)
    {
        chat.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String myMsg = "" ;
                for (DataSnapshot mess : snapshot.getChildren())
                {
                    if (!mess.getKey().equals("Counter"))
                    {
                        myMsg += "\n"+mess.getValue()+"\n" ;
                    }
                }
                me.appendText(myMsg);
            }
            
            @Override
            public void onCancelled(DatabaseError error) {
            }
            
        });
    }

    public void add(Message msg) 
    {
        chat.child(userID).child(counter).setValueAsync(msg.toString()) ;
        chat.child(userID).child("Counter").setValueAsync(Integer.parseInt(counter)+1) ;
        counter = ""+(Integer.parseInt(counter)+1) ;
    }

    public void setCounter() 
    {
        chat.child(userID).child("Counter").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) 
                {
                    set(""+snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onCancelled'");
            }
            
        });
    }

    public void set(String c) 
    {
        counter = c ;
    }
}
