package com.example.demo;


import javafx.scene.image.Image;

public class Movie {

    Controller controller;
    String name;
    String genre;
    Image image;
    int year;
    private int id ;
    public Movie(int id, String name, String genre) {
        this.id = id ;
        this.name = name ;
        this.genre = genre ;
    }

    public String getTitle() {
        return name ;
    }

    public String getGenre(){
        return genre;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "Name: "+ name+ " id: "+ id+ "genre: "+ genre;
    }
}
