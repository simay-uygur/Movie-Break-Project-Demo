package com.example.demo;

import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
    private ArrayList<String> favMoviesIDs;
    ArrayList<String> friendsIDs;
    ArrayList<String> chatIDs;
    ArrayList<String> sessionIDs;
    MovieService mService; 
    private ArrayList<Movie> movies ;
    private DatabaseReference friends ;
    private HashMap<String,String> favGenres ; 
    private static ArrayList<String> recommendedMovies ; 
    public User(String userName, String password, String userID , Firebase fb , ArrayList<String> favMovies)
    {
        favMoviesIDs = favMovies ;
        System.out.println(favMoviesIDs);
        favMoviesIDs = fb.take("Fav_MovieIDs", userID) ;
        System.out.println(favMoviesIDs);
        setFirebase(fb);
        setUserName(userName);
        setPassword(password);
        setID(userID);
        initRefs();
        setFavGenres();
        recomIds();
    }
    

    public void initRefs()
    {
        recommendedMovies = new ArrayList<>() ;
        movies = new ArrayList<>(fb.movies) ;
        friends = FirebaseDatabase.getInstance().getReference("users/"+userID+"/Friends") ;
    }

    public void setGenres(HashMap<String, String> genres) 
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

    public void addFriendID(String userID)
    {
        friendsIDs.add(userID);
    }

    public void addFriend(String friendID)
    {
        fb.add(userID, "Friends", friendID);
    }

    public void addMovie(String movieId) 
    {
        System.out.println("Movie added:" + movieId);
        fb.add(userID, "Fav_MovieIDs", movieId);
        favMoviesIDs.add(movieId);
    }

    public void removeFriend(String userID)
    {
        friendsIDs.remove(userID);
    }

    public void setFavGenres() {
        ArrayList<String> genres = new ArrayList<>();
        
        for (int i = 0; i < favMoviesIDs.size(); i++) {
            for (Movie m : movies) 
            {
                if (favMoviesIDs.contains(""+m.takeId())) genres.add(m.getGenre()) ;
            }
        }
        findMaxes(genres);
    }

    public void findMaxes(ArrayList<String> genres) 
    {
        int max = 0 , temp = 0;
        String g1 = "" , g2 = "" ; 
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
                    g2 = g1 ;
                    g1 = genres.get(i+1) ;
                }
                temp = 0 ;
            }
        }
        HashMap<String, String> ret = new HashMap<>() ;
        ret.put(g1, g2) ;
        favGenres = ret ;
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
        ArrayList<String> recomms = new ArrayList<>() ; 
        String first = favGenres.keySet().toString().substring(1, favGenres.keySet().toString().length()-1) ;
        String second = favGenres.values().toString().substring(1, favGenres.values().toString().length()-1) ;
        for (Movie m : movies) 
        {
            if ((m.getGenre().equals(second) 
                 || 
                 m.getGenre().equals(first)) 
                 && 
                 !favMoviesIDs.contains(""+m.takeId())) 
            recommendedMovies.add(""+m.takeId()) ;
        }
    }

    public void addForRec(String id) 
    {
        recommendedMovies.add(""+id) ;
    }

    public String toString()
    {
        return "Name: " + userName + " ID: " + userID;
    }

    /* 
    public ArrayList<Integer> search(String input)
    {
        return;
    }*/

    public ArrayList<String> recommend()
    {
        recommendMovies(); 
        return recommendedMovies ;
    }
}