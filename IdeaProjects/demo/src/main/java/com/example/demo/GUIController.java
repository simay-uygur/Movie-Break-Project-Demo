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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
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
    private TextField ar;
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
    private Text message ;

    @FXML
    private ImageView profilePhoto ;

    public void insert(ActionEvent e)
    {
        if (userN.getText().equals("") || pass.getText().equals("")) 
        {
            message.setText("Empty Password or Username");
            message.setFill(Color.rgb(139,0,0));
        }
        else
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
    }

    public void check(ActionEvent e) throws IOException {
        if (fb.hasAcc(userN.getText() , pass.getText() ))
        {
            changeMainPage(e);
        }
    }

    public void takeUserID(Object value)
    {
        id = Integer.parseInt(""+value) ;
        if (fb.userPush(userN.getText() , pass.getText() , id)) 
        {
            message.setVisible(true);
            message.setText("You are signed up");
            message.setFill(Color.rgb(34,139,34));
        }
        else 
        {
            message.setVisible(true);
            message.setText("Username already exists");
            message.setFill(Color.rgb(139,0,0));
            Alert alert = new Alert(AlertType.INFORMATION);
            
            alert.setTitle("Mesaj Başliği");
            alert.setHeaderText(null);
            alert.setContentText("Bu bir bilgilendirme mesajidir.");
            alert.showAndWait();
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
        //display();
        stage.show();
    }

    public void changeMainPage(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void backToMain(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openProfileSettings(ActionEvent e) throws IOException 
    {
        root = FXMLLoader.load(getClass().getResource("profilePage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setProfImg() 
    {
        Circle c = new Circle(50) ;
        profilePhoto.setClip(c); 
    }
    public void loadMoviePoster() {
        /*DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference("movies/1060090/path"); 
        movieRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String posterPath = dataSnapshot.getValue(String.class);
                displayMoviePoster(posterPath);
            }
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });*/
    }

    
        /*public void check(ActionEvent e) throws IOException {
        if (fb.hasAcc(userN.getText() , pass.getText() ))
        {
            /*System.out.println("artik");
            fb.createUser(userN.getText() , pass.getText() , ""+(id));
            root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            //display();
           //oot = loader.load();
            //GUIController controller = loader.getController(); // Get the controller associated with mainPage.fxml
            //controller.loadMoviePoster(); // Call the method to load the movie poster

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println(Label.getText());
            display();// 
            fb.createUser(userN.getText() , pass.getText() , ""+(id));
            //display();
            root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow() ;
            scene = new Scene(root) ;
            stage.setScene(scene);
            stage.show();
        }
    }*/
    /*public void check(ActionEvent e) throws IOException {
        if (fb.hasAcc(userN.getText(), pass.getText())) {
            fb.createUser(userN.getText(), pass.getText(), "" + (id));
    
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainPage.fxml"));
            loader.setController(this);
            root = loader.load();
    
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }*/
    
}