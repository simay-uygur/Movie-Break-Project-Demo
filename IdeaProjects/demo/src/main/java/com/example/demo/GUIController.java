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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GUIController{
    private static int id;
    private Scene scene;
    private Stage stage;
    private Parent root;
    Firebase fb = new Firebase();
    @FXML
    private Button insert;

    @FXML
    private Button backIn;

    @FXML
    private  Button backUp;

    @FXML
    private TextField pass;
    @FXML
    private TextField userN;

    @FXML
    private VBox friends;

    @FXML
    private Label label;

    @FXML
    private Button in;

    @FXML
    private Button up;

    @FXML
    private ImageView image;

    @FXML 
    private Label message ;

    public void insert(ActionEvent e)
    {
        initId();
    }

    public void check(ActionEvent e) throws IOException {
        String name = userN.getText() ;
        String passw = pass.getText() ;
        System.out.println(id);
        if (fb.hasAcc(name , passw))
        {
            fb.createUser(name , passw , ""+(id));
            root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            loadMoviePoster();
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
                    takeUserID(value);
                }
                else 
                {
                    takeUserID(0);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });
    }

    public void takeUserID(Object value)
    {
        id = Integer.parseInt(""+value) ;
        if (fb.userPush(userN.getText() , pass.getText() , id)) 
        {
            message.setVisible(true);
            message.setText("You are signed up");
            message.setTextFill(Color.rgb(34,139,34));
        }
        else 
        {
            message.setVisible(true);
            message.setText("Username already exists");
            message.setTextFill(Color.rgb(139,0,0));
        }
    }

    public void changeIn(ActionEvent e) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }

    public void changeUp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
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
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadMoviePoster() {
        DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference("movies/1060090/path"); 
        displayMoviePoster(movieRef.child("path").getKey() );
    }

    public void displayMoviePoster(String posterPath) {
        System.out.println(posterPath);
        try {
            if (posterPath != null && !posterPath.isEmpty()) {
                Image posterImage = new Image(posterPath);
                image.setImage(posterImage); // 'image' is the name of your ImageView component.
            } else {
                System.err.println("posterPath is empty or null.");
            }
        } catch (Exception e) {
            System.err.println("Error loading and displaying the image: " + e.getMessage());
        }
    }  
    


}