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
    private static ArrayList<Movie> movies ;
    private static ArrayList<String> favGenres ; 
    private DatabaseReference chats ;
    private static ArrayList<String> recommendedMovies ; 
    public User(String userName, String password, String userID , Firebase fb)
    {
        chats = FirebaseDatabase.getInstance().getReference("chats"); 
        //genres = new ArrayList<>() ;
        chatsWithFriends = new ArrayList<>() ;
        userMessages = new ArrayList<>() ;
        friendsMessages = new ArrayList<>() ;
        friendsIDs = new ArrayList<>();
        recommendedMovies = new ArrayList<>() ;
        setFirebase(fb);
        setUserName(userName);
        setPassword(password);
        setID(userID);
        recommendedFriendsIDs = new ArrayList<>();
    }
    public ArrayList<String> getFavGenres(){
        return favGenres;
    }
    
    public void findRecommendedFriends(){
        ArrayList<User> userForCompare = fb.getUsers();
        for (int num = 0; num < userForCompare.size(); num++) {
            System.out.println("hee"+userForCompare.get(num).getFavGenres());
            if (userForCompare.get(num).getFavGenres().contains(getFavGenres().get(0)) ||
                    userForCompare.get(num).getFavGenres().contains(getFavGenres().get(1)) ||
                    userForCompare.get(num).getFavGenres().contains(getFavGenres().get(2))) {
                boolean check = true;
                for (int n = 0; n < this.friendsIDs.size(); n++) {
                    if (userForCompare.get(num).getId().equals(friendsIDs.get(n))) {
                        check = false;
                    }
                }
                if (check == true) {
                    recommendedFriendsIDs.add(userForCompare.get(num).getId());
                }
            }
        }
        
    }
    
    public void setFavMovies(ArrayList<String> fav)
    {
        favMoviesIDs = new ArrayList<>(fav) ;
    }
    /* 
    public void setFavMovies(ArrayList<String> fav, UserCallback callback) {
        this.favMoviesIDs = new ArrayList<>(fav);
        setFavGenres();
        if (callback != null) {
            callback.onFavMoviesLoaded(this);
        }
    }*/

    public void setChatIDs(ArrayList<String> ChatIDs){
        this.chatIDs = ChatIDs;
    }


    public void initRefs()
    {
        this.movies = new ArrayList<>(movies) ;
        //System.out.println(movies);
        //friends = FirebaseDatabase.getInstance().getReference("users/"+userID+"/Friends") ;
    }

    public void setFriends(ArrayList<String> homo) 
    {
        friendsIDs = new ArrayList<>(homo) ; 
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



    public void addFriend(String friendID)
    {
        if (!friendsIDs.contains(friendID) && !friendID.equals("000000")) 
        {
            fb.add(userID, "Friends", friendID);
            friendsIDs.add(friendID);
        }
    }

    public void addMovie(String movieId) 
    {
        if (!favMoviesIDs.contains(movieId)) 
        {
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
        for (int i = 0; i < favMoviesIDs.size(); i++) {
            for (Movie m : mo) 
            {
                if (favMoviesIDs.get(i).equals(""+m.takeId())) 
                {
                    genres.add(m.getGenre()) ;
                }
            }
        }
        /*System.out.println(favMoviesIDs);
        System.out.println(genres);*/
        findMaxes(genres);
        return genres;
    }
    
    public void findMaxes(ArrayList<String> genres) 
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
        favGenres = new ArrayList<>(g) ;
        recommendMovies();
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

    public void recommendMovies() 
    {
        for (Movie m : movies) 
        {
            if ( favGenres.contains(m.getGenre()) 
                 && 
                 !favMoviesIDs.contains(""+m.takeId())) 
            recommendedMovies.add(""+m.takeId()) ;
        }
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
        return "Name: " + userName + " ID: " + userID + favMoviesIDs + "huesosi-ebanye" +favGenres ;
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