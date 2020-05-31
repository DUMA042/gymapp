package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gymapp.data.gymContract;
import com.example.gymapp.data.gymDbHelper;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements CustomAdapter.ForecastAdapterOnClickHandler{
    private RecyclerView mRecyclerView;
    ArrayList<DataClass> hold_data=new ArrayList<>();

    private CustomAdapter mCustomAdapter;
    private gymDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        mRecyclerView = findViewById(R.id.recyclerview_test);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // COMPLETED (41) Set the layoutManager on mRecyclerView
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mDbHelper=new gymDbHelper(this);
        dosetup();
        doinsertdata();


            mCustomAdapter= new CustomAdapter(hold_data,this);
            mRecyclerView.setAdapter(mCustomAdapter);
            mCustomAdapter.setWeatherData(hold_data);

    }

    ////////////////////////////////////
    void dosetup(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();
        ContentValues values5 = new ContentValues();
        Cursor cursor3 = db.rawQuery("SELECT * FROM " + gymContract.exerciseEntry.TABLE_NAME, null);
        if (cursor3.getCount()<2){
            values3.put(gymContract.exerciseEntry.COLUMN_Exercise_NAME,"Jump");
            values3.put(gymContract.exerciseEntry. COLUMN_Exercise_DESCRIPTION,"Jump High ..");
            values4.put(gymContract.exerciseEntry.COLUMN_Exercise_NAME,"RUN");
            values4.put(gymContract.exerciseEntry. COLUMN_Exercise_DESCRIPTION,"Run High ..");
            values5.put(gymContract.exerciseEntry.COLUMN_Exercise_NAME,"Work");
            values5.put(gymContract.exerciseEntry. COLUMN_Exercise_DESCRIPTION,"Work High ..");

          long newId=  db.insert(gymContract.exerciseEntry.TABLE_NAME, null, values3);
            db.insert(gymContract.exerciseEntry.TABLE_NAME, null, values4);
            db.insert(gymContract.exerciseEntry.TABLE_NAME, null, values5);
            if (newId==-1){
                Toast.makeText(this, "Wrong!!!!! ", Toast.LENGTH_SHORT).show();

            }

        }
        cursor3.close();
    }
    void doinsertdata(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor3 = db.rawQuery("SELECT * FROM " + gymContract.exerciseEntry.TABLE_NAME, null);
        try {
            int nutindinexid = cursor3.getColumnIndex(gymContract.exerciseEntry._ID);
            int nutnameindex = cursor3.getColumnIndex(gymContract.exerciseEntry.COLUMN_Exercise_NAME);
            int nutdescriptionindex = cursor3.getColumnIndex(gymContract.exerciseEntry.COLUMN_Exercise_DESCRIPTION);
            while (cursor3.moveToNext()) {
                int currentid = cursor3.getInt(nutindinexid);
                String currentname = cursor3.getString(nutnameindex);
                String curentcalorie = cursor3.getString(nutdescriptionindex);
                DataClass data_object = new DataClass(currentname, curentcalorie,0,0);
                hold_data.add(data_object);

            }
        }finally {
            cursor3.close();

        }
    }

    @Override
    public void onClick(String item) {
        Toast.makeText(this, "The Exercise has " + item+ " been has been saved ", Toast.LENGTH_SHORT).show();
    }

    }
