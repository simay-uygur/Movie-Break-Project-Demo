package com.example.demo;

public class User
{
    private String name ;
    private String pass ;
    private String id ;
    public User(String name, String pass , String id)
    {
        this.name = name ;
        this.pass = pass ;
        this.id = id ;
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
}
