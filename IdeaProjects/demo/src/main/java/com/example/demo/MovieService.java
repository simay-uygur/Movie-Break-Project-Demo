package com.example.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import okhttp3.OkHttpClient;
import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import okhttp3.Request;
import okhttp3.Response;



// ... (import statements for HTTP, JSON libraries)

public class MovieService {

    private static final String TMDB_API_KEY = "66c5cf5c1f248b705be57383ee7906ae";
    private static final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/";

    public static void main(String[] args) throws IOException {
        fetchAndStoreMovieData(123); // Replace 123 with an actual movie ID
    }

    public static void fetchAndStoreMovieData(int movieId) throws IOException {
        // Make HTTP request to TMDB API
        String apiUrl = TMDB_API_URL + movieId + "?api_key=" + TMDB_API_KEY;
        String jsonResponse = makeHttpRequest(apiUrl);

        // Parse JSON response
        Movie movie = parseJsonResponse(jsonResponse);

        // Store in Firebase Realtime Database
        storeMovieInDatabase(movie);
    }

    private static String makeHttpRequest(String apiUrl) throws IOException {
        // Implement HTTP request logic using a library
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/configuration")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NmM1Y2Y1YzFmMjQ4YjcwNWJlNTczODNlZTc5MDZhZSIsInN1YiI6IjY1Nzc3NzczYmJlMWRkMDBhYzdkMmJiNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GM-DfhStzBQvUN5nfEQqaWhN44hDaVnrkxFRxqF0BSY")
        .build();

        Response response = client.newCall(request).execute();

        return ""; // Replace with actual response
    }

    private static Movie parseJsonResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // Extract data from JSON and create a Movie object
            String name = jsonNode.path("original_title").asText(); // Adjust the field name based on the API response
            int id = jsonNode.path("id").asInt();
            String genre = getGenreFromJson(jsonNode);

            return new Movie(id, name, genre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getGenreFromJson(JsonNode jsonNode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGenreFromJson'");
    }

    private static void storeMovieInDatabase(Movie movie) {
        try {
            FileInputStream serviceAccount = new FileInputStream("path/to/your/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://your-project-id.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);

            DatabaseReference moviesRef = FirebaseDatabase.getInstance().getReference("movies");
            moviesRef.child(movie.getTitle()).setValue(movie);
            //get title olayını anlamadım daha
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
