module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.auth.oauth2;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires com.google.api.apicommon;
    requires google.cloud.core;
    requires com.google.auth;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;

    opens com.example.demo to javafx.fxml, firebase.admin; // Open to Firebase
    exports com.example.demo;
}
