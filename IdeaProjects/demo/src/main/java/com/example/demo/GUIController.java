package com.example.demo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GUIController{
    private static int id;
    private Scene scene ;
    private Stage stage ;
    private Parent root ;
    Firebase fb = new Firebase();
    @FXML
    private Button signIn ;

    @FXML
    private Button backIn ;

    @FXML
    private  Button backUp ;

    @FXML
    private TextField pass ;
    @FXML
    private TextField userN ;

    @FXML
    private TextField pass1 ;
    @FXML
    private TextField userN1 ;

    @FXML
    private Label label ;

    public void insert(ActionEvent e)
    {
        initId();
    }

    public void check(ActionEvent e) throws IOException {
        if (fb.hasAcc(userN.getText() , pass.getText())) 
        {
            root = FXMLLoader.load(getClass().getResource("IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\mainPage.fxml"));
            root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow() ;
            scene = new Scene(root) ;
            stage.setScene(scene);
            stage.show();
        }
    }

    public void initId()
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("users/ID-Counter");
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) 
                {
                    Object value = dataSnapshot.getValue();
                    takeId(value) ;
                }
                else 
                {
                    takeId(0) ;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });
    }

    public void takeId(Object value)
    {
        id = Integer.parseInt(""+value) ;
        fb.push(userN.getText() , pass.getText() , id);
        id++ ;
    }

    public void changeIn(ActionEvent e) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml")) ;
        stage = (Stage)((Node)e.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }

    public void changeUp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }

    public void changeMainPage(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\mainPage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }

    public void backToMain(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }
}