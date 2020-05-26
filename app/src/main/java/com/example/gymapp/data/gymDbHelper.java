package com.example.gymapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gymapp.CustomAdapter;
import  com.example.gymapp.data.gymContract.*;


public class gymDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = gymDbHelper.class.getSimpleName();
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gymReader.db";


    public gymDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_User_TABLE =
                "CREATE TABLE " + gymContract.userEntry.TABLE_NAME + " (" +
                        userEntry._ID + " INTEGER PRIMARY KEY," +
                        userEntry.COLUMN_User_NAME  + " TEXT NOT NULL," +
                        userEntry.COLUMN_User_GENDER  + " INTEGER NOT NULL," +
                        userEntry.COLUMN_User_AGE + " INTEGER NOT NULL DEFAULT 0)";
        db.execSQL(SQL_CREATE_User_TABLE);

        /**Blog Table**/
        String SQL_CREATE_Blog_TABLE = "CREATE TABLE " + blogEntry.TABLE_NAME + " ("

                + blogEntry._ID + " INTEGER PRIMARY KEY,"
                      +  blogEntry.COLUMN_Blog_NAME  + " TEXT NOT NULL,"
                        + blogEntry.COLUMN_Blog_DESCRIPTION + " TEXT NOT NULL)" ;
        db.execSQL(SQL_CREATE_Blog_TABLE);

         /*String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + PetEntry.TABLE_NAME + " ("
                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL,"
                + PetEntry.COLUMN_PET_BREED + " TEXT NOT NULL )";*/
        //   db.execSQL(SQL_CREATE_PETS_TABLE);


        String SQL_CREATE_Nutrition_TABLE =
                "CREATE TABLE " + nutritionEntry.TABLE_NAME + " (" +
                        nutritionEntry._ID + " INTEGER PRIMARY KEY," +
                        nutritionEntry.COLUMN_Nutrition_NAME  + " TEXT NOT NULL," +
                        nutritionEntry.COLUMN_Nutrition_Calories + " TEXT NOT NULL," +
                        nutritionEntry.COLUMN_Nutrition_Vegan + " INTEGER NOT NULL,"
                       + nutritionEntry.COLUMN_Nutrition_Protein + " INTEGER NOT NULL DEFAULT 0)" ;
        db.execSQL(SQL_CREATE_Nutrition_TABLE);


        String SQL_CREATE_Exercise_TABLE =
                "CREATE TABLE " + exerciseEntry.TABLE_NAME + " (" +
                        exerciseEntry._ID + " INTEGER PRIMARY KEY," +
                        exerciseEntry.COLUMN_Exercise_NAME  + " TEXT NOT NULL," +
                        exerciseEntry.COLUMN_Exercise_DESCRIPTION + " TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_Exercise_TABLE);

        /*String SQL_CREATE_Nutrition_TABLE =
                "CREATE TABLE " + nutritionEntry.TABLE_NAME + " (" +
                        nutritionEntry._ID + " INTEGER PRIMARY KEY," +
                        nutritionEntry.COLUMN_Nutrition_NAME  + " TEXT NOT NULL," +
                        nutritionEntry.COLUMN_Nutrition_Calories + " TEXT NOT NULL," +
                        nutritionEntry.COLUMN_Nutrition_Vegan + " INTEGER NOT NULL,"
                        + nutritionEntry.COLUMN_Nutrition_Protein + " INTEGER NOT NULL DEFAULT 0)" ;
        db.execSQL(SQL_CREATE_Nutrition_TABLE);*/








    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
