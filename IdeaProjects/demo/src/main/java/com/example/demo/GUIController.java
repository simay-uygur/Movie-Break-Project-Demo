package com.example.demo;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

import com.example.demo.Firebase.FirebaseDataCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.security.cert.X509CRL;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.text.DefaultEditorKit.CutAction;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
public class GUIController {
    private static User currentUser ; 
    private static Chat privateChat ;
    private static int id;
    private Scene scene;
    private Stage stage;
    private Parent root;
    private static ArrayList<String> friendsIDs ;
    private static ArrayList<String> ChatIDs;
    private static ArrayList<String> favMoviesIDs ;
    private static ArrayList<User> users ;
    private ArrayList<Movie> moviesStore;
    private static int disp = 0 ;
    private static int friendsIndex = 0 ;
    private static String[] chatFriendList = new String[5] ;
    Firebase fb = new Firebase(new FirebaseDataCallback() {
        @Override
        public void onDataLoaded(ArrayList<Movie>movies) {
            moviesStore = movies;
        }

        @Override
        public void onUserLoaded(User user) {
            currentUser = user ;
        }

        @Override
        public void onFav_MoviesIDSloaded(ArrayList<String> datas) {
            favMoviesIDs = datas ;
        }

        @Override
        public void onUsersLoaded(ArrayList<User> Users) {
            users = Users ;
        }

        @Override
        public void onFriendsLoaded(ArrayList<String> friendIDs) {
            friendsIDs = friendIDs ;
        }
    });
    @FXML private Button friend1, friend2, friend3, friend4, friend5;
    @FXML private TextArea chatText1;
    @FXML private ComboBox<String> menu;
    @FXML private Button insert;
    @FXML private Button backIn;
    @FXML private Button backUp;
    @FXML private TextField pass;
    @FXML private TextField ar;
    @FXML private TextField userN;
    @FXML private VBox friends;
    @FXML private Button in;
    @FXML private Button up;
    @FXML private ImageView profilePhoto;
    @FXML private Label label1;
    @FXML private ImageView view1;
    @FXML private Label label2;
    @FXML private ImageView view2;
    @FXML private Label label3;
    @FXML private ImageView view3;
    @FXML private Label label4;
    @FXML private ImageView view4;
    @FXML private Label label5;
    @FXML private ImageView view5;
    @FXML private Label label6;
    @FXML private ImageView view6;
    @FXML private Label label7;
    @FXML private ImageView view7;
    @FXML private Label label8;
    @FXML private ImageView view8;
    @FXML private Label label9;
    @FXML private ImageView view9;
    @FXML private Label label10;
    @FXML private ImageView view10;
    @FXML private Button movierefreshbutton;
    @FXML private Button friendrefreshbutton;
    @FXML private TextField movieSearchTextField;
    @FXML private Text message;
    @FXML private ListView<Movie> searchResultsListView; 
    @FXML private TextField userSearchTextField; 
    @FXML private Button b1; 
    @FXML private Button b2;
    @FXML private Button b3;
    @FXML private Button b4;
    @FXML private Button b5;
    @FXML private Button b6;
    @FXML private Button b7;        
    @FXML private Button b8;
    @FXML private Button b9;
    @FXML private Button b10;   

    @FXML private MenuItem addToFav0,addToFav1,addToFav2,addToFav3,addToFav4,addToFav5,addToFav6,addToFav7,addToFav8,addToFav9;
    @FXML private TextArea friendChat , myChat ;
    @FXML private Button send ;
    @FXML private TextArea textToSend ;
    private List<User> usersStore; 
    private ObservableList<String> movieIds = FXCollections.observableArrayList();   
    private ObservableList<String> userIds = FXCollections.observableArrayList();  
    private String user;
    private int index = 0;
    @FXML private Button left , right ;
    private int smcounter = 0;
    private int sucounter = 0;

    private int sCounterMovie = 0;

    String[] x = new String[5];
    String[] y = new String[5];


    String[] k = new String[5];
    String[] l = new String[5];


    private String[] movieIDs = new String[5];
    //private static String[] movieIDs = new String[5];

