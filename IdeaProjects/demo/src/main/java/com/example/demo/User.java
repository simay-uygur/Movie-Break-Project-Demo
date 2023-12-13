package com.example.demo;

public class User
{
    private String name ;
    private String pass ;
    private String id ;

    private Firebase fb ;
    public User(String name, String pass , String id , Firebase fb)
    {
        this.name = name ;
        this.pass = pass ;
        this.id = id ;
        this.fb = fb ;
    }

    public String getPass()
    {
        return  pass ;
    }

    public String getName()
    {
        return  name ;
    }

    public String getID()
    {
        return id ;
    }

    public String toString() {return  name +":"+pass ;}

    public void addFriend(String friendId) 
    {
        fb.addFriend(id, friendId);
    }
}
