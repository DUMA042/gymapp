package com.example.gymapp;

public class DataClass {
    private String name;
    private String details;
    private String calories;
    private int vegan;
    private int protein;

    public DataClass(String name1,String calories1,int vegan1,int protein1 ){
        name=name1;
        calories=calories1;
        vegan=vegan1;
        protein=protein1;
    }
    public String get_name(){
        return  name;
    }
    public String get_details(){
        return  name;
    }
    public int get_vegan(){
        return  vegan;
    }
    public int get_protein(){
        return  protein;
    }


}
