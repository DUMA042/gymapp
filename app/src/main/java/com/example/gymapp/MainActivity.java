package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymapp.data.gymContract.nutritionEntry;
import com.example.gymapp.data.gymContract.blogEntry;
import com.example.gymapp.data.gymContract.userEntry;
import com.example.gymapp.data.gymDbHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    TextView displayView ;
    TextView displayView2;
    Button editprofile;
    Button temp_show_button;

    private gymDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //giving Text view display a view
        displayView=findViewById(R.id.text_view_result);
        displayView2=findViewById(R.id.text_view_result2);
        editprofile=findViewById(R.id.user_profile_button);
        temp_show_button=findViewById(R.id.temp_button);

        temp_show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ExerciseActivity.class);
                startActivity(intent);
            }
        });
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserInput.class);
                startActivity(intent);
            }
        });
        mDbHelper=new gymDbHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    void displayDatabaseInfo(){
       /* String[] projection={
                userEntry._ID,
                userEntry.COLUMN_User_NAME,
                userEntry.COLUMN_User_GENDER,
                userEntry.COLUMN_User_AGE
        };*/
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor2 = db.rawQuery("SELECT * FROM " + blogEntry.TABLE_NAME, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + userEntry.TABLE_NAME, null);
        Cursor cursor3 = db.rawQuery("SELECT * FROM " +nutritionEntry.TABLE_NAME, null);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            int idcolumnindex=cursor.getColumnIndex(userEntry._ID);
            int nameindex =cursor.getColumnIndex(userEntry.COLUMN_User_NAME);
            int genderindex =cursor.getColumnIndex(userEntry.COLUMN_User_GENDER);
            int ageindex =cursor.getColumnIndex(userEntry.COLUMN_User_AGE);
            int blogid=cursor2.getColumnIndex(blogEntry._ID);
           int  blognameindex=cursor2.getColumnIndex(blogEntry.COLUMN_Blog_NAME);
           int  blogdescription=cursor2.getColumnIndex(blogEntry.COLUMN_Blog_DESCRIPTION);


           // TextView displayView=findViewById(R.id.text_view_result);
            displayView.setText("Number of rows in pets database table: " + cursor.getCount());
            while(cursor.moveToNext()){
                int currentID=cursor.getInt(idcolumnindex);
                String currentName=cursor.getString(nameindex);
                int currentgender=cursor.getInt(genderindex);
                int currentage=cursor.getInt(ageindex);
                displayView.append("\n\n" +currentID  + ","+ currentName + ","+ currentgender +","+ currentage);
            }
           // displayView2.setText("Number of rows in Blog database table: " + cursor2.getCount());
           while(cursor2.moveToNext()){
               int currentblogid=cursor2.getInt(blogid);
                String currentblog=cursor2.getString(blognameindex);
                String currentblogdescription=cursor2.getString(blogdescription);


                displayView2.append("\n"+currentblogid +","+currentblog +", "+ currentblogdescription);
            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
            cursor2.close();

        }
    }
}