    private List<Movie> performMovieSearch(String searchText) {
        String trimmedSearchText = searchText.trim().toLowerCase();
        // Arama teriminin film adında herhangi bir yerde olup olmadığını kontrol etmek için bir filtre kullanın
        List<Movie> searchResults = moviesStore.stream()
            .filter(movie -> movie.getTitle().toLowerCase().contains(trimmedSearchText))
            .collect(Collectors.toList());
        return searchResults;
        
    }
    @FXML
    private void handleMovieSearch(ActionEvent event) {
        smcounter =0;
        movieIds.clear();
        String searchText = movieSearchTextField.getText().trim();
        if (!searchText.isEmpty()) {
            List<Movie> searchResults = performMovieSearch(searchText);
            for (Movie movie : searchResults) {
                movieIds.add(""+movie.takeId());
            }
        }
        while (movieIds.size()%10 !=0 ) {
            movieIds.add("000000");
        }

        System.out.println("searched movie ids  "+movieIds);

        for(int a=0; a<10; a++){
            if(a<5){
                x[a] = movieIds.get(a);
            }
            else{
                y[a-5] = movieIds.get(a);
            }
        }
        helperChangeMovie1(x);
        helperChangeMovie2(y);
    }
    public void moveForwardMovieSearch(ActionEvent e) {
        if (smcounter <= movieIds.size()/ 10) { 
            smcounter++;
    
            int startIndex = 10 * smcounter;
    
            for (int a = 0; a < 10; a++) {
                if (startIndex + a < movieIds.size()) {
                    if (a < 5) {
                        x[a] = movieIds.get(startIndex + a);
                    } else {
                        y[a - 5] = movieIds.get(startIndex + a);
                    }
                } else {
                    if (a < 5) {
                        x[a] = "0000";
                    } else {
                        y[a - 5] = "0000";
                    }
                }
            }
            helperChangeMovie1(x);
            helperChangeMovie2(y);
        }
    }
    public void moveBackwardMovieSearch(ActionEvent e) {
        if (smcounter > 0) {
            smcounter--;

            int startIndex = 10 * smcounter;

            for (int a = 0; a < 10; a++) {
                if (startIndex + a < movieIds.size()) {
                    if (a < 5) {
                        x[a] = movieIds.get(startIndex + a);
                    } else {
                        y[a - 5] = movieIds.get(startIndex + a);
                    }
                } else {
                    // Handle the case when you go before the start of the list
                    if (a < 5) {
                        x[a] = "0000"; // Fill with "0000" or any other placeholder
                    } else {
                        y[a - 5] = "000000"; 
                    }
                }
            }
            helperChangeMovie1(x);
            helperChangeMovie2(y);
        }
    }
    public List<User> performUserSearch(String searchText) {
        String trimmedSearchText = searchText.trim().toLowerCase();
        List<User> searchResults = users.stream()
            .filter(user -> !user.getName().equals(currentUser.getName()) && user.getName().toLowerCase().contains(trimmedSearchText))
            .collect(Collectors.toList());
        return searchResults;
    }

    @FXML
    private void handleUserSearch(ActionEvent event) {
        sucounter = 0;
        userIds.clear();
        String searchText = userSearchTextField.getText().trim();
        userIds.clear();
        if (!searchText.isEmpty()) {
            List<User> searchResults = performUserSearch(searchText);
            userIds = FXCollections.observableArrayList();
            for (User user : searchResults) {
                userIds.add(""+user.getID());
            }
        }
        if(userIds.size() ==0){
            userIds.add("000000");
        }
        while (userIds.size()%10 !=0 ) {
            userIds.add("000000");
        }
        for(int a=0; a<10; a++){
            if(a<5){
                k[a] = userIds.get(a);
            }
            else{
                l[a-5] = userIds.get(a);
            }
        }
        helperChangeUser1(k);
        helperChangeUser2(l);
    }
    
    public void moveForwardUserSearch(ActionEvent e) {
        if (sucounter <= userIds.size()/ 10) { 
            sucounter++;
    
            int startIndex = 10 * sucounter;
    
            for (int a = 0; a < 10; a++) {
                if (startIndex + a < userIds.size()) {
                    if (a < 5) {
                        k[a] = userIds.get(startIndex + a);
                    } else {
                        l[a - 5] = userIds.get(startIndex + a);
                    }
                } else {
                    if (a < 5) {
                        k[a] = "000000";
                    } else {
                        l[a - 5] = "000000";
                    }
                }
            }
            helperChangeUser1(k);
            helperChangeUser2(l);
            //integer bölmesi yap ki devam etmesim e!n son!!!!!!!!
        }
    }

