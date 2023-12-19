package com.example.demo;
import com.example.demo.GUIController;
import com.fasterxml.jackson.databind.Module.SetupContext;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Query;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.firebase.messaging.Message;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
public class Firebase {
    private boolean accExists = false;
    ArrayList<User> users ;
    ArrayList<Movie> movies ;
    ArrayList<Chat> chats;
    ArrayList<String> friendsIDs ;
    private static ArrayList<String> DATA ;
    DatabaseReference films ;
    private int userID;
    private int chatID;
    private static User u;
    private static Chat c;
    private FirebaseDataCallback dataCallback;
    DatabaseReference userDB;
    DatabaseReference chatDB;
    Query userQ ;
    private static String id ; 
    public Firebase()
    {
        films = FirebaseDatabase.getInstance().getReference("movies");
        userDB = FirebaseDatabase.getInstance().getReference("users");
        chatDB = FirebaseDatabase.getInstance().getReference("chats");
        users = new ArrayList<>() ;
        //user = new User(, getCurrentID(), getCurrentID(), null)
        movies = new ArrayList<>() ;
        takeAllMovieData();
        takeAllData();
    }
    public Firebase(FirebaseDataCallback callback) {
        films = FirebaseDatabase.getInstance().getReference("movies");
        userDB = FirebaseDatabase.getInstance().getReference("users");
        chatDB = FirebaseDatabase.getInstance().getReference("chats");
        users = new ArrayList<>();
        movies = new ArrayList<>();
        friendsIDs = new ArrayList<>() ;
        this.dataCallback = callback;
        //takeIDS("Fav_MovieIDs", id) ;
        takeAllData();
        takeAllMovieData();
    }

    public interface FirebaseDataCallback {
        void onDataLoaded(ArrayList<Movie> movies);
        void onUserLoaded(User user) ;
        void onFav_MoviesIDSloaded(ArrayList<String> fav_moviesDatas) ;
        void onUsersLoaded(ArrayList<User> userIDs) ;
        void onFriendsLoaded(ArrayList<String> friendIDs) ;
    }

