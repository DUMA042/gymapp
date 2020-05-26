package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gymapp.data.gymContract;
import com.example.gymapp.data.gymDbHelper;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements CustomAdapter.ForecastAdapterOnClickHandler {
    private RecyclerView mRecyclerView;
    ArrayList<DataClass> hold_data=new ArrayList<>();
    // COMPLETED (35) Add a private ForecastAdapter variable called mForecastAdapter
    private CustomAdapter mCustomAdapter;
    private gymDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        mRecyclerView = findViewById(R.id.recyclerview_test);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // COMPLETED (41) Set the layoutManager on mRecyclerView
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        // COMPLETED (43) set mForecastAdapter equal to a new ForecastAdapter
        /*
         * The ForecastAdapter is responsible for linking our weather data with the Views that
         * will end up displaying our weather data.
         *
         */
        //String[] data_list={"E","A","T" ,"er","edf","E","A","T" ,"er","edf"};
        mDbHelper=new gymDbHelper(this);
        dosetup();
        doinsertdata();
        mCustomAdapter= new CustomAdapter(hold_data,this);
        mRecyclerView.setAdapter(mCustomAdapter);
        mCustomAdapter.setWeatherData(hold_data);
    }
    ////////////////////////////////////////////////////////////////////
    void dosetup(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();
        Cursor cursor3 = db.rawQuery("SELECT * FROM " + gymContract.nutritionEntry.TABLE_NAME, null);
        if (cursor3.getCount()==0){
        values3.put(gymContract.nutritionEntry.COLUMN_Nutrition_NAME,"Boiled egg");
        values3.put(gymContract.nutritionEntry.COLUMN_Nutrition_Calories,"155");
        values3.put(gymContract.nutritionEntry.COLUMN_Nutrition_Vegan, 0);
        values3.put(gymContract.nutritionEntry.COLUMN_Nutrition_Protein, 23);
        values4.put(gymContract.nutritionEntry.COLUMN_Nutrition_NAME,"Boiled Yam");
        values4.put(gymContract.nutritionEntry.COLUMN_Nutrition_Calories,"150");
        values4.put(gymContract.nutritionEntry.COLUMN_Nutrition_Vegan, 1);
        values4.put(gymContract.nutritionEntry.COLUMN_Nutrition_Protein, 23);
        long    newRowId =  db.insert(gymContract.nutritionEntry.TABLE_NAME, null, values3);
        db.insert(gymContract.nutritionEntry.TABLE_NAME, null, values4);
        }
        cursor3.close();
    }

    void doinsertdata(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor3 = db.rawQuery("SELECT * FROM " + gymContract.nutritionEntry.TABLE_NAME, null);
        try {
            int nutindinexid = cursor3.getColumnIndex(gymContract.nutritionEntry._ID);
            int nutnameindex = cursor3.getColumnIndex(gymContract.nutritionEntry.COLUMN_Nutrition_NAME);
            int nutcaloriesindex = cursor3.getColumnIndex(gymContract.nutritionEntry.COLUMN_Nutrition_Calories);
            int nutveganindex = cursor3.getColumnIndex(gymContract.nutritionEntry.COLUMN_Nutrition_Vegan);
            int nutprotein = cursor3.getColumnIndex(gymContract.nutritionEntry.COLUMN_Nutrition_Protein);
            while (cursor3.moveToNext()) {
                int currentid = cursor3.getInt(nutindinexid);
                String currentname = cursor3.getString(nutnameindex);
                String curentcalorie = cursor3.getString(nutcaloriesindex);
                int currentvegan = cursor3.getInt(nutveganindex);
                int currentprotein = cursor3.getInt(nutprotein);
                DataClass data_object = new DataClass(currentname, curentcalorie, currentvegan, currentprotein);
                hold_data.add(data_object);
            }
        }finally {
            cursor3.close();

        }
    }

    //////////////////////////////////////////////////////////////////

    @Override
    public void onClick(String weatherForDay) {
        Toast.makeText(this, "The point has been saved "+ weatherForDay, Toast.LENGTH_SHORT).show();



        /* This is the class that we want to start (and open) when the button is clicked. */


        /*
         * Here, we create the Intent that will start the Activity we specified above in
         * the destinationActivity variable. The constructor for an Intent also requires a
         * context, which we stored in the variable named "context".
         */


        // COMPLETED (2) Use the putExtra method to put the String from the EditText in the Intent
        /*
         * We use the putExtra method of the Intent class to pass some extra stuff to the
         * Activity that we are starting. Generally, this data is quite simple, such as
         * a String or a number. However, there are ways to pass more complex objects.
         */



    }
}
