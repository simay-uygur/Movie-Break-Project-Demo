package com.example.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Welcome extends Application {

    @Override

    public void start(Stage stage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\welcomePage.fxml")) ;
        Scene scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }

    public static void initialize() {
        try{
            FileInputStream serviceAccount =
<<<<<<< Updated upstream
                    new FileInputStream("demo\\serviceAccountKey.json");
=======
                    new FileInputStream("IdeaProjects\\demo\\serviceAccountKey.json");
>>>>>>> Stashed changes

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://movie-break-3650d-default-rtdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initialize();
        launch();
    }
}
