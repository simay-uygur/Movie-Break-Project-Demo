package com.example.demo.Firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseDataWriter {
    public void writeToFirebase(String collectionName, String documentId, Map<String, Object> data) {
        initialize();
        Firestore db = FirestoreClient.getFirestore();

        // Create a reference to the document
        DocumentReference docRef = db.collection(collectionName).document(documentId);

        // Write data to the document
        ApiFuture<WriteResult> future = docRef.set(data);

        try {
            // Wait for the write to complete
            WriteResult result = future.get();
            System.out.println("Write completed at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Example usage:
    public static void main(String[] args) {
        FirebaseDataWriter dataWriter = new FirebaseDataWriter();

        // Example data to be written
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Perhat Amanlyyev");
        data.put("age", 45);
        data.put("email", "pipidastr@gmail.com");

        // Write data to the com.example.demo.Firebase Realtime Database
        dataWriter.writeToFirebase("users", "users", data);
    }

    public static void initialize() {
        try {
            System.out.println("FirebaseDataWriter.initialize");
            FileInputStream serviceAccount =
                    new FileInputStream("C:\\Users\\perha\\IdeaProjects\\demo\\serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://movie-break-3650d-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
