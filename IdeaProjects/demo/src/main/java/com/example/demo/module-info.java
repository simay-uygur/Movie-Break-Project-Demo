module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.auth.oauth2;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires com.google.api.apicommon;
    requires google.cloud.core;
    requires com.google.auth;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires java.desktop;
    requires java.net.http;
    requires javafx.swing; 
    
    opens com.example.demo to javafx.fxml, firebase.admin;
    exports com.example.demo;
}