    public void takeAllMovieData() {
        films.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                movies.clear(); // Clear the ArrayList to avoid duplicates
                for (DataSnapshot movie : snapshot.getChildren()) {
                    movies.add(new Movie(
                            Integer.parseInt(movie.getKey()),
                            "" + movie.child("title").getValue(),
                            "" + movie.child("genre").getValue(),
                            "" + movie.child("path").getValue())
                    );
                }

                // Notify the callback that data has been loaded
                if (dataCallback != null) {
                    dataCallback.onDataLoaded(movies);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Something went wrong :(");
            }
        });
    }
    
    
    

    public ArrayList<User> getUsers(){
        return users;
    }
    public void takeAllData()
    {
        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren())
                {
                    User u = new User(""+userSnapshot.child("Username").getValue(), ""+userSnapshot.child("Password").getValue(), userSnapshot.getKey(), Firebase.this) ; 
                    users.add(setUser(u));
                }
                

                if (dataCallback != null) 
                    dataCallback.onUsersLoaded(users);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void takeFriends(String id)
    {
        userDB.child(id).child("Friends").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                friendsIDs.clear();
                for (DataSnapshot homie : snapshot.getChildren()) 
                {
                    friendsIDs.add(""+homie.getKey()) ;
                }

                if (dataCallback != null) 
                {
                    dataCallback.onFriendsLoaded(friendsIDs);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
            
        });
    }
    public boolean userPush(String name , String password , int id)
    {
        if (!accountExists(name)) 
        {
            DatabaseReference user = userDB.child(""+id);
            user.child("Username").setValueAsync(name);
            user.child("Password").setValueAsync(password);
            user.child("Fav_MovieIDs").setValueAsync("") ;
            user.child("Friends").setValueAsync("") ;
            user.child("SessionIDS").setValueAsync("") ;
            user.child("ChatIDS").setValueAsync("") ;
            DatabaseReference userIDs = userDB.child("ID-Counter");
            userIDs.setValueAsync(id + 1);
            return true ;
        }
        return false ;
    }

    public boolean accountExists(String name) 
    {
        for (User toCheck : users) 
        {
            if (toCheck.getName().equals(name)) return true ;
        }
        return false ;
    }

    public void chatPush()
    {
        DatabaseReference chat = chatDB.child(c.getID());
        chat.child("messages").setValueAsync(c.getMessages());
        DatabaseReference chatIDs = chatDB.child("ID-Counter");
        chatIDs.setValueAsync(Integer.parseInt(c.getID()) + 1);
    }

    public boolean hasAcc(String name, String pass)
    {
        int ID = indexOf(name, pass) ;
        if (ID > -1) 
        {
            id = ""+ID ;
            takeIDS("Fav_MovieIDs", ""+ID) ;
            createUser(name, pass, ""+ID);
            return true ;
        }
        return false ;
    }

    public int indexOf(String name , String pass)
    {
        for (User toCheck : users) 
        {
            if (toCheck.getName().equals(name) && toCheck.getPassword().equals(pass)) 
                return Integer.parseInt(toCheck.getID()) ;
        }
        return -1 ;
    }

   /* public void takeAllMovieData() 
    {
        films.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
               for (DataSnapshot movie : snapshot.getChildren()) 
               {
                    movies.add(new Movie(
                                    Integer.parseInt(movie.getKey()),
                                    ""+movie.child("title").getValue(), 
                                    ""+movie.child("genre").getValue(), 
                                    ""+movie.child("path").getValue())
                                ) ;
               }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Something went wrong :(");
            }
            
        });
    } */

    public void setAcc()
    {
        accExists = true;
    }

    public boolean getB()
    {
        return accExists;
    }

    public void initUserID()
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("users/ID-Counter");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();
                userID = Integer.parseInt("" + value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });
    }
    public void initChatID()
    {
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("chats/ID-Counter");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();
                chatID = Integer.parseInt("" + value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
            }
        });
    }
    public void createUser(String userName, String pass, String ID)
    {
        u = new User(userName, pass , ID , this);
        if (dataCallback != null) 
        {
            dataCallback.onUserLoaded(u);
            takeFriends(id);
            u.setFriends(friendsIDs);
        }
    }

    /*public void createChat(ArrayList<Message> messages, String ID, ArrayList<User> users)
    {
        c = new Chat(messages, ID, users);
    }*/

    public ArrayList<String> takeIDS(String path , String id) 
    {
        ArrayList<String> ids = new ArrayList<>() ;
        userDB.child(id).child(path).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) 
                {
                    ids.add(data.getKey()) ;
                }

                if (dataCallback != null) 
                {
                    dataCallback.onFav_MoviesIDSloaded(ids);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }

        });
        return DATA ;
    }

    public void setData(ArrayList<String> datas) 
    {
        DATA = new ArrayList<>(datas) ;
    }

    public void add(String userId , String path , String id) 
    {
        DatabaseReference user = userDB.child(userId).child(path) ; user.child(id).setValueAsync("") ;
        switch(path)
        {
            case "Friends" : 
            DatabaseReference friend = userDB.child(id).child(path) ; friend.child(userId).setValueAsync("") ; 
            if (Integer.parseInt(id) > Integer.parseInt(userId)) 
            {
                DatabaseReference chat = chatDB.child(userId+"-"+id) ; 
                chat.child(userId).setValueAsync("") ; 
                chat.child(id).setValueAsync(""); 
            }
            else 
            {
                DatabaseReference chat = chatDB.child(id+"-"+userId) ; 
                chat.child(userId).setValueAsync("") ; 
                chat.child(id).setValueAsync(""); 
            }
            break ;
            case "Fav_MovieIDs" : user = userDB.child(userId).child(path) ; user.child(id).setValueAsync("") ; break ;
        }
    }

    public User setUser(User u) 
    {
        ArrayList<String> ids = new ArrayList<>() ;
        userDB.child(u.getID()).child("Fav_MovieIDs").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot m : snapshot.getChildren())
                {
                    ids.add(m.getKey()) ;
                }
                u.setFavMovies(ids);
                u.setFavGenres();
                
            }
            
            @Override
            public void onCancelled(DatabaseError error) {
            }
            
        });
        return u ;
    }

        
    /*public void setUser(User u, Consumer<User> callback) {
        ArrayList<String> ids = new ArrayList<>();
        userDB.child(u.getID()).child("Fav_MovieIDs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot m : snapshot.getChildren()) {
                    ids.add(m.getKey());
                }
                u.setFavMovies(ids);
                callback.accept(u); // Callback ile User nesnesini döndür
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Hata işleme
            }
        });
    }*/
    

    public User getUser(){
        return u;
    }
    public String getCurrentID(){
        return u.getID();
    }
}