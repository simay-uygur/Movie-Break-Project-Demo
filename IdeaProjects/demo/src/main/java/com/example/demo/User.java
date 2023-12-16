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
    ArrayList<String> favMoviesIDs;
    ArrayList<String> friendsIDs;
    ArrayList<String> chatIDs;
    ArrayList<String> sessionIDs;
    MovieService mService; 
    private ArrayList<Movie> movies ;
    private DatabaseReference friends ;
    private HashMap<String,String> favGenres ; 
    private ArrayList<String> recommendedMovies ; 
    public User(String userName, String password, String userID , Firebase fb)
    {
        mService = new MovieService() ;
        recommendedMovies = new ArrayList<>() ; 
        setFirebase(fb);
        setUserName(userName);
        setPassword(password);
        setID(userID);
        initRefs(); 
        addMovie(""+238);
        addMovie(""+155);
        addMovie(""+240);
        addMovie(""+150540);
        addMovie(""+287903);
        addMovie(""+10908);
        addMovie(""+118249);
        addMovie(""+76600);
        addMovie(""+662167);
        addMovie(""+726209);
        addMovie(""+897087);
        setFavGenres();
        System.out.println(favGenres);
        System.out.println(recommendedMovies);
    }
    

    public void initRefs()
    {
        recommendedMovies = new ArrayList<>() ;
        favMoviesIDs = new ArrayList<>() ;
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
        fb.add(userID, "Fav_MovieIDs", movieId);
        favMoviesIDs.add(movieId);
    }

    public void removeFriend(String userID)
    {
        friendsIDs.remove(userID);
    }

    /*public void setFavGenres()
    {
        ArrayList genres = new ArrayList<>() ;
        for (int i = 0 ; i < favMoviesIDs.size() ; i++) 
        {
            for (Movie movie : movies) 
            {
                if ((""+movie.takeId()).equals(favMoviesIDs.get(i))) genres.add(movie.getGenre()) ;
            }
        }
        favGenres = findMaxes(genres) ;
        System.out.println(favGenres);
        movies.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (int i = 0 ; i < favMoviesIDs.size() ; i++)
                {
                    for (DataSnapshot movie : snapshot.getChildren())
                    {
                        if (favMoviesIDs.get(i).equals(""+movie.child("genre").getValue())) 
                            genres.add(movie.child("genre").getValue());
                    }
                }
                favGenres = findMaxes(genres) ;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onCancelled'");
            }
            
        });
    }*/

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

    public void recomIds(){
        
        recomms[0] = "844416";
        recomms[1] = "845844";
        recomms[2] = "854648";
        recomms[3] = "857879";
        recomms[4] = "870358";
    }

    public void recommendMovies() 
    {
        ArrayList<String> recomms = new ArrayList<>() ; 
        System.out.println(favGenres);
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

    /*public void findGenre(String id) 
    {
        String genre = ""+movies.child(id).child("genres") ;
        favMovies.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot movie : snapshot.)
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onCancelled'");
            }
            
        })
    }*/

    public String toString()
    {
        return "Name: " + userName + " ID: " + userID;
    }

    /* 
    public ArrayList<Integer> search(String input)
    {
        return;
    }*/
}