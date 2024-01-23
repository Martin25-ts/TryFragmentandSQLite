package com.android.fragment_and_sqllite.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "MyApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_USER_ENTRIES =
            "CREATE TABLE " + DatabaseContract.UserEntry.TABLE_NAME + " (" +
                    DatabaseContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DatabaseContract.UserEntry.COLUMN_NAME_NAME + " TEXT," +
                    DatabaseContract.UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    DatabaseContract.UserEntry.COLUMN_NAME_PASSWORD + " TEXT);";

    private static final String SQL_CREATE_TABLE_TODO_ENTRIES =
            "CREATE TABLE " + DatabaseContract.TodoEntry.TABLE_NAME + " (" +
                    DatabaseContract.TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.TodoEntry.COLUMN_NAME_title + " TEXT," +
                    DatabaseContract.TodoEntry.COLUMN_NAME_DESCRIPTION +  " TEXT," +
                    DatabaseContract.TodoEntry.COLUMN_NAME_USER_ID + " INTEGER," +
                    "FOREIGN KEY ( " + DatabaseContract.TodoEntry.COLUMN_NAME_USER_ID + ") REFERENCES " +
                    DatabaseContract.UserEntry.TABLE_NAME + "(" + DatabaseContract.UserEntry._ID + "));";


    private static final String SQL_DROP_USER_ENTERIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.UserEntry.TABLE_NAME;

    private static final String SQL_DROP_TODO_ENTERIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.TodoEntry.TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USER_ENTRIES);
        db.execSQL(SQL_CREATE_TABLE_TODO_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DROP_TODO_ENTERIES);
        db.execSQL(SQL_DROP_USER_ENTERIES);
        onCreate(db);
    }
}
