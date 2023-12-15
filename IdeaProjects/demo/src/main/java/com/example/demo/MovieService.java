package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.io.FileInputStream;
import java.net.HttpURLConnection;

import java.util.ArrayList;
import java.util.List;


public class MovieService 
{
     static ArrayList<String>  imageUrls; 
     static ArrayList<String> movieIds; 
    private final static String letters = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm?!:;() " ; 
    private static final String TMDB_API_KEY = "66c5cf5c1f248b705be57383ee7906ae";
    private static final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/";
    // TMDb'nin resimler için temel URL'i
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    public static void main(String[] args) throws IOException {
        imageUrls = new ArrayList<String>();
        movieIds = new ArrayList<String>();
        initializeFirebase();
        String genreId = "28"; // Aksiyon türünün ID'si
        fetchAndStoreMovies(genreId);
        genreId = "12"; //Adventure
        fetchAndStoreMovies(genreId);
        genreId = "16"; // Animation
        fetchAndStoreMovies(genreId);
        genreId = "35"; //Comedy
        fetchAndStoreMovies(genreId);
        genreId = "80"; //Crime
        fetchAndStoreMovies(genreId);
        genreId = "18" ; //Drama
        fetchAndStoreMovies(genreId);
        /* genreId = "10749"; //Romance
        fetchAndStoreMovies(genreId);
        genreId = "878"; //Science Fiction
        fetchAndStoreMovies(genreId);
        genreId = "27"; //horror
        fetchAndStoreMovies(genreId);
        genreId = "99"; //docummentary
        fetchAndStoreMovies(genreId);
        */

        String saveDirectory = "IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\movieImages\\";
        for (int i = 0; i < imageUrls.size(); i++) {
            String imageUrl = imageUrls.get(i);
            String movieId = movieIds.get(i);
            System.out.println(imageUrl+"  id. "+movieId);
            downloadImage(imageUrl, movieId, saveDirectory);
        }
    }

    public static void fetchAndStoreMovies(String genreId) throws IOException {
        String apiUrl = "https://api.themoviedb.org/3/discover/movie" +
        "?api_key=" + TMDB_API_KEY +
        "&with_genres=" + genreId;

        String jsonResponse = makeHttpRequest(apiUrl);
        System.out.println(jsonResponse);
        List<Movie> movies = parseSearchResults(jsonResponse, genreId);
        //System.out.println(movies);
        for (Movie movie : movies) {
            //if(movie.getTitle().con)
            storeMovieInDatabase(movie);
        }
    }

    private static String makeHttpRequest(String apiUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("IdeaProjects\\demo\\serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://movie-break-3650d-default-rtdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase: " + e.getMessage());
        }
    }

    private static void storeMovieInDatabase(Movie movie) {
        try {
            DatabaseReference moviesRef = FirebaseDatabase.getInstance().getReference("movies");
            moviesRef.child(movie.takeId()+"").setValueAsync(movie).get() ;
            System.out.println(movie);
            System.out.println("Movie data saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to save movie data to Firebase: " + e.getMessage());
        }
    }


    public static List<Movie> parseSearchResults(String jsonResponse, String searchQ) {
        List<Movie> movies = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("results");

            for (JsonNode movieNode : resultsNode) {
                int id = movieNode.path("id").asInt();
                String title = movieNode.path("original_title").asText();
                String posterPath = getFullImageUrl(movieNode.path("poster_path").asText());

                String genreName = mapGenreIdToName(searchQ); // Convert genre ID to name

                Movie movie = new Movie(id, title, genreName, posterPath);
                movies.add(movie);

                movieIds.add(Integer.toString(id)); // Collect movie IDs
                if (!posterPath.isEmpty())
                    imageUrls.add(posterPath); // Collect image URLs if available
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    private static String mapGenreIdToName(String genreId) {
        switch (genreId) {
            case "28": return "action";
            case "12": return "adventure";
            case "16": return "animation";
            case "35": return "comedy";
            case "80": return "crime";
            case "18": return "drama";
            case "10749": return "romance";
            case "878": return "science fiction";
            case "27": return "horror";
            case "99": return "documentary";
            default: return genreId; // Return the original ID if it doesn't match
        }
    }

    private static String getFullImageUrl(String partialPath) {
        return "https://image.tmdb.org/t/p/w500" + partialPath;
    }

    public static void downloadImage(String imageUrl, String movieId, String saveDirectory) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(imageUrl))
                .GET()
                .build();

        try {
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() == 200) {
                String fileName = movieId + ".jpg";
                System.out.println("filename = "+ fileName);
                Path savePath = Paths.get(saveDirectory, fileName);
                Files.copy(response.body(), savePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image successfully downloaded and saved: " + fileName);
            } else {
                System.out.println("HTTP request failed. Response code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean correctTitle(String title) 
    {
        for (int i = 0 ; i < title.length() - 1 ; i++)
        {
            if (!letters.contains(title.substring(i, i+1))) return false ;
        }
        return true ;
    }

    
    

    /*public void addIt(int id , Object t , Object g , Object p)
    {
        Movie m = new Movie(id, ""+t, ""+g, ""+p) ;
        movies.add(m) ;
        //System.out.println(movies);
    }

    public void sss()
    {
        takeAllData();
    }*/
}    