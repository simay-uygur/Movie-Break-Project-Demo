package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

import java.io.FileInputStream;
import java.io.IOException;



public class MovieService {

    private static final String TMDB_API_KEY = "66c5cf5c1f248b705be57383ee7906ae";
    private static final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/";

    public static void main(String[] args) throws IOException {
        fetchAndStoreMovieData(135397); // Replace 123 with an actual movie ID
    }

    public static void fetchAndStoreMovieData(int movieId) throws IOException {
        String apiUrl = TMDB_API_URL + movieId + "?api_key=" + TMDB_API_KEY;
        String jsonResponse = makeHttpRequest(apiUrl);

        Movie movie = parseJsonResponse(jsonResponse);
        System.out.println(movie);

        storeMovieInDatabase(movie);
    }

    private static String makeHttpRequest(String apiUrl) throws IOException {
        // Implement HTTP request logic using a library
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
        //https://api.themoviedb.org/3/configuration
                .url(apiUrl)
                .get()
                //.addHeader("accept", "application/json")
                //.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NmM1Y2Y1YzFmMjQ4YjcwNWJlNTczODNlZTc5MDZhZSIsInN1YiI6IjY1Nzc3NzczYmJlMWRkMDBhYzdkMmJiNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GM-DfhStzBQvUN5nfEQqaWhN44hDaVnrkxFRxqF0BSY")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
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
        StringBuilder genres = new StringBuilder();
        JsonNode genresArray = jsonNode.path("genres");
        for (JsonNode genre : genresArray) {
            genres.append(genre.path("name").asText()).append(", ");
        }
        return genres.length() > 0 ? genres.substring(0, genres.length() - 2) : "";
    }

    private static void storeMovieInDatabase(Movie movie) {
        try {
        if (FirebaseApp.getApps().isEmpty()) { // Check if FirebaseApp is already initialized
            FileInputStream serviceAccount = new FileInputStream("Movie-Break-Project-Demo\\Movie-Break-Project-Demo\\IdeaProjects\\demo\\serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://movie-break-3650d-default-rtdb.firebaseio.com/")
                .build();
            FirebaseApp.initializeApp(options);
        }
        DatabaseReference moviesRef = FirebaseDatabase.getInstance().getReference("movies");
        moviesRef.child(String.valueOf(movie.getId())).setValueAsync(movie); // Using movie ID as the key
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
}    