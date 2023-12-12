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
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}