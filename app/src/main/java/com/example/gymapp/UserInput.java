package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.gymapp.data.gymContract.*;
import com.example.gymapp.data.gymDbHelper;

public class UserInput extends AppCompatActivity {
   private Spinner mGenderSpinner;
   private Button msaveprofile;
   private EditText mname;
   private EditText mage;
    private gymDbHelper mDbHelper;
    int mGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        mGenderSpinner=findViewById(R.id.spinner_gender);
        msaveprofile=findViewById(R.id.save_profile);
        mDbHelper=new gymDbHelper(this);
        mname=findViewById(R.id.edit_user_name);
        mage=findViewById(R.id.edit_user_Age);
        setupSpinner();
        msaveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertuser();
                finish();
            }
        });

    }

    /***/
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = userEntry.gender_MALE; // Male
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender =userEntry.gender_FEMALE; // Female
                    } else {
                        mGender = userEntry.gender_UNKNOWN; // Unknown
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                            mGender=0;
            }

        });
    }
   void  insertuser(){
String name=mname.getText().toString().trim();
SQLiteDatabase db = mDbHelper.getWritableDatabase();
int age=Integer.parseInt(mage.getText().toString().trim());
       long newRowId=-1;
       //long newRowId4=2;


       ContentValues values = new ContentValues();
       /**This is for the blog **/
       ContentValues values1 = new ContentValues();
       /**This value is for Execrise**/
       ContentValues values2 = new ContentValues();
       /**This value is for Execrise**/
       ContentValues values3 = new ContentValues();

       values.put(userEntry.COLUMN_User_NAME, name);
       values.put(userEntry.COLUMN_User_GENDER, mGender);
       values.put(userEntry.COLUMN_User_AGE, age);
       values1.put(blogEntry.COLUMN_Blog_NAME,"GGG");
       values1.put(blogEntry.COLUMN_Blog_DESCRIPTION,"er");
       values2.put(exerciseEntry.COLUMN_Exercise_NAME,"RUN");
      values2.put(exerciseEntry.COLUMN_Exercise_DESCRIPTION,"vvv");

       values3.put(nutritionEntry.COLUMN_Nutrition_NAME,"Beef stew");
       values3.put(nutritionEntry.COLUMN_Nutrition_Calories,"600 gm");
       values3.put(nutritionEntry.COLUMN_Nutrition_Vegan, 0);
       values3.put(nutritionEntry.COLUMN_Nutrition_Protein, 45);

       if (age<=0 || age>150){
           Toast.makeText(this, "Error in  saving  data check your Age !", Toast.LENGTH_SHORT).show();

       }
else{
            db.insert(blogEntry.TABLE_NAME, null, values1);
    db.insert(userEntry.TABLE_NAME, null, values);
           newRowId =  db.insert(nutritionEntry.TABLE_NAME, null, values3);

           db.insert(exerciseEntry.TABLE_NAME, null, values2);




      }

       if (newRowId==-1) {
           Toast.makeText(this, "Error in  saving  data !", Toast.LENGTH_SHORT).show();

       }
       else{
           Toast.makeText(this, "Pet save with id ! "+newRowId, Toast.LENGTH_SHORT).show();
       }
    }
}

