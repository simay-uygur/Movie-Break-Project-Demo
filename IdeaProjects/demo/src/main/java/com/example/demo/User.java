package com.example.demo;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.Collections;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class User {
    Controller controller;
    String userName;
    String password;
    String userID;
    Firebase fb ;
    private static ArrayList<String> favMoviesIDs;
    ArrayList<String> friendsIDs;
    ArrayList<String> recommendedFriendsIDs;    
    ArrayList<String> chatIDs;
    ArrayList<String> sessionIDs;
    MovieService mService; 
    private ArrayList<Chat> chatsWithFriends ;
    private ArrayList<Message> userMessages ;
    private ArrayList<Message> friendsMessages ;
    private ArrayList<Movie> movies ;
    private static ArrayList<String> favGenres ; 
    private DatabaseReference chats ;
    private ArrayList<String> recommendedMovies ; 
    public User(String userName, String password, String userID , Firebase fb)
    {
        chats = FirebaseDatabase.getInstance().getReference("chats"); 
        //genres = new ArrayList<>() ;
        chatsWithFriends = new ArrayList<>() ;
        userMessages = new ArrayList<>() ;
        friendsMessages = new ArrayList<>() ;
        friendsIDs = new ArrayList<>();
        recommendedMovies = new ArrayList<>() ;
        recommendedFriendsIDs = new ArrayList<>();
        setFirebase(fb);
        setUserName(userName);
        setPassword(password);
        setID(userID);
        initRefs();
       
    }
    public ArrayList<String> getFavGenres(){
        return favGenres;
    }

    
    public void setFavMovies(ArrayList<String> fav)
    {
        favMoviesIDs = new ArrayList<>(fav) ;
    }


    public void setChatIDs(ArrayList<String> ChatIDs){
        this.chatIDs = ChatIDs;
    }


    public void initRefs()
    {
        movies = new ArrayList<>(fb.movies) ;
        //friends = FirebaseDatabase.getInstance().getReference("users/"+userID+"/Friends") ;
    }

    public void setFriends(ArrayList<String> mo) 
    {
        friendsIDs = new ArrayList<>(mo) ; 
    }

    public void setGenres(ArrayList<String> genres) 
    {
        this.favGenres = genres ;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setFirebase(Firebase fb)
    {
        this.fb = fb ;
    }

    public String getName()
    {
        return userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setID(String userID)
    {
        this.userID = userID;
    }

    public String getID()
    {
        return userID;
    }

    public ArrayList<String> getFriendsIDs()
    {
        return friendsIDs;
    }
/*
    public void setRecommendedUsers(ArrayList<String> user){
        this.recommendedFriendsIDs = user;
    }

 */
    public void addFriend(String friendID)
    {
        if (!friendsIDs.contains(friendID) && !friendID.equals("000000")) 
        {
            System.out.println("friend added:" + friendID);
            fb.add(userID, "Friends", friendID);
            friendsIDs.add(friendID);
        }
    }

    public void addMovie(String movieId) 
    {
        if (!favMoviesIDs.contains(movieId)) 
        {
            System.out.println("Movie added:" + movieId);
            fb.add(userID, "Fav_MovieIDs", movieId);
            favMoviesIDs.add(movieId);
        }
    }

    public void removeFriend(String userID)
    {
        friendsIDs.remove(userID);
    }

    public ArrayList<String> setFavGenres(ArrayList<Movie> mo) {
        ArrayList<String> genres = new ArrayList<>();
        System.out.println(favMoviesIDs);
        for (int i = 0; i < favMoviesIDs.size(); i++) {
            for (Movie m : mo) 
            {
                if (favMoviesIDs.get(i).equals(""+m.takeId())) 
                {
                    System.out.println(m.takeId()+" "+favMoviesIDs.get(i) );
                    genres.add(m.getGenre()) ;
                }
            }
        }
        System.out.println(genres);

        //findMaxes(genres);
        return genres;
    }
    

    public ArrayList<String> getFavMoviesIDs()
    {
        return favMoviesIDs;
    }

    public void removeFavMovie(String movieID)
    {
        favMoviesIDs.remove(movieID);
    }

    public ArrayList<String> getChatIDs()
    {
        return chatIDs;
    }

    public ArrayList<String> getSessionIDs()
    {
        return sessionIDs;
    }

    public void changeUserName (String newUserName)
    {
        setUserName(newUserName);
    }

    public void changePassword (String newPassword)
    {
        setPassword(newPassword);
    }

    public String[] recomIds(){
        String[] x = new String[5];
        x[0] = "844416";
        x[1] = "845844";
        x[2] = "854648";
        x[3] = "857879";
        x[4] = "870358";
        return x;
    }

    public void createChats() 
    {

    }

    public void takeMessages(ArrayList<Message> unknown , String path) 
    {
        chats.child(path).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot message : snapshot.getChildren()) 
                {
                    
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onCancelled'");
            }
            
        });
    }

    public void addForRec(String id) 
    {
        recommendedMovies.add(""+id) ;
    }

    public String getId(){
        return userID;
    }
    
    public String toString()
    {
        return "Name: " + userName + " ID: " + userID + favMoviesIDs + "huesosi-ebanye" +favGenres;
    }

    public ArrayList<String> getRecommendedMovies()
    {
        return recommendedMovies ;
    }

    public ArrayList<String> getRecommendedFriends()
    {
        return recommendedFriendsIDs ;
    }
}