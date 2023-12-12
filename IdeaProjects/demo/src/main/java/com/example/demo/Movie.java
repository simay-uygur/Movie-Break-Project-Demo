package com.example.demo;


import javafx.scene.image.Image;

public class Movie {

    Controller controller;
    String name;
    String genre;
    Image image;
    int year;
    int id ;
    public Movie(int id, String name, String genre) {
        this.id = id ;
        this.name = name ;
        this.genre = genre ;
    }

    public String getTitle() {
        return name ;
    }

    public String toString() 
    {
        return id +":"+name +":"+ genre ; 
    }
    
}
