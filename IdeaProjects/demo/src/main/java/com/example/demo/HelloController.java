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

public class HelloController{
    private static int id;
    private Scene scene ;
    private Stage stage ;
    private Parent root ;
    Firebase fb = new Firebase();
    @FXML
    private Button insert ;

    @FXML
    private Button backIn ;

    @FXML
    private  Button backUp ;

    @FXML
    private TextField pass ;
    @FXML
    private TextField userN ;

    @FXML
    private VBox friends ;

    @FXML
    private Label label ;

    @FXML
    private Button in ;

    @FXML
    private Button up ;

    @FXML
    private ImageView image ;

    @FXML
    private HBox movies ;
    @FXML
    private  Button lArrow;

    @FXML
    private  Button rArrow;

    @FXML
    private StackPane one ;
    @FXML
    private StackPane two ;
    @FXML
    private StackPane three ;
    @FXML
    private StackPane four ;
    @FXML
    private StackPane five ;
    @FXML
    private StackPane six ;



    public void insert(ActionEvent e)
    {
        initId();
    }

    public void check(ActionEvent e) throws IOException {
        fb.hasAcc(userN.getText() , pass.getText() );
        if (fb.getB())
        {
            fb.createUser(userN.getText() , pass.getText() , ""+(id));
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
        fb.createUser(userN.getText() , pass.getText() , ""+(id));
        id++ ;
        fb.push();
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
        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
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