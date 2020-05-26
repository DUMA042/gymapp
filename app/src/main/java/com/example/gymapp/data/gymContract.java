package com.example.gymapp.data;

import android.provider.BaseColumns;

public class gymContract {
    gymContract(){}
  public static class userEntry implements BaseColumns{
      public static final String TABLE_NAME = "User";
      /****/
      public static final String  _ID = BaseColumns._ID;
      public static final String COLUMN_User_NAME = "Name";
      public static final String COLUMN_User_GENDER = "gender";
      public static final String COLUMN_User_AGE = "age";
      /****/
      public static final int gender_MALE = 1;
      public static final int gender_FEMALE = 2;
      public static final int gender_UNKNOWN = 0;
    }
    public static class blogEntry implements BaseColumns{
        public static final String TABLE_NAME = "Blog";
        /****/
        public static final String  _ID = BaseColumns._ID;
        public static final String COLUMN_Blog_NAME = "blog_Name";
        public static final String COLUMN_Blog_DESCRIPTION = "blog_Description";


    }
    public static class exerciseEntry implements BaseColumns{
        public static final String TABLE_NAME = "Exercise";
        /****/
        public static final String  _ID = BaseColumns._ID;
        public static final String COLUMN_Exercise_NAME = "Exercise_Name";
        public static final String COLUMN_Exercise_DESCRIPTION = "Exercise_Description";


    }

    public static class nutritionEntry implements BaseColumns {
        public static final String TABLE_NAME = "Nutrition";
        /****/
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_Nutrition_NAME = "Nutrition_Name";
        public static final String COLUMN_Nutrition_Calories = "Nutrition_Calories";
        public static final String COLUMN_Nutrition_Vegan = "Nutrition_Vegan";
        public static final String COLUMN_Nutrition_Protein = "Nutrition_Total_Protein";
    }

    public static class routineEntry implements BaseColumns {
        public static final String TABLE_NAME = "Routine";
        /****/
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_Routine_NAME = "Routine_Name";
        public static final String COLUMN_Routine_Calories = "Routine_Calories";
        public static final String COLUMN_Routine_Vegan = "Routine_Vegan";
        public static final String COLUMN_Routie_Protein = "Routine_Total_Protein";
    }






}
