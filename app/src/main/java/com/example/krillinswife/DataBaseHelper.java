package com.example.krillinswife;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

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

    public boolean addOne(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUM_USERNAME, user.getUsername());
        cv.put(COLUM_PASSWORD, user.getPassword());
        cv.put(COLUM_NAME, user.getName());

        long insert = db.insert(USER_TABLE, null, cv);
        return insert != -1;
    }

    public ArrayList<User> readUsers() {

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
        return courseModalArrayList;
    }

    public boolean deleteOne(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(USER_TABLE, COLUM_ID + " = ?", new String[]{String.valueOf(id)});
        return delete != -1;
    }

    public boolean updateOne(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUM_USERNAME, user.getUsername());
        cv.put(COLUM_PASSWORD, user.getPassword());
        cv.put(COLUM_NAME, user.getName());

        long update = db.update(USER_TABLE, cv, COLUM_ID + " = ?", new String[]{String.valueOf(user.getId())});
        return update != -1;
    }

    public User findUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUM_ID + " = " + id;
        Cursor cursor = db.rawQuery(queryString, null);
        User user = null;
        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String name = cursor.getString(3);
            user = new User(userId, username, password, name);
        }
        return user;
    }

    //encrypt password using md5
    public String encryptPassword(String password) {

        /* Plain-text password initialization. */
        String encryptedpassword = null;
        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return encryptedpassword;
    }


    public User checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUM_USERNAME + " = '" + username + "' AND " + COLUM_PASSWORD + " = '" + password + "'";
        Cursor cursor = db.rawQuery(queryString, null);
        User user = null;

        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            String name = cursor.getString(3);
            user = new User(userId, username, password, name);
        }
        return user;
    }

}
