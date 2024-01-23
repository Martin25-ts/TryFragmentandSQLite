package com.android.fragment_and_sqllite.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.fragment_and_sqllite.R;
import com.android.fragment_and_sqllite.dao.ToDoDAO;
import com.android.fragment_and_sqllite.dao.UserDAO;
import com.android.fragment_and_sqllite.object.ToDo;
import com.android.fragment_and_sqllite.object.User;

import java.util.ArrayList;

public class Add extends Fragment {


    private EditText ettitle, etdescription;
    private Button btnadd;
    private ToDoDAO toDoDAO;
    private User user;

    private ArrayList<ToDo> todolist = new ArrayList<>();

    public Add() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        ettitle = view.findViewById(R.id.ettitle);
        etdescription = view.findViewById(R.id.etdescription);
        btnadd = view.findViewById(R.id.btnsave);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addTodo(user.getUser_id(), ettitle.getText().toString(), etdescription.getText().toString());
            }
        });

        return view;
    }

    private void addTodo(long userid, String title, String description){
        toDoDAO.open();

        long todoid =  toDoDAO.addTodo(userid, title, description);
        Log.d("AddFragment", "Adding todo: User ID: " + userid + ", Title: " + title + ", Description: " + description);

        todolist.add(new ToDo(userid, todoid,title, description));

        toDoDAO.close();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<ToDo> getTodolist() {
        return todolist;
    }

    public void setTodolist(ArrayList<ToDo> todolist) {
        this.todolist = todolist;
    }

    public ToDoDAO getToDoDAO() {
        return toDoDAO;
    }

    public void setToDoDAO(ToDoDAO toDoDAO) {
        this.toDoDAO = toDoDAO;
    }
}