package com.example.demo;

public class test {
    public static void main(String[] args)
    {
        String toCheck = "123|huesoso|bitch" ;
        System.out.println(toCheck.substring(toCheck.indexOf("|")+1, toCheck.lastIndexOf("|")));
        System.out.println(toCheck.substring(toCheck.indexOf("|")+1));
        System.out.println(toCheck.substring(toCheck.indexOf("|")+1).equals("huesoso|bitch"));
    }
}
