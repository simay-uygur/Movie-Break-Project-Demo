package com.example.demo.Firetype;

public class User
{
    private String name ;
    private String pass ;

    public User(String name, String pass)
    {
        this.name = name ;
        this.pass = pass ;
    }

    public String getPass()
    {
        return  pass ;
    }

    public String getName()
    {
        return  name ;
    }

    public String toString() {return  name +":"+pass ;}
}
