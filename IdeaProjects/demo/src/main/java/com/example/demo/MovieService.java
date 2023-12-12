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
import java.util.ArrayList;
import java.util.List;



public class MovieService {

    private static final String TMDB_API_KEY = "66c5cf5c1f248b705be57383ee7906ae";
    private static final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/";

    public static void main(String[] args) throws IOException {
        initializeFirebase();
        String searchQuery = "action"; 
        fetchAndStoreMovies(searchQuery); // Replace 123 with an actual movie ID
        searchQuery = "thriller";
        fetchAndStoreMovies(searchQuery);

    }

    public static void fetchAndStoreMovies(String searchQuery) throws IOException {
        String apiUrl = "https://api.themoviedb.org/3/search/movie" +
                "?api_key=" + TMDB_API_KEY +
                "&query=" + searchQuery;

        String jsonResponse = makeHttpRequest(apiUrl);

        List<Movie> movies = parseSearchResults(jsonResponse, searchQuery);
        System.out.println(movies);
        for (Movie movie : movies) {
            //if(movie.getTitle().con)
            storeMovieInDatabase(movie);
        }
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

    /* 
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
    }*/

    private static String getGenreFromJson(JsonNode jsonNode) {
        StringBuilder genres = new StringBuilder();
        JsonNode genresArray = jsonNode.path("genres");
        genres.append(genresArray.get(0).path("name").asText());
           
        return genres.length() > 0 ? genres.substring(0, genres.length()) : "";
    }

    private static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("demo\\serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://movie-break-3650d-default-rtdb.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase: " + e.getMessage());
        }
    }

    private static void storeMovieInDatabase(Movie movie) {
        try {
            DatabaseReference moviesRef = FirebaseDatabase.getInstance().getReference("movies").push();
            moviesRef.setValueAsync(movie)
                .get(); // Wait for the operation to complete
            System.out.println("Movie data saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to save movie data to Firebase: " + e.getMessage());
        }
    }

    private static List<Movie> parseSearchResults(String jsonResponse, String searchQ) {
        List<Movie> movies = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            JsonNode results = jsonNode.path("results");

            for (JsonNode movieNode : results) {
                int id = movieNode.path("id").asInt();
                String title = movieNode.path("original_title").asText();
                Movie movie = new Movie(id, title, searchQ); 
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }
}    