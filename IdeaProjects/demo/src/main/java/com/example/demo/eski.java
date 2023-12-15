package com.example.demo;

public class eski {
        /*private static List<Movie> parseSearchResults(String jsonResponse, String searchQ) {
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
                movieIds.add(Integer.toString(id));
                if(!posterPath.isEmpty())
                    imageUrls.add(posterPath);
                movies.add(movie);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }*/
    /* 
    public void refreshMovie(ActionEvent e ) throws IOException{
        
        String[] ids = {"76600", "299054", "335977", "346698", "385687"};

        CompletableFuture<String> cposter = new CompletableFuture<>();
        String path = "";

        CompletableFuture<String> ctitle = new CompletableFuture<>();
        String title = "";

        for(int i=0; i<5; i++){
            if(i==0){

                cposter = loadMoviePoster(ids[i]);
                path = cposter.join();

                ctitle = loadMovieName(ids[i]);
                title = ctitle.join();
                    try {
                    if (path != null && !path.isEmpty()) {
                        Image posterImage = new Image(path);
                        view1.setImage(posterImage);
                        label1.setText(title);
                        
                    } else {
                        System.err.println("posterPath is empty or null.");
                    }
                } catch (Exception m) {
                    System.err.println("Error loading and displaying the image: " + m.getMessage());
                }
            }
            else if(i==1){
                cposter = loadMoviePoster(ids[i]);
                path = cposter.join();

                ctitle = loadMovieName(ids[i]);
                title = ctitle.join();
                    try {
                    if (path != null && !path.isEmpty()) {
                        Image posterImage = new Image(path);
                        view2.setImage(posterImage);
                        label2.setText(title);

                    } else {
                        System.err.println("posterPath is empty or null.");
                    }
                } catch (Exception m) {
                    System.err.println("Error loading and displaying the image: " + m.getMessage());
                }
            }
            else if (i==2){
                cposter = loadMoviePoster(ids[i]);
                path = cposter.join();

                ctitle = loadMovieName(ids[i]);
                title = ctitle.join();
                    try {
                    if (path != null && !path.isEmpty()) {
                        Image posterImage = new Image(path);
                        view3.setImage(posterImage);
                        label3.setText(title);

                    } else {
                        System.err.println("posterPath is empty or null.");
                    }
                } catch (Exception m) {
                    System.err.println("Error loading and displaying the image: " + m.getMessage());
                }
            }
            else if (i==3){
                cposter = loadMoviePoster(ids[i]);
                path = cposter.join();

                ctitle = loadMovieName(ids[i]);
                title = ctitle.join();
                    try {
                    if (path != null && !path.isEmpty()) {
                        Image posterImage = new Image(path);
                        view4.setImage(posterImage);
                        label4.setText(title);

                    } else {
                        System.err.println("posterPath is empty or null.");
                    }
                } catch (Exception m) {
                    System.err.println("Error loading and displaying the image: " + m.getMessage());
                }
            }
            else {
                cposter = loadMoviePoster(ids[i]);
                path = cposter.join();

                ctitle = loadMovieName(ids[i]);
                title = ctitle.join();
                    try {
                    if (path != null && !path.isEmpty()) {
                        Image posterImage = new Image(path);
                        view5.setImage(posterImage);
                        label5.setText(title);

                    } else {
                        System.err.println("posterPath is empty or null.");
                    }
                } catch (Exception m) {
                    System.err.println("Error loading and displaying the image: " + m.getMessage());
                }
            }

        }
        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        if (stage != null) {
            stage.setScene(scene);
            stage.show();
        } else {
            System.err.println("Stage is null");
        }            
        System.out.println("runned");
    }

    public CompletableFuture<String> loadMoviePoster(String movieId) {
        DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference("movies/" + movieId + "/path");

        CompletableFuture<String> future = new CompletableFuture<>();

        movieRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String posterPath = dataSnapshot.getValue(String.class);
                if (posterPath != null) {
                    future.complete(posterPath);
                } else {
                    future.completeExceptionally(new Exception("Path not found"));
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new Exception(databaseError.getMessage()));
            }
        });

    return future;
    }

    public CompletableFuture<String> loadMovieName(String movieId) {
        DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference("movies/" + movieId + "/title");

        CompletableFuture<String> future = new CompletableFuture<>();

        movieRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String movietitle = dataSnapshot.getValue(String.class);
                if (movietitle != null) {
                    future.complete(movietitle);
                } else {
                    future.completeExceptionally(new Exception("title not found"));
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new Exception(databaseError.getMessage()));
            }
        });

    return future;
    }

*/

}
