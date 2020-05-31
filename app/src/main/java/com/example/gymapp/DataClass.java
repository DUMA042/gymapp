package com.example.gymapp;

public class DataClass {
    private String name="NULL";
    private String calories="NULL";
    private int vegan=0;
    private int protein=0;

    public DataClass(String name1,String calories1,int vegan1,int protein1 ){
        name=name1;
        calories=calories1;
        vegan=vegan1;
        protein=protein1;
    }
    public String get_name(){
        return  name;
    }
    public String get_calories(){
        return  calories;
    }
    public int get_vegan(){
        return  vegan;
    }
    public int get_protein(){
        return  protein;
    }


}
