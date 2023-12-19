package com.example.demo;
import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Chat {
    @FXML private TextArea text1;
    @FXML private TextArea text2;
    Controller controller;
    String chatID;
    ArrayList<String> userIDs;
    ArrayList<Message> messages;
    String message1 = "";
    String message2 = "";
    DatabaseReference chat ;
    private static String userID ;
    private static String friendID ;
    public Chat (String ID , String userID , String friendID)
    {
        this.friendID = friendID ;
        this.userID = userID ;
        this.chatID = ID;
        chat = FirebaseDatabase.getInstance().getReference("chats").child(ID); 
        /*for (int i = 0; i < users.size(); i++)
        {
            userIDs.add(users.get(i));
        }*/
    }
    public String getID()
    {
        return this.chatID;
    }

    public ArrayList<String> getUsers()
    {
        return this.userIDs;
    }
    public ArrayList<Message> getMessages()
    {
        return this.messages;
    }
    /*public void displayMessages(String currentUserID, TextArea text1, TextArea text2){
        for (int i = 0; i < messages.size(); i++) {
            if(messages.get(i).getUserID().equals(currentUserID)){
                message1 += messages.get(i).displayHeadTitle() +"\n"+ messages.get(i).getText()+"\n";
                message2 += "\n\n";
            }
            else{
                message2 +=  messages.get(i).displayHeadTitle() +"\n"+ messages.get(i).getText()+"\n";
                message1 += "\n\n";
            }
        }
        text1.setText(message1);
        text2.setText(message2);
    }*/

    public void setMessages(TextArea f , TextArea u) 
    {
        setMyMessages(u);
        setFriendMessages(f);
    }

    public void setMyMessages(TextArea me)
    {
        chat.child(userID).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot mess : snapshot.getChildren())
                {
                    me.appendText(""+mess.getValue()+"\n");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onCancelled'");
            }
            
        });
    }

    public void setFriendMessages(TextArea friend)
    {
        chat.child(friendID).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot mess : snapshot.getChildren())
                {
                    friend.appendText(""+mess.getValue()+"\n");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
            
        });
    }

    public void add(Message msg) 
    {
        chat.child(userID).setValueAsync(msg) ;
    }
}
