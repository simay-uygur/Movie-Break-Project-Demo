package com.example.demo;
import java.util.ArrayList;

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
    public Chat (String ID)
    {
        this.chatID = ID;
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
    public void displayMessages(String currentUserID, TextArea text1, TextArea text2){
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
    }
}
