package com.example.krillinswife;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String PERSON_TABLE = "PERSON_TABLE";
    public static final String COLUM_USERNAME = "USERNAME";
    public static final String COLUM_PASSWORD = "PASSWORD";
    public static final String COLUM_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PERSON_TABLE + " (" + COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUM_USERNAME + " TEXT, " + COLUM_PASSWORD + " TEXT)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUM_USERNAME, person.getUsername());
        cv.put(COLUM_PASSWORD, person.getPassword());

        long insert = db.insert(PERSON_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
}
