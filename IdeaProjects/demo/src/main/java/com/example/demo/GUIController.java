package com.example.demo;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

import com.example.demo.Firebase.FirebaseDataCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
//import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
//import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    private int friendID ;
    private static ArrayList<String> friendsIDs ;
    private static ArrayList<String> ChatIDs;
    private static ArrayList<String> favMoviesIDs ;
    private static ArrayList<User> users ;
    private ArrayList<Movie> moviesStore;
    private static int openOnce1 = 0;
    private static int openOnce = 0;
    private static int friendsIndex = 0;
    private static int friendsIndex1 = 0;
    private static int friendsIndex2 = 0;
    private static String[] chatFriendList = new String[5];
    private static ArrayList<String> favGenres = new ArrayList<>();
    private static ArrayList<String> recommendedMovies = new ArrayList<>();
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
    @FXML Label profilefriend1,profilefriend2,profilefriend3,profilefriend4,profilefriend5;
    @FXML Button r1,r2,r3,r4,r5;
    @FXML private Button friend1, friend2, friend3, friend4, friend5;
    @FXML private Button changePassButton,changeNickButton;
    @FXML private TextField changePass, changeNick;
    @FXML private TextArea chatText1;
    @FXML
    private ComboBox<String> menu;
    @FXML Button delFavMov0,delFavMov1,delFavMov2,delFavMov3,delFavMov4,delFavMov5,delFavMov6,delFavMov7,delFavMov8,delFavMov9;
    @FXML
    private Button insert;
    @FXML
    private Button backIn;
    @FXML
    private Button backUp;
    @FXML
    private TextField pass;
    @FXML
    private TextField ar;
    @FXML
    private TextField userN;
    @FXML
    private VBox friends;
    @FXML
    private Button in;
    @FXML
    private Button up;
    @FXML
    private ImageView profilePhoto;
    @FXML
    private Label label1;
    @FXML
    private ImageView view1;
    @FXML
    private Label label2;
    @FXML
    private ImageView view2;
    @FXML
    private Label label3;
    @FXML
    private ImageView view3;
    @FXML
    private Label label4;
    @FXML
    private ImageView view4;
    @FXML
    private Label label5;
    @FXML
    private ImageView view5;
    @FXML
    private Label label6;
    @FXML
    private ImageView view6;
    @FXML
    private Label label7;
    @FXML
    private ImageView view7;
    @FXML
    private Label label8;
    @FXML
    private ImageView view8;
    @FXML
    private Label label9;
    @FXML
    private ImageView view9;
    @FXML
    private Label label10;
    @FXML
    private ImageView view10;
    @FXML
    private Button movierefreshbutton;
    @FXML
    private Button friendrefreshbutton;
    @FXML
    private TextField movieSearchTextField, userSearchTextField;
    @FXML
    private Text message;
    @FXML
    private ListView<Movie> searchResultsListView;
    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    @FXML
    private Button addToFav0, addToFav1, addToFav2, addToFav3, addToFav4, addToFav5, addToFav6, addToFav7, addToFav8,addToFav9;
    @FXML
    private TextArea friendChat, myChat;
    @FXML
    private Button send;
    @FXML
    private TextArea textToSend;
    private List<User> usersStore;
    private ObservableList<String> movieIds = FXCollections.observableArrayList();
    private ObservableList<String> userIds = FXCollections.observableArrayList();
    private String user;
    private int index = 0;
    private int index1 = 0;
    @FXML private Button left , right ;
    private int smcounter = 0;
    private int sucounter = 0;
    //private int =0;
    private static int disp = 0 ;
    private static int disp1 = 0;
    private static int disp2 = 0;

    String[] x = new String[5];
    String[] y = new String[5];
    String[] k = new String[5];
    String[] l = new String[5];
    String[] switchforuser = new String[5];

    private String[] movieIDs = new String[5];
    private String[] recUserIds = new String[5];
    
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
            
            
            
            if(ids[i].equals("000000")){
                title = "";
            }

            else{
                cUtitle = loadUserName(ids[i]);
                title = cUtitle.join();
            }    
            
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


    public void refreshUsers(ActionEvent e) {
        /*
         int counter = 0 ;
         //recommend users al
         
        //ArrayList<String> a = recommendMovies();
         int c = a.size()%5 ;
         
        for (int i = index ; counter < 5 && i < a.size() ; i++)
         {
            movieIDs[counter] = a.get(i) ;
         counter ++ ;
         }
        index += counter ;
         if (index == a.size() - 1 && c > 0)
         { 
            for (int i = index ; i < c + index ; i++)
         {
                movieIDs[i] = "000000";
         }
        }
        helperChangeMovie1(movieIDs);
         */
    }

    public void refreshMovie(ActionEvent e) {
        int counter = 0 ;
        currentUser.setFavMovies(favMoviesIDs);
        favGenres=currentUser.setFavGenres(moviesStore);

        ArrayList<String> a = recommendMovies();
        int c = a.size()%5 ;

        for (int i = index ; counter < 5 && i < a.size() ; i++) 
        {
            movieIDs[counter] = a.get(i) ;
            counter ++ ;
        }
        index += counter ;
        if (index == a.size() - 1 && c > 0) 
        {
            for (int i = index ; i < c + index ; i++) 
            {
                movieIDs[i] = "000000";
            }
        }
        helperChangeMovie1(movieIDs);
    }
    public void displayImage(){
        //setUsers();
        displayfriendpro1();
        setAllgenres();
        if (disp == 0) 
        {
            //System.out.println("user"+ users.get(2).getFavGenres());

            currentUser.setFavMovies(favMoviesIDs);
            //System.out.println("1111"+callFavGenres("1"));
            ArrayList<String> a = new ArrayList<>();
            a = recommendUsers();
            System.out.println("PRINT" + a);
            
            if(currentUser.getFavMoviesIDs().isEmpty()){
                movieIDs = currentUser.recomIds();
            }
            else{
                int counter = 0 ;
                ArrayList<String> b = recommendMovies();

                int c = b.size()%5 ;
                for (int i = index ; counter < 5 && i < b.size() ; i++) 
                {
                    movieIDs[counter] = b.get(i) ;
                    counter ++ ;
                }
                index += counter ;
                if (index == b.size() - 1 && c > 0) 
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


    public void displayFriendsProfile() {
        disp1 = 0;
        if (disp1 == 0) {

            int counter = 0 ;

            int c = friendsIDs.size()%5 ;
            for (int i = index1 ; counter < 5 && i < friendsIDs.size() ; i++) 
            {
                recUserIds[counter] = friendsIDs.get(i) ;
                counter ++ ;
            }
            index1 += counter ;
            if (index1 == friendsIDs.size() - 1 && c > 0) 
            {
                for (int i = index1 ; i < c + index1 ; i++) 
                {
                    recUserIds[i] = "000000";
                }
            }
        }
        helperChangeUser1(recUserIds);
        disp1++;
    }
/* 
    public void displayFavMovies(){
        if (disp2 == 0) {

            
            int startIndex =0;
            currentUser.setFavMovies(favMoviesIDs);
            favGenres = currentUser.setFavGenres(moviesStore);

            for (int a = 0; a < 10; a++) {
                if (startIndex + a < favMoviesIDs.size()) {
                    if (a < 5) {
                        moviesDisplayedids1[a] = favMoviesIDs.get(startIndex + a);
                    } else {
                        moviesDisplayedids2[a - 5] = favMoviesIDs.get(startIndex + a);
                    }
                } else {
                    if (a < 5) {
                        moviesDisplayedids1[a] = "000000";
                    } else {
                        moviesDisplayedids2[a - 5] = "000000";
                    }
                }
            }
            }
        helperChangeUser1(recUserIds);
        disp1++;

    }*/
 
    //recommend friend ve update var YAPMAM GEREK

    public BufferedImage loadMoviePoster(String movieId) {
        BufferedImage img = null;
        String imagePath = "IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\movieImages\\" + movieId
                + ".jpg";
        try {
            File imageFile = new File(imagePath);
            img = ImageIO.read(imageFile);    
        } 
        catch (IOException e) {
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
        System.out.println(favMoviesIDs);
        if (e.getSource() == addToFav0) 
        {
            if(!(favMoviesIDs.contains(movieIDs[0]))){
                favMoviesIDs.add(movieIDs[0]);
                currentUser.addMovie(movieIDs[0]) ;
            }
            System.out.println(favMoviesIDs);
        }
        else if (e.getSource() == addToFav1) 
        {
            if(!(favMoviesIDs.contains(movieIDs[1]))){
                favMoviesIDs.add(movieIDs[1]);
                currentUser.addMovie(movieIDs[1]);
            }
        }
        else if (e.getSource() == addToFav2) 
        {
            if(!(favMoviesIDs.contains(movieIDs[2]))){
                favMoviesIDs.add(movieIDs[2]);
                currentUser.addMovie(movieIDs[2]) ;
            }
        }
        else if (e.getSource() == addToFav3) 
        {
            if(!(favMoviesIDs.contains(movieIDs[3]))){
                favMoviesIDs.add(movieIDs[3]);
                currentUser.addMovie(movieIDs[3]) ;
            }
        }
        else 
        {
            //System.out.println(movieIDs[4]);
            if(!(favMoviesIDs.contains(movieIDs[4]))){
                favMoviesIDs.add(movieIDs[4]);
                currentUser.addMovie(movieIDs[4]) ;
            }
        }
    }
    public void addMovie1(ActionEvent e) 
    {
        System.out.println(favMoviesIDs);
        if (e.getSource() == addToFav0) 
        {
            //System.out.println(movieIDs[0]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[0]) ;
        }
        else if (e.getSource() == addToFav1) 
        {
            //System.out.println(movieIDs[1]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[1]) ;
        }
        else if (e.getSource() == addToFav2) 
        {
            //System.out.println(movieIDs[2]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[2]) ;
        }
        else if (e.getSource() == addToFav3) 
        {
            //System.out.println(movieIDs[3]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[3]) ;
        }
        else if (e.getSource() == addToFav4) 
        {
            //System.out.println(movieIDs[4]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[4]) ;
        }
        else if (e.getSource() == addToFav5) 
        {
            //System.out.println(movieIDs[1]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[1]) ;
        }
        else if (e.getSource() == addToFav6) 
        {
            //System.out.println(movieIDs[2]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[2]) ;
        }
        else if (e.getSource() == addToFav7) 
        {
            //System.out.println(movieIDs[3]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[3]) ;
        }
        else if (e.getSource() == addToFav8) 
        {
            //System.out.println(movieIDs[4]);
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[4]) ;
        }
        else if(e.getSource() == addToFav9){
            favMoviesIDs.add(movieIDs[0]);
            currentUser.addMovie(movieIDs[4]) ;
        }
        else{}
    }

    public void addMovieInSearch(ActionEvent e) 
    {
        if (e.getSource() == addToFav0) 
        {
            currentUser.addMovie(x[0]) ;
        }
        else if (e.getSource() == addToFav1) 
        {
            currentUser.addMovie(x[1]) ;
        }
        else if (e.getSource() == addToFav2) 
        {
            currentUser.addMovie(x[2]) ;
        }
        else if (e.getSource() == addToFav3) 
        {
            currentUser.addMovie(x[3]) ;
        }
        else if( e.getSource() == addToFav4)
        {
            currentUser.addMovie(x[4]) ;
        }
        else if( e.getSource() == addToFav5)
        {
            currentUser.addMovie(y[0]) ;
        }
        else if (e.getSource() == addToFav6) 
        {
            currentUser.addMovie(y[1]) ;
        }
        else if (e.getSource() == addToFav7) 
        {
            currentUser.addMovie(y[2]) ;
        }
        else if (e.getSource() == addToFav8) 
        {
            currentUser.addMovie(y[3]) ;
        }
        else
        {
            currentUser.addMovie(y[4]) ;
        }

    }

    public void addUserAsFriend(ActionEvent e){

        if (e.getSource() == b1) 
        {
            currentUser.addFriend(k[0]);
        }
        else if (e.getSource() == b2) 
        {
            currentUser.addFriend(k[1]);
        }
        else if (e.getSource() == b3) 
        {
            currentUser.addFriend(k[2]);
        }
        else if (e.getSource() == b4) 
        {
            currentUser.addFriend(k[3]);
        }
        else if(e.getSource() == b5)
        {
            currentUser.addFriend(k[4]);
        }
        else if (e.getSource() == b6)
        {
            currentUser.addFriend(l[0]);
        }
        else if (e.getSource() == b7)
        {
            currentUser.addFriend(l[1]);
        }
        else if (e.getSource() == b8)
        {
            currentUser.addFriend(l[2]);
        }
        else if (e.getSource() == b9)
        {
            currentUser.addFriend(l[3]);
        }
        else{
            currentUser.addFriend(l[4]);
        }
    }

    public void changeNick(ActionEvent e)
    {
        String newNick = changeNick.getText();
        currentUser.setUserName(newNick);
        changeNick1(newNick);
    }

    public void changePass(ActionEvent e)
    {
        String newPass = changePass.getText();
        currentUser.setPassword(newPass);
        changePass1(newPass);
    }
    public void changeNick1(String newNick) {
        DatabaseReference currentUserRef = fb.userDB.child(currentUser.getID());
        currentUserRef.child("Username").setValueAsync(newNick);
        currentUser.setUserName(newNick);
    }
    
    public void changePass1(String newPass) {
        DatabaseReference currentUserRef = fb.userDB.child(currentUser.getID());
        currentUserRef.child("Password").setValueAsync(newPass);
        currentUser.setPassword(newPass);
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

    }

    public void backToMain(ActionEvent e) throws IOException {
        disp = 0;
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

    public void openChat(ActionEvent e) throws IOException {
        friendsIndex = 0;
        root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openSearchPage(ActionEvent e) throws IOException {
        if(menu.getValue() == "User Search"){
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
        else {
            menu.setPromptText("Select Type First");
        }
    }

    public void openProfile1(ActionEvent e) throws IOException {
        disp2 = 0;
        friendsIndex2 = 0;
        root = FXMLLoader.load(getClass().getResource("profile1.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void openProfile2(ActionEvent e) throws IOException {
        friendsIndex1 = 0;
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
            menu.getItems().addAll("User Search", "Movie Search");
        }
    }
    // this is for chat friend displaying
    public void displayFriends() {
        System.out.println(friendsIDs);
            int bound = Math.min(friendsIDs.size()-friendsIndex, 5);
            for (int i = friendsIndex; i < friendsIndex + bound; i++) {
                for (int j = 0; j < users.size(); j++) {
                    if (i == friendsIndex + 0 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        friend1.setText(users.get(j).getName());
                        chatFriendList[0] = users.get(j).getID() ;
                    }
                    if (i == friendsIndex + 1 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        friend2.setText(users.get(j).getName());
                        chatFriendList[1] = users.get(j).getID() ;
                    }
                    if (i == friendsIndex + 2 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        friend3.setText(users.get(j).getName());
                        chatFriendList[2] = users.get(j).getID() ;
                    }
                    if (i == friendsIndex + 3 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        friend4.setText(users.get(j).getName());
                        chatFriendList[3] = users.get(j).getID() ;
                    }
                    if (i == friendsIndex + 4 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        friend5.setText(users.get(j).getName());
                        chatFriendList[4] = users.get(j).getID() ;
                    }
                }
            }
        }


    public void displayFriendsRight(ActionEvent e) {
        if (!(friendsIndex + 5 >= friendsIDs.size())) {
            friendsIndex += 5;
        }
        int bound = Math.min(friendsIDs.size()-friendsIndex, 5);
        for (int i = friendsIndex; i < friendsIndex+bound; i++) {
            for (int j = friendsIndex+0; j < users.size(); j++) {
                if(i == friendsIndex+0 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend1.setText(users.get(j).getName());
                    chatFriendList[0] = users.get(j).getID() ;
                }
                if (i == friendsIndex + 1 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend2.setText(users.get(j).getName());
                    chatFriendList[1] = users.get(j).getID() ;
                }
                if (i == friendsIndex + 2 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend3.setText(users.get(j).getName());
                    chatFriendList[2] = users.get(j).getID() ;
                }
                if (i == friendsIndex + 3 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend4.setText(users.get(j).getName());
                    chatFriendList[3] = users.get(j).getID() ;
                }
                if (i == friendsIndex + 4 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend5.setText(users.get(j).getName());
                    chatFriendList[4] = users.get(j).getID() ;
                }
                if(bound <= 4){
                    friend5.setText("");
                }
                if(bound <= 3){
                    friend4.setText("");
                }
                if(bound <= 2){
                    friend3.setText("");
                }
                if(bound <= 1){
                    friend2.setText("");
                }
                if(bound <= 0){
                    friend1.setText("");
                }
            }
        }
    }

    public void createChat(ActionEvent e) 
    {
        if (e.getSource() == friend1) 
        {
            myChat.setText("");
            friendChat.setText("");
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[0]) , currentUser.getID() , chatFriendList[0] , friendChat) ;
        }
        else if (e.getSource() == friend2) 
        {
            myChat.setText("");
            friendChat.setText("");
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[1]) , currentUser.getID() , chatFriendList[1] , friendChat) ;
        }
        else if (e.getSource() == friend3) 
        {
            myChat.setText("");
            friendChat.setText("");
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[2]) , currentUser.getID() , chatFriendList[2] , friendChat) ;
        }
        else if (e.getSource() == friend4) 
        {
            myChat.setText("");
            friendChat.setText("");
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[3]) , currentUser.getID() , chatFriendList[3] , friendChat) ;
        }
        else 
        {
            myChat.setText("");
            friendChat.setText("");
            privateChat = new Chat(getChatID(currentUser.getID(), chatFriendList[4]) , currentUser.getID() , chatFriendList[4] , friendChat) ;
        }
        privateChat.setMessages(myChat);
    }

    public String getChatID(String userId, String friendId) {
        if (Integer.parseInt(userId) > Integer.parseInt(friendId)) {
            return friendId + "-" + userId;
        } 
        else {
            return userId + "-" + friendId;
        }
    }

    public void toSend(ActionEvent e) 
    {
        myChat.setText("");
        friendChat.setText("");
        if (myChat.getWidth() <= textToSend.getText().length()) 
        {
            privateChat.add(new Message(textToSend.getText(), currentUser.getID()));

        }
        else 
        {
            privateChat.add(new Message(textToSend.getText(), currentUser.getID()));

        }
        textToSend.setText("");
        privateChat.setMyMessages(myChat);
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
                if (i == friendsIndex + 1 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend2.setText(users.get(j).getName());
                    chatFriendList[1] = users.get(j).getID() ;
                }
                if (i == friendsIndex + 2 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend3.setText(users.get(j).getName());
                    chatFriendList[2] = users.get(j).getID() ;
                }
                if (i == friendsIndex + 3 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend4.setText(users.get(j).getName());
                    chatFriendList[3] = users.get(j).getID() ;
                }
                if (i == friendsIndex + 4 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    friend5.setText(users.get(j).getName());
                    chatFriendList[4] = users.get(j).getID() ;
                }
            }
        }
        System.out.println(friendID);
    }
    public void displayfriendpro(){
            int bound = Math.min(friendsIDs.size()-friendsIndex1, 5);
            for (int i = friendsIndex1; i < friendsIndex1 + bound; i++) {
                for (int j = 0; j < users.size(); j++) {
                    if (i == 0 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        profilefriend1.setText(users.get(j).getName());
                    }
                    if (i == 1 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        profilefriend2.setText(users.get(j).getName());
                    }
                    if (i == 2 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        profilefriend3.setText(users.get(j).getName());
                    }
                    if (i == 3 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        profilefriend4.setText(users.get(j).getName());
                    }
                    if (i == 4 && friendsIDs.get(i).equals(users.get(j).getID())) {
                        profilefriend5.setText(users.get(j).getName());
                    }
                    if(bound <= 4){
                        profilefriend5.setText("");
                    }
                    if(bound <= 3){
                        profilefriend4.setText("");
                    }
                    if(bound <= 2){
                        profilefriend3.setText("");
                    }
                    if(bound <= 1){
                        profilefriend2.setText("");
                    }
                    if(bound <= 0){
                        profilefriend1.setText("");
                }
                }
            }
        }
        public void displayfriendpro1(){
            ArrayList<String> arr = new ArrayList<>();
            arr = recommendUsers();
            int bound = Math.min(arr.size()-0, 5);
            for (int i = 0; i < 0 + bound; i++) {
                if (i == 0 ) {
                    label6.setText(arr.get(i));
                }
                if (i == 1 ) {
                    label7.setText(arr.get(i));
                }
                if (i == 2 ) {
                    label8.setText(arr.get(i));
                }
                if (i == 3 ) {
                    label9.setText(arr.get(i));
                }
                if (i == 4 ) {
                    label10.setText(arr.get(i));
                }
            }
        }
        public void displayImage1(){
            BufferedImage cposter1 = loadMoviePoster("000000");
            Image posterImage1 = SwingFXUtils.toFXImage(cposter1, null);
            int bound = Math.min(favMoviesIDs.size()-friendsIndex2, 10);
            CompletableFuture<String> ctitle = new CompletableFuture<>();
            String title = "";
            System.out.println(favMoviesIDs);
            for (int i = friendsIndex2; i < friendsIndex2 + bound; i++) {
                BufferedImage cposter = loadMoviePoster(favMoviesIDs.get(i));
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);                
                ctitle = loadMovieName(favMoviesIDs.get(i));
                title = ctitle.join();
                if (i == friendsIndex2 + 0) {
                    view1.setImage(posterImage);
                    label1.setText(title);
                }
                if (i == friendsIndex2 + 1) {
                    view2.setImage(posterImage);
                    label2.setText(title);
                }
                if (i == friendsIndex2 + 2) {
                    view3.setImage(posterImage);
                    label3.setText(title);
                }
                if (i == friendsIndex2 + 3) {
                    view4.setImage(posterImage);
                    label4.setText(title);
                }
                if (i == friendsIndex2 + 4 ) {
                    view5.setImage(posterImage);
                    label5.setText(title);
                }
                if (i == friendsIndex + 5 ) {
                    view6.setImage(posterImage);
                    label6.setText(title);
                }
                if (i == friendsIndex2 + 6 ) {
                    view7.setImage(posterImage);
                    label7.setText(title);
                }
                if (i == friendsIndex2 + 7 ) {
                    view8.setImage(posterImage);
                    label8.setText(title);
                    }
                if (i == friendsIndex2 + 8) {
                    view9.setImage(posterImage);
                    label9.setText(title);
                }
                if (i == friendsIndex2 + 9) {
                    view10.setImage(posterImage);
                    label10.setText(title);
                }
                if(bound <= 9){
                    view10.setImage(posterImage1);
                    label10.setText("");
                }
                if(bound <= 8){
                    view9.setImage(posterImage1);
                    label9.setText("");
                }
                if(bound <= 7){
                    view8.setImage(posterImage1);
                    label8.setText("");
                }
                if(bound <= 6){
                    view7.setImage(posterImage1);
                    label7.setText("");
                }
                if(bound <= 5){
                    view6.setImage(posterImage1);
                    label6.setText("");
                }
                if(bound <= 4){
                    view5.setImage(posterImage1);
                    label5.setText("");
                }
                if(bound <= 3){
                    view4.setImage(posterImage1);
                    label4.setText("");
                }
                if(bound <= 2){
                    view3.setImage(posterImage1);
                    label3.setText("");
                }
                if(bound <= 1){
                    view2.setImage(posterImage1);
                    label2.setText("");
                }
                if(bound <= 0){
                    view1.setImage(posterImage1);
                    label1.setText("");
                }
            }
        }
        public void displayImage1Right(){
            if (!(friendsIndex2 + 10 > favMoviesIDs.size())) {
                friendsIndex2 += 10;
            }
            int bound = Math.min(favMoviesIDs.size() - friendsIndex2, 10);
            CompletableFuture<String> ctitle = new CompletableFuture<>();
            String title = "";
            BufferedImage cposter1 = loadMoviePoster("000000");
            Image posterImage1 = SwingFXUtils.toFXImage(cposter1, null);
            for (int i = friendsIndex2; i < friendsIndex2 + bound; i++) {
                BufferedImage cposter = loadMoviePoster(favMoviesIDs.get(i));
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);                
                ctitle = loadMovieName(favMoviesIDs.get(i));
                title = ctitle.join();
                if (i == friendsIndex2 + 0) {
                    view1.setImage(posterImage);
                    label1.setText(title);
                }
                if (i == friendsIndex2 + 1) {
                    view2.setImage(posterImage);
                    label2.setText(title);
                }
                if (i == friendsIndex2 + 2) {
                    view3.setImage(posterImage);
                    label3.setText(title);
                }
                if (i == friendsIndex2 + 3) {
                    view4.setImage(posterImage);
                    label4.setText(title);
                }
                if (i == friendsIndex2 + 4 ) {
                    view5.setImage(posterImage);
                    label5.setText(title);
                }
                if (i == friendsIndex2 + 5 ) {
                    view6.setImage(posterImage);
                    label6.setText(title);
                }
                if (i == friendsIndex2 + 6 ) {
                    view7.setImage(posterImage);
                    label7.setText(title);
                }
                if (i == friendsIndex2 + 7 ) {
                    view8.setImage(posterImage);
                    label8.setText(title);
                    }
                if (i == friendsIndex2 + 8) {
                    view9.setImage(posterImage);
                    label9.setText(title);
                }
                if (i == friendsIndex2 + 9) {
                    view10.setImage(posterImage);
                    label10.setText(title);
                }
                if(bound <= 9){
                    view10.setImage(posterImage1);
                    label10.setText("");
                }
                if(bound <= 8){
                    view9.setImage(posterImage1);
                    label9.setText("");
                }
                if(bound <= 7){
                    view8.setImage(posterImage1);
                    label8.setText("");
                }
                if(bound <= 6){
                    view7.setImage(posterImage1);
                    label7.setText("");
                }
                if(bound <= 5){
                    view6.setImage(posterImage1);
                    label6.setText("");
                }
                if(bound <= 4){
                    view5.setImage(posterImage1);
                    label5.setText("");
                }
                if(bound <= 3){
                    view4.setImage(posterImage1);
                    label4.setText("");
                }
                if(bound <= 2){
                    view3.setImage(posterImage1);
                    label3.setText("");
                }
                if(bound <= 1){
                    view2.setImage(posterImage1);
                    label2.setText("");
                }
                if(bound <= 0){
                    view1.setImage(posterImage1);
                    label1.setText("");
                }
            }
        }
        public void displayImage1Left(){
            if (!(friendsIndex2 - 10 < 0)) {
                friendsIndex2 -= 10;
            }
            int bound = Math.min(favMoviesIDs.size() - friendsIndex2, 10);
            CompletableFuture<String> ctitle = new CompletableFuture<>();
            String title = "";
            for (int i = friendsIndex2; i < friendsIndex2 + bound; i++) {
                BufferedImage cposter = loadMoviePoster(favMoviesIDs.get(i));
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);                
                ctitle = loadMovieName(favMoviesIDs.get(i));
                title = ctitle.join();
                if (i == friendsIndex2 + 0) {
                    view1.setImage(posterImage);
                    label1.setText(title);
                }
                if (i == friendsIndex2 + 1) {
                    view2.setImage(posterImage);
                    label2.setText(title);
                }
                if (i == friendsIndex2 + 2) {
                    view3.setImage(posterImage);
                    label3.setText(title);
                }
                if (i == friendsIndex2 + 3) {
                    view4.setImage(posterImage);
                    label4.setText(title);
                }
                if (i == friendsIndex2 + 4 ) {
                    view5.setImage(posterImage);
                    label5.setText(title);
                }
                if (i == friendsIndex2 + 5 ) {
                    view6.setImage(posterImage);
                    label6.setText(title);
                }
                if (i == friendsIndex2 + 6 ) {
                    view7.setImage(posterImage);
                    label7.setText(title);
                }
                if (i == friendsIndex2 + 7 ) {
                    view8.setImage(posterImage);
                    label8.setText(title);
                    }
                if (i == friendsIndex2 + 8) {
                    view9.setImage(posterImage);
                    label9.setText(title);
                }
                if (i == friendsIndex2 + 9) {
                    view10.setImage(posterImage);
                    label10.setText(title);
                }
            }
        }
    public void removeFriendFromProfile(ActionEvent e){
        if(e.getSource() == r1){
            removeFriend(currentUser.getID(), friendsIDs.get(friendsIndex1+0));
            friendsIDs.remove(friendsIndex1 + 0);
            System.out.println(friendsIndex1+0);
        }
        if(e.getSource() == r2){
            removeFriend(currentUser.getID(), friendsIDs.get(friendsIndex1+1));
            friendsIDs.remove(friendsIndex1 + 0);
            System.out.println(friendsIndex1+1);
        }
        if(e.getSource() == r3){
            removeFriend(currentUser.getID(), friendsIDs.get(friendsIndex1+2));
            friendsIDs.remove(friendsIndex1 + 2);
            System.out.println(friendsIndex1+2);
        }
        if(e.getSource() == r4){
            removeFriend(currentUser.getID(), friendsIDs.get(friendsIndex1+3));
            friendsIDs.remove(friendsIndex1 + 3);
            System.out.println(friendsIndex1+3);
        }
        if(e.getSource() == r5){
            removeFriend(currentUser.getID(), friendsIDs.get(friendsIndex1+4));
            friendsIDs.remove(friendsIndex1 + 4);
            System.out.println(friendsIndex1 + 4);
        }
    }
    public void removeFriend(String userID, String friendID) {
            DatabaseReference userFriendsRef = fb.userDB.child(userID).child("Friends");
            DatabaseReference friendFriendsRef = fb.userDB.child(friendID).child("Friends");
            userFriendsRef.child(friendID).removeValueAsync();
            friendFriendsRef.child(userID).removeValueAsync();
    }
    public void removeFilm(ActionEvent e){
        if(e.getSource() == delFavMov0){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+0));
            favMoviesIDs.remove(friendsIndex2+0);
        }
        if(e.getSource() == delFavMov1){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+1));
            favMoviesIDs.remove(friendsIndex2+1);
        }
        if(e.getSource() == delFavMov2){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+2));
            favMoviesIDs.remove(friendsIndex2+2);
        }
        if(e.getSource() == delFavMov3){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+3));
            favMoviesIDs.remove(friendsIndex2+3);
        }
        if(e.getSource() == delFavMov4){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+4));
            favMoviesIDs.remove(friendsIndex2+4);
        }
        if(e.getSource() == delFavMov5){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+5));
            favMoviesIDs.remove(friendsIndex2+5);
        }
        if(e.getSource() == delFavMov6){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+6));
            favMoviesIDs.remove(friendsIndex2+6);
        }
        if(e.getSource() == delFavMov7){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+7));
            favMoviesIDs.remove(friendsIndex2+7);
        }
        if(e.getSource() == delFavMov8){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+8));
            favMoviesIDs.remove(friendsIndex2+8);
        }
        if(e.getSource() == delFavMov9){
            removeFavoriteMovie(currentUser.getID(), favMoviesIDs.get(friendsIndex2+9));
            favMoviesIDs.remove(friendsIndex2+9);
        }
    }
    public void removeFavoriteMovie(String userID, String movieIDToRemove) {
        DatabaseReference userFavoritesRef = fb.userDB.child(userID).child("Fav_MovieIDs");
        userFavoritesRef.child(movieIDToRemove).removeValueAsync();
    }
    public void displayFriendsRightpro(ActionEvent e) {
        if (!(friendsIndex1 + 5 >= friendsIDs.size())) {
            friendsIndex1 += 5;
        }
        int bound = Math.min(friendsIDs.size() - friendsIndex1, 5);
        for (int i = friendsIndex1; i < friendsIndex1 + bound; i++) {
            for (int j = friendsIndex1 + 0; j < users.size(); j++) {
                if (i == friendsIndex1 + 0 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend1.setText(users.get(j).getName());
                }
                if(bound <= 4){
                    profilefriend5.setText("");
                }
                if(bound <= 3){
                    profilefriend4.setText("");
                }
                if(bound <= 2){
                    profilefriend3.setText("");
                }
                if(bound <= 1){
                    profilefriend2.setText("");
                }
                if(bound <= 0){
                    profilefriend1.setText("");
                }
                if (i == friendsIndex1 + 1 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend2.setText(users.get(j).getName());
                }
                if (i ==  friendsIndex1 + 2 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend3.setText(users.get(j).getName());
                }
                if (i ==  friendsIndex1 + 3 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend4.setText(users.get(j).getName());
                }
                if (i ==  friendsIndex1 + 4 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend5.setText(users.get(j).getName());
                }
            }
        }
    }
    public void displayFriendsLeftPro(ActionEvent e) {
        if (!(friendsIndex1 - 5 < 0)) {
            friendsIndex1 -= 5;
        }
        int bound = Math.min(friendsIDs.size() - friendsIndex1, 5);
        for (int i = friendsIndex1; i < friendsIndex1 + bound; i++) {
            for (int j = 0; j < users.size(); j++) {
                if (i == friendsIndex1 + 0 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend1.setText(users.get(j).getName());
                }
                if (i == friendsIndex1 + 1 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend2.setText(users.get(j).getName());
                }
                if (i == friendsIndex1 + 2 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend3.setText(users.get(j).getName());
                }
                if (i == friendsIndex1 + 3 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend4.setText(users.get(j).getName());
                }
                if (i == friendsIndex1 + 4 && friendsIDs.get(i).equals(users.get(j).getID())) {
                    profilefriend5.setText(users.get(j).getName());
                }
            }
        }
    }
    public ArrayList<String> callFavGenres(String ID) {
        ArrayList<String> ids = new ArrayList<>();
        for (User user : users) {
            
            if(user.getID().equals(ID)){
                ids = user.setFavGenres(moviesStore);
            }
        }
        return ids;
    }    

    public ArrayList<String> recommendMovies() 
    {

        ArrayList<String> a = new ArrayList<String>();
        for (Movie m : moviesStore) 
        {
            if ( callFavGenres(currentUser.getID()).contains(m.getGenre())
                    &&
                    !favMoviesIDs.contains(""+m.takeId()))
                a.add(""+m.takeId()) ;
        }
        return a;
    }
    
    public ArrayList<String> recommendUsers()
    {
        ArrayList<String> current = new ArrayList<String>();
        ArrayList<String> otherUsers = new ArrayList<>();
        ArrayList<String> recomUser = new ArrayList<>();
        current = callFavGenres(currentUser.getID());
        System.out.println("current" + current);
        
        for(int y=0; y< users.size(); y++){
            System.out.println(callFavGenres(users.get(y).getID()));
            otherUsers = callFavGenres(users.get(y).getID());
            if(similarPerson(current, otherUsers) && !(currentUser.getID().equals(users.get(y).getID()))) {
                recomUser.add(users.get(y).getID());
            }
        }
        return recomUser;
    }

    public boolean similarPerson(ArrayList<String> current ,ArrayList<String> other){
        for (int i = 0; i < current.size(); i++) {
            for (int j = 0; j < other.size(); j++) {
                if(current.get(i).equals(other.get(j))){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static String findMostFrequent(ArrayList<String> list) {
        if (list.isEmpty()) {
            return null; 
           
        }
        else{
            System.out.println("not null")
        }
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        for (String element : list) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        int maxFrequency = 0;
        ArrayList<String> mostFrequentElements = new ArrayList<>();

        for (String key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostFrequentElements.clear();
                mostFrequentElements.add(key);
            } else if (frequency == maxFrequency) {
                mostFrequentElements.add(key);
            }
        }
        System.out.println("İn find Max"+ mostFrequentElements);
        if (mostFrequentElements.size() == 1) {
            return mostFrequentElements.get(0);
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(mostFrequentElements.size());
            return mostFrequentElements.get(randomIndex);
        }
    } 
      
    public void findRecommendedFriends(){
     ArrayList<User> userForCompare = fb.getUsers();
     for (int num = 0; num < userForCompare.size(); num++) {
     if (userForCompare.get(num).getFavGenres().contains(currentUser.getFavGenres().get(0)) ||
     userForCompare.get(num).getFavGenres().contains(currentUser.getFavGenres().get(1)) ||
     userForCompare.get(num).getFavGenres().contains(currentUser.getFavGenres().get(2))) {
     boolean check = true;
     for (int n = 0; n < currentUser.friendsIDs.size(); n++) {
     if (userForCompare.get(num).getId().equals(friendsIDs.get(n))) {
     check = false;
     }
                }
                if (check == true) {
     currentUser.recommendedFriendsIDs.add(userForCompare.get(num).getId());
     }
            }
        }
        
    }

    public ArrayList<String> findMaxes(ArrayList<String> genres) 
    {
        int max = 0 , temp = 0;
        String g1 = "" , g2 = "" , g3 = "";
        Collections.sort(genres);
        for (int i = 0 ; i < genres.size() - 1 ; i++) 
        {
            if (genres.get(i).equals(genres.get(i+1)))
                temp ++ ;
            else 
            {
                if (temp >= max) 
                {
                    max = temp ;
                    g3 = g2 ;
                    g2 = g1 ;
                    g1 = genres.get(i+1) ;
                }
                temp = 0 ;
            }
        }
        ArrayList<String> g = new ArrayList<>() ;
        g.add(g1) ;
        g.add(g2) ;
        g.add(g3) ;
        return g;
}

public void setAllgenres(){
    for(int a=0; a<users.size()-1; a++){
        callFavGenres(users.get(a).getID());
    }
}    
}