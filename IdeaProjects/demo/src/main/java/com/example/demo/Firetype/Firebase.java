package com.example.demo.Firetype;

import com.example.demo.HelloController;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Firebase {
    HashMap<String, String> users ;
    private static int id ;
    private  User u ;
    DatabaseReference db ;
    /*private static Scanner sc ;
    public static void main(String[] args) {
        initialize();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("user");
        sc = new Scanner(System.in) ;
        System.out.print("Enter Name:");
        String name = sc.nextLine() ;
        System.out.print("Enter pass:");
        String pass = sc.nextLine() ;
        User user = new User(name , pass) ;
        User user2 = new User("popka" , "4723471") ;
        Map<String , String> users = new HashMap<>();
        users.put(user.getName() , user.getPass()) ;
        users.put(user2.getName() , user2.getPass()) ;

        db.child("user").setValueAsync(users);
    }*/

    public Firebase(String name , String pass)
    {
        u = new User(name , pass) ;
        db = FirebaseDatabase.getInstance().getReference("users") ;
    }

    public void push()
    {
        /*DatabaseReference user = db.push() ;
        user.child(u.getName()).setValueAsync(u.getPass()) ;
        user.setValueAsync(u.toString()) ;*/
        DatabaseReference idS = db.child("ID-Counter") ;
        idS.child("ID").setValueAsync(""+id) ;
        DatabaseReference user = db.child(""+id);
        user.child("Username").setValueAsync(u.getName()) ;
        user.child("Password").setValueAsync(u.getPass()) ;
        increaseId();
    }

    public void hasAcc()
    {
        users = new HashMap<>() ;
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren())
                {
                    /*if  (
                            userSnapshot.child("Username").getValue().equals(u.getName()) &&
                            userSnapshot.child("Password").getValue().equals(u.getPass())
                        )
                    {
                        System.out.println(userSnapshot.toString());
                        return;
                    }
                    else
                    {
                        System.out.println("New account?");
                        return;
                    }*/
                    users.put(userSnapshot.getKey() , ""+userSnapshot.child("Username").getValue()) ;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        HelloController j = new HelloController() ;
        j.showUsers(users);
    }

    public void increaseId()
    {
        initId();
        id++ ;
    }

    public void initId()
    {
        DatabaseReference data = db.child("ID-Counter").child("ID") ;
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    id = Integer.parseInt(""+snapshot.getValue()) ;
                    //id = (int)snapshot.getValue() ;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

}
