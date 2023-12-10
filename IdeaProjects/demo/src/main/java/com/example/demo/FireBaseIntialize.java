package com.example.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;

public class FireBaseIntialize {
    public FireBaseIntialize()
    {

    }
    public void initialize() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("C:\\Users\\perha\\IdeaProjects\\demo\\serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://movie-break-3650d-default-rtdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
