package com.example.demo;

import com.example.demo.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

public class HelloController{
    Firebase fb ;
    @FXML
    private Button insert ;

    @FXML
    private TextField pass ;
    @FXML
    private TextField userN ;

    @FXML
    private VBox friends ;

    @FXML
    private Label label ;
    public void insert(ActionEvent e)
    {
        fb = new Firebase(userN.getText() , pass.getText()) ;
        fb.push();
    }

    public void check(ActionEvent e)
    {
        fb = new Firebase(userN.getText() , pass.getText()) ;
        fb.hasAcc();
    }

    public void showUsers(HashMap<String , String> users)
    {
        for (int i = 0 ; i < users.size() ; i++)
        {
            label.setText(users.get(i).toString());
        }
    }



}