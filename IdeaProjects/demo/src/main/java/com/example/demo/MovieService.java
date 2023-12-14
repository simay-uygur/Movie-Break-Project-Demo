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

public class MovieService 
{
    private final static String letters = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm?!:;() " ; 
    private static final String TMDB_API_KEY = "66c5cf5c1f248b705be57383ee7906ae";
    private static final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/";
    // TMDb'nin resimler için temel URL'i
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    public static void main(String[] args) throws IOException {
        initializeFirebase();
        String genreId = "28"; // Aksiyon türünün ID'si
        fetchAndStoreMovies(genreId);
        genreId = "12"; //Adventure
        fetchAndStoreMovies(genreId);
        /*genreId = "16"; // Animation
        fetchAndStoreMovies(genreId);
        genreId = "35"; //Comedy
        fetchAndStoreMovies(genreId);
        genreId = "80"; //Crime
        fetchAndStoreMovies(genreId);
        genreId = "18" ; //Drama
        fetchAndStoreMovies(genreId);
        genreId = "10749"; //Romance
        fetchAndStoreMovies(genreId);
        genreId = "878"; //Science Fiction
        fetchAndStoreMovies(genreId);
        genreId = "27"; //horror
        fetchAndStoreMovies(genreId);
        genreId = "99"; //docummentary
        fetchAndStoreMovies(genreId);*/

    }
    // Poster yolu ve temel URL'i birleştiren fonksiyon

    // TMDb'nin resimler için temel URL'i
    
    // Poster yolu ve temel URL'i birleştiren fonksiyon
    private static String getFullImageUrl(String posterPath) {
        if (posterPath != null && !posterPath.isEmpty()) {
            return BASE_IMAGE_URL + posterPath;
        } else {
            return null; // ya da varsayılan bir resim URL'si
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
            FileInputStream serviceAccount = new FileInputStream("IdeaProjects\\demo\\serviceAccountKey.json");

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
            DatabaseReference moviesRef = FirebaseDatabase.getInstance().getReference("movies");
            moviesRef.child(movie.takeId()+"").setValueAsync(movie).get() ;
            System.out.println(movie);
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
                String posterPath = getFullImageUrl(movieNode.path("poster_path").asText()); // Tam URL elde ediliyor

                if(searchQ.equals("28")){
                    searchQ = "action";
                }
                if(searchQ.equals("12")){
                    searchQ = "adventure";
                }
                if(searchQ.equals("16")){
                    searchQ = "animation";
                }
                if(searchQ.equals("35")){
                    searchQ = "comedy";
                }
                if(searchQ.equals("80")){
                    searchQ = "crime";
                }
                if(searchQ.equals("18")){
                    searchQ = "drama";
                }
                if(searchQ.equals("10749")){
                    searchQ = "romance";
                }
                if(searchQ.equals("878")){
                    searchQ = "science fiction";
                }
                if(searchQ.equals("27")){
                    searchQ = "horror";
                } 
                if(searchQ.equals("99")){
                    searchQ = "documentary";
                }                               
                Movie movie = new Movie(id, title, searchQ, posterPath); 
                movies.add(movie);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static boolean correctTitle(String title) 
    {
        for (int i = 0 ; i < title.length() - 1 ; i++)
        {
            if (!letters.contains(title.substring(i, i+1))) return false ;
        }
        return true ;
    }
}    