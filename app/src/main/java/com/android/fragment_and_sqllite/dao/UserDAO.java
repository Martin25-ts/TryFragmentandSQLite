package com.android.fragment_and_sqllite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.fragment_and_sqllite.dbhelper.DatabaseContract;
import com.android.fragment_and_sqllite.dbhelper.DatabaseHelper;
import com.android.fragment_and_sqllite.object.User;

public class UserDAO {
    // Data Access Object
    // Kalo mau CRUD Di sini
    // logic nya kaya misal mau insert, update dll di sini

    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    public UserDAO (Context mCtx){
        dbHelper = new DatabaseHelper(mCtx);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public User getUser(String email, String password){
        User user = null;


        // ini tuh buat SELECT apa aja karena mau semuanya yauda kita tulis semua
        String[] projection = {
                DatabaseContract.UserEntry._ID,
                DatabaseContract.UserEntry.COLUMN_NAME_NAME,
                DatabaseContract.UserEntry.COLUMN_NAME_USERNAME,
                DatabaseContract.UserEntry.COLUMN_NAME_PASSWORD
        };

        // ini tuh where clause kaya apa yang mau di check
        String selection =
                DatabaseContract.UserEntry.COLUMN_NAME_USERNAME + " = ? AND " +
                DatabaseContract.UserEntry.COLUMN_NAME_PASSWORD + " = ? ";

        // Ini buat nentuin tand '?' di isi sama apa
        String[] selectinoArgs = {email,password};

        // cursor ini buat query aja
        Cursor cursor = database.query(
                DatabaseContract.UserEntry.TABLE_NAME, // param 1 buat table name
                projection, // ini buat select sebenarnya bisa langsung tulis null artinya SELECT * FROM user
                selection, // param 3 untuk where clausenya
                selectinoArgs, // param 4 buat isi '?' dari where clausenya
                null, // Group By
                null, // having
                null // order by
        );

        if(cursor != null && cursor.moveToFirst()){
            user = new User();
            user.setUser_id(cursor.getLong(cursor.getColumnIndex(DatabaseContract.UserEntry._ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_NAME_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_NAME_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_NAME_PASSWORD)));


        }

        close();
        return user;
    }

    public long addUser(String username, String password, String name){

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UserEntry.COLUMN_NAME_NAME, name);
        values.put(DatabaseContract.UserEntry.COLUMN_NAME_USERNAME, username);
        values.put(DatabaseContract.UserEntry.COLUMN_NAME_PASSWORD, password);

        return database.insert(DatabaseContract.UserEntry.TABLE_NAME, null, values);
    }




}
