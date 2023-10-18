package com.example.krillinswife;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE = "USER_TABLE";

    public static final String COLUM_USERNAME = "USERNAME";
    public static final String COLUM_PASSWORD = "PASSWORD";
    public static final String COLUM_ID = "ID";
    public static final String COLUM_NAME = "NAME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUM_USERNAME + " TEXT, " + COLUM_PASSWORD + " TEXT, " + COLUM_NAME + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUM_USERNAME, user.getUsername());
        cv.put(COLUM_PASSWORD, user.getPassword());
        cv.put(COLUM_NAME, user.getName());

        long insert = db.insert(USER_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<User> readUsers()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUser
                = db.rawQuery("SELECT * FROM " + USER_TABLE, null);

        // on below line we are creating a new array list.
        ArrayList<User> courseModalArrayList
                = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.
                courseModalArrayList.add(new User(
                        cursorUser.getInt(0), // ID
                        cursorUser.getString(1), // Username
                        cursorUser.getString(2), // Password
                        cursorUser.getString(3))); // Name
            } while (cursorUser.moveToNext());

        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return courseModalArrayList;
    }

    public boolean deleteOne(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + USER_TABLE + " WHERE " + COLUM_ID + " = " + user.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
}
