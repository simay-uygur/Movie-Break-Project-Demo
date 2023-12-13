package com.example.demo;

import javafx.scene.image.Image;

public class Movie {

    Controller controller;
    String name;
    String genre;
    Image image;
    int year;
    String posterPath;
    private int id ;

    public Movie(int id, String name, String genre,String posterPath) {
        this.id = id ;
        this.name = name ;
        this.genre = genre ;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return name ;
    }

    public String getGenre() {
        return genre;
    }

    public String getPath(){
        return posterPath;
    }

    public int takeId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Name: "+ name+ " id: "+ id+ "genre: "+ genre + "posterpath"+ posterPath;
    }
}