    public void moveBackwardUserSearch(ActionEvent e) {
        if (smcounter > 0) {
            smcounter--;

            int startIndex = 10 * smcounter;

            for (int a = 0; a < 10; a++) {
                if (startIndex + a < userIds.size()) {
                    if (a < 5) {
                        k[a] = userIds.get(startIndex + a);
                    } else {
                        l[a - 5] = userIds.get(startIndex + a);
                    }
                } else {
                    if (a < 5) {
                        k[a] = "000000"; 
                    } else {
                        l[a - 5] = "000000"; 
                    }
                }
            }
            helperChangeUser1(k);
            helperChangeUser2(l);            
        }
    }

    public void helperChangeMovie1(String[] ids) {
        CompletableFuture<String> ctitle = new CompletableFuture<>();
        String title = "";
        for (int i = 0; i < ids.length; i++) {
            BufferedImage cposter = loadMoviePoster(ids[i]);
            ctitle = loadMovieName(ids[i]);
            title = ctitle.join();
            if (i == 0) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view1.setImage(posterImage);
                label1.setText(title);
            } else if (i == 1) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view2.setImage(posterImage);
                label2.setText(title);
            } else if (i == 2) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view3.setImage(posterImage);
                label3.setText(title);
            } else if (i == 3) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view4.setImage(posterImage);
                label4.setText(title);
            } else {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view5.setImage(posterImage);
                label5.setText(title);
            }
        }
    } 

    public void helperChangeMovie2(String[] ids) {
        CompletableFuture<String> ctitle = new CompletableFuture<>();
        String title = "";
        for (int i = 0; i < ids.length; i++) {
            BufferedImage cposter = loadMoviePoster(ids[i]);
            ctitle = loadMovieName(ids[i]);
            title = ctitle.join();
            if (i == 0) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view6.setImage(posterImage);
                label6.setText(title);
            } else if (i == 1) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view7.setImage(posterImage);
                label7.setText(title);
            } else if (i == 2) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view8.setImage(posterImage);
                label8.setText(title);
            } else if (i == 3) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view9.setImage(posterImage);
                label9.setText(title);
            } else {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view10.setImage(posterImage);
                label10.setText(title);
            }
        }
    } 

    public void helperChangeUser1(String[] ids) {

        CompletableFuture<String> cUtitle = new CompletableFuture<>();
        String title = "";
        for (int i = 0; i < ids.length; i++) {
            cUtitle = loadUserName(ids[i]);
            title = cUtitle.join();
            if (i == 0) {
                label1.setText(title);
            } else if (i == 1) {
                label2.setText(title);
            } else if (i == 2) {
                label3.setText(title);
            } else if (i == 3) {
                label4.setText(title);
            } else {
                label5.setText(title);
            }
        }
    } 

    public void helperChangeUser2(String[] ids) {
        CompletableFuture<String> cUtitle = new CompletableFuture<>();
        String title = "";

        for (int i = 0; i < ids.length; i++) {
            cUtitle = loadUserName(ids[i]);
            title = cUtitle.join();
            if (i == 0) {
                label6.setText(title);
            } else if (i == 1) {
                label7.setText(title);
            } else if (i == 2) {
                label8.setText(title);
            } else if (i == 3) {
                label9.setText(title);
            } else {
                label10.setText(title);
            }
        }
    } 
    
    //refreshFriend
    //public void refreshFriend(){}
    public void refreshMovie(ActionEvent e) {
        int counter = 0 ;
                currentUser.setFavMovies(favMoviesIDs);
        int c = currentUser.getRecommendedMovies().size()%5 ;
        for (int i = index ; counter < 5 && i < currentUser.getRecommendedMovies().size() ; i++) 
        {
            movieIDs[counter] = currentUser.getRecommendedMovies().get(i) ;
            counter ++ ; 
        }
        index += counter ;
        if (index == currentUser.getRecommendedMovies().size() - 1 && c > 0) 
        { 
            for (int i = index ; i < c + index ; i++) 
            {
                movieIDs[i] = "000000";
            }
        }
        helperChangeMovie1(movieIDs);
    }
    public void displayImage(){
        if (disp == 0) 
        {
            
            currentUser.setFavMovies(favMoviesIDs);
            
            currentUser.setFavGenres();
System.out.println("favee"+currentUser.getFavMoviesIDs());
            System.out.println( currentUser.getFavGenres());
            System.out.println( currentUser.recommendedFriendsIDs);
        //currentUser.findRecommendedFriends();
            if(currentUser.getFavMoviesIDs().isEmpty()){
                movieIDs = currentUser.recomIds();
            }
            else{
                int counter = 0 ;
                
                int c = currentUser.getRecommendedMovies().size()%5 ;
                for (int i = index ; counter < 5 && i < currentUser.getRecommendedMovies().size() ; i++) 
                {
                    movieIDs[counter] = currentUser.getRecommendedMovies().get(i) ;
                    counter ++ ; 
                }
                index += counter ;
                if (index == currentUser.getRecommendedMovies().size() - 1 && c > 0) 
                { 
                    for (int i = index ; i < c + index ; i++) 
                    {
                        movieIDs[i] = "000000";
                    }
                }
            }
            
            helperChangeMovie1(movieIDs);
            disp++;
        }

    }



    //recommend ve update var YAPMAM GEREK

    public BufferedImage loadMoviePoster(String movieId) {
        BufferedImage img = null;
        String imagePath = "IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\movieImages\\" + movieId
                + ".jpg";
        try {
            File imageFile = new File(imagePath);
            img = ImageIO.read(imageFile);  
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());  
        }
        return img;
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

    public CompletableFuture<String> loadUserName(String userid) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users/" + userid +"/Username" );
        CompletableFuture<String> future = new CompletableFuture<>();
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userTitle = dataSnapshot.getValue(String.class);
                if (userTitle != null) {
                    future.complete(userTitle);
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

    public void insert(ActionEvent e) {
        if (userN.getText().equals("") || pass.getText().equals("")) {
            message.setText("Empty Password or Username");
            message.setFill(Color.rgb(139, 0, 0));
        } else {
            DatabaseReference data = FirebaseDatabase.getInstance().getReference("users/ID-Counter");
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Object value = dataSnapshot.getValue();
                        takeUserID(value);
                    } else {
                        takeUserID(0);
                    }
                }
                public void onCancelled(DatabaseError databaseError) {
                    System.err.println("Error: " + databaseError.getMessage());
                }
            });
        }
    }

    public void check(ActionEvent e) throws IOException {
        if (fb.hasAcc(userN.getText(), pass.getText())) {
            changeMainPage(e);
        }
    }
    
    public void takeUserID(Object value) {
        id = Integer.parseInt("" + value);
        if (fb.userPush(userN.getText(), pass.getText(), id)) {
            message.setText("You are signed up");
            message.setFill(Color.rgb(34, 139, 34));
        } else {
            message.setText("You are signed up");
            message.setFill(Color.rgb(139, 0, 0));
        }
    }
    public void addMovie(ActionEvent e) 
    {
        if (e.getSource() == addToFav0) 
        {
            System.out.println(movieIDs[0]); 
            currentUser.addMovie(movieIDs[0]) ;
        }
        else if (e.getSource() == addToFav1) 
        {
            System.out.println(movieIDs[1]); 
            currentUser.addMovie(movieIDs[1]) ;
        }
        else if (e.getSource() == addToFav2) 
        {
            System.out.println(movieIDs[2]); 
            currentUser.addMovie(movieIDs[2]) ;
        }
        else if (e.getSource() == addToFav3) 
        {
            System.out.println(movieIDs[3]); 
            currentUser.addMovie(movieIDs[3]) ;
        }
        else 
        {
            System.out.println(movieIDs[4]); 
            currentUser.addMovie(movieIDs[4]) ;
        }
        
    }

    public void addMovieInSearch(ActionEvent e) 
    {
        if (e.getSource() == addToFav0) 
        {
            System.out.println(x[0]); 
            currentUser.addMovie(x[0]) ;
        }
        else if (e.getSource() == addToFav1) 
        {
            System.out.println(x[1]); 
            currentUser.addMovie(x[1]) ;
        }
        else if (e.getSource() == addToFav2) 
        {
            System.out.println(x[2]); 
            currentUser.addMovie(x[2]) ;
        }
        else if (e.getSource() == addToFav3) 
        {
            System.out.println(x[3]); 
            currentUser.addMovie(x[3]) ;
        }
        else if( e.getSource() == addToFav4)
        {
            System.out.println(x[4]); 
            currentUser.addMovie(x[4]) ;
        }
        else if( e.getSource() == addToFav5)
        {
            System.out.println(y[0]); 
            currentUser.addMovie(y[0]) ;
        }
        else if (e.getSource() == addToFav6) 
        {
            System.out.println(y[1]); 
            currentUser.addMovie(y[1]) ;
        }
        else if (e.getSource() == addToFav7) 
        {
            System.out.println(y[2]); 
            currentUser.addMovie(y[2]) ;
        }
        else if (e.getSource() == addToFav8) 
        {
            System.out.println(y[3]); 
            currentUser.addMovie(y[3]) ;
        }
        else
        {
            System.out.println(y[4]); 
            currentUser.addMovie(y[4]) ;
        }

    }

    public void setUsers() 
    {
        for (User u : users)
        {
            u.setFavGenres(); 
        }
        System.out.println(users);
    }

    public void addUserAsFriend(ActionEvent e){
        
        if (e.getSource() == b1) 
        {
            System.out.println(k[0]);
            currentUser.addFriend(k[0]);
        }
        else if (e.getSource() == b2) 
        {
            System.out.println(k[1]);
            currentUser.addFriend(k[1]);
        }
        else if (e.getSource() == b3) 
        {
            System.out.println(k[2]);
            currentUser.addFriend(k[2]);
        }
        else if (e.getSource() == b4) 
        {
            System.out.println(k[3]);
            currentUser.addFriend(k[3]);
        }
        else if(e.getSource() == b5)
        {
            System.out.println(k[4]);
            currentUser.addFriend(k[4]);
        }
        else if (e.getSource() == b6)
        {
            System.out.println(l[0]);
            currentUser.addFriend(l[0]);
        }
        else if (e.getSource() == b7)
        {
            System.out.println(l[1]);
            currentUser.addFriend(l[1]);
        }
        else if (e.getSource() == b8)
        {
            System.out.println(l[2]);
            currentUser.addFriend(l[2]);
        }
        else if (e.getSource() == b9)
        {
            System.out.println(l[3]);
            currentUser.addFriend(l[3]);
        }
        else{
            System.out.println(l[4]);
            currentUser.addFriend(l[4]);
        }
    }
    public void changeIn(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeUp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeMainPage(ActionEvent e) throws IOException {
        disp = 0 ;
        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        setUsers();
    }

    public void backToMain(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openProfileSettings(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profile3.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void openCalender(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sessionCalender.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void openChat(ActionEvent e) throws IOException 
    {
        root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void openSearchPage(ActionEvent e) throws IOException {
        if(menu.getValue() == "Friend Search"){
            root = FXMLLoader.load(getClass().getResource("userSearchPage.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        }
        else if(menu.getValue() == "Movie Search"){
            root = FXMLLoader.load(getClass().getResource("movieSearchPage.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        }
        else if(menu.getValue() == "Session Search"){
            root = FXMLLoader.load(getClass().getResource("sessionSearchPage.fxml"));
            stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        }
        else {
            menu.setPromptText("Select Type First");
        }
    }

    public void openProfile1(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profile1.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void openProfile2(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profile2.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void openProfile3(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profile3.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void exit(ActionEvent e) 
    {
        // push all datas here.
        System.exit(1);
    }

    public void callSearchComboBox(Event e){
        if (menu.getItems().isEmpty()) {
            menu.getItems().addAll("Friend Search", "Movie Search", "Session Search");
        }
    }
    public void displayFriends(MouseEvent e){
        int bound = Math.min(friendsIDs.size(), 5);
        for (int i = friendsIndex; i < friendsIndex+bound; i++) {
            for (int j = 0; j < users.size(); j++) {
                if(i == 0 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend1.setText(users.get(j).getName());
                    chatFriendList[0] = users.get(j).getID() ;
                }
                if(i == 1 && friendsIDs.get(i).equals(users.get(j).getID())) 
                {
                    friend2.setText(users.get(j).getName());
                    chatFriendList[1] = users.get(j).getID() ;
                }
                if(i == 2 && friendsIDs.get(i).equals(users.get(j).getID())) 
                {
                    friend3.setText(users.get(j).getName());
                    chatFriendList[2] = users.get(j).getID() ;
                }
                if(i == 3 && friendsIDs.get(i).equals(users.get(j).getID())) 
                {
                    friend4.setText(users.get(j).getName());
                    chatFriendList[3] = users.get(j).getID() ;
                }
                if(i == 4 && friendsIDs.get(i).equals(users.get(j).getID())) 
                {
                    friend5.setText(users.get(j).getName());
                    chatFriendList[4] = users.get(j).getID() ;
                }
            }
        }
    }
    public void displayFriendsRight(ActionEvent e){
        if(!(friendsIndex+5 >= friendsIDs.size())){
            friendsIndex += 5;
        }
        int bound = Math.min(friendsIDs.size()-friendsIndex, 5);
        for (int i = friendsIndex; i < friendsIndex+bound; i++) {
            for (int j = friendsIndex+0; j < users.size(); j++) {
                if(i == friendsIndex+0 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend1.setText(users.get(j).getName());
                    chatFriendList[0] = users.get(j).getID() ;
                }
                if(i == 1 && friendsIDs.get(i) == users.get(j).getID()) 
                {
                    friend2.setText(users.get(j).getName());
                    chatFriendList[1] = users.get(j).getID() ;
                }
                if(i == 2 && friendsIDs.get(i) == users.get(j).getID()) 
                {
                    friend3.setText(users.get(j).getName());
                    chatFriendList[2] = users.get(j).getID() ;
                }
                if(i == 3 && friendsIDs.get(i) == users.get(j).getID()) 
                {
                    friend4.setText(users.get(j).getName());
                    chatFriendList[3] = users.get(j).getID() ;
                }
                if(i == 4 && friendsIDs.get(i) == users.get(j).getID()) 
                {
                    friend5.setText(users.get(j).getName());
                    chatFriendList[4] = users.get(j).getID() ;
                }
            }
        }
    }

    public void createChat(ActionEvent e) 
    {
        myChat.setText("");
        friendChat.setText("");   
        if (e.getSource() == friend1) 
        {
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[0]) , currentUser.getID() , chatFriendList[0]) ;
        }
        else if (e.getSource() == friend2) 
        {
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[1]) , currentUser.getID() , chatFriendList[1]) ;
        }
        else if (e.getSource() == friend3) 
        {
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[2]) , currentUser.getID() , chatFriendList[2]) ;
        }
        else if (e.getSource() == friend4) 
        {
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[3]) , currentUser.getID() , chatFriendList[3]) ;
        }
        else 
        {
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[4]) , currentUser.getID() , chatFriendList[4]) ;
        }
        privateChat.setMessages(friendChat, myChat);
    }   

    public String getChatID(String userId , String friendId) 
    {
        if (Integer.parseInt(userId) > Integer.parseInt(friendId)) 
        {
            return friendId+"-"+userId ;
        }
        else 
        {
            return userId+"-"+friendId ;
        }
    }

    public void toSend(ActionEvent e) 
    {
        if (myChat.getWidth() < textToSend.getText().length()) 
        {
            privateChat.add(new Message(textToSend.getText(), currentUser.getID()));
            /*myChat.appendText(textToSend.getText(0 , textToSend.getText().length()/2) + "\n");
            myChat.appendText(textToSend.getText(textToSend.getText().length()/2 , textToSend.getText().length()) + "\n");*/
            //fb.add(currentUser.getID(), "chats", textToSend.getText() , "03");
        }
        else 
        {
            privateChat.add(new Message(textToSend.getText(), currentUser.getID()));
            //myChat.appendText(textToSend.getText() + "\n");
            //fb.add(currentUser.getID(), "chats", textToSend.getText() , "03" );
        }
        textToSend.setText("");
    }
public void displayFriendsLeft(ActionEvent e){
        if(!(friendsIndex-5 < 0)){
            friendsIndex -= 5;
        }
        int bound = Math.min(friendsIDs.size()-friendsIndex, 5);
        for (int i = friendsIndex; i < friendsIndex+bound; i++) {
            for (int j = 0; j < users.size(); j++) {
                if(i == friendsIndex+0 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend1.setText(users.get(j).getName());
                    chatFriendList[0] = users.get(j).getID() ;
                }
                if(i == 1 && friendsIDs.get(i) == users.get(j).getID()) 
                {
                    friend2.setText(users.get(j).getName());
                    chatFriendList[1] = users.get(j).getID() ;
                }
                if(i == 2 && friendsIDs.get(i) == users.get(j).getID()) 
                {
                    friend3.setText(users.get(j).getName());
                    chatFriendList[2] = users.get(j).getID() ;
                }
                if(i == 3 && friendsIDs.get(i) == users.get(j).getID()) 
                {
                    friend4.setText(users.get(j).getName());
                    chatFriendList[3] = users.get(j).getID() ;
                }
                if(i == 4 && friendsIDs.get(i) == users.get(j).getID()) 
                {
                    friend5.setText(users.get(j).getName());
                    chatFriendList[4] = users.get(j).getID() ;
                }
            }
        }
    }
}