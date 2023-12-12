module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.auth.oauth2;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires com.google.api.apicommon;
    requires google.cloud.core;
    requires com.google.auth;
<<<<<<< HEAD
    requires java.net.http;
=======
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
>>>>>>> a36b399e4224feab03d54dc0ef566ce36a1e7c84
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}