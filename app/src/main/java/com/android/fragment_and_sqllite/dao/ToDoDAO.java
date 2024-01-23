package com.android.fragment_and_sqllite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.fragment_and_sqllite.dbhelper.DatabaseContract;
import com.android.fragment_and_sqllite.dbhelper.DatabaseHelper;
import com.android.fragment_and_sqllite.object.ToDo;

import java.util.ArrayList;

public class ToDoDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ToDoDAO(Context mCtx){
        dbHelper = new DatabaseHelper(mCtx);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public long addTodo(long userid, String title, String description){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.TodoEntry.COLUMN_NAME_title,title);
        cv.put(DatabaseContract.TodoEntry.COLUMN_NAME_DESCRIPTION,description);
        cv.put(DatabaseContract.TodoEntry.COLUMN_NAME_USER_ID,userid);



        return database.insert(DatabaseContract.TodoEntry.TABLE_NAME, null, cv);
    }

    public ArrayList<ToDo> getAllTodoByUser(long user_id){
        ArrayList<ToDo> userTodoList = new ArrayList<>();

        String[] projection = {
                DatabaseContract.TodoEntry._ID,
                DatabaseContract.TodoEntry.COLUMN_NAME_USER_ID,
                DatabaseContract.TodoEntry.COLUMN_NAME_title,
                DatabaseContract.TodoEntry.COLUMN_NAME_DESCRIPTION

        };

        String Selection = DatabaseContract.TodoEntry.COLUMN_NAME_USER_ID + " = ?";
        String[] selectionArgs = {Long.toString(user_id)};


        Cursor cursor = database.query(
                DatabaseContract.TodoEntry.TABLE_NAME,
                null,
                Selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor != null ){
            while(cursor.moveToNext()){
                long todoid = cursor.getLong(cursor.getColumnIndex(DatabaseContract.TodoEntry._ID));
                long userid = cursor.getLong(cursor.getColumnIndex(DatabaseContract.TodoEntry.COLUMN_NAME_USER_ID));
                String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TodoEntry.COLUMN_NAME_title));
                String description = cursor.getString(cursor.getColumnIndex(DatabaseContract.TodoEntry.COLUMN_NAME_DESCRIPTION));

                userTodoList.add(new ToDo(userid,todoid,title,description));

            }


        }

        close();
        return userTodoList;
    }

}
