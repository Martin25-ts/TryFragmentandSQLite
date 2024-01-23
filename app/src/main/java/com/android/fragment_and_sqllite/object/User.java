package com.android.fragment_and_sqllite.object;

import java.util.ArrayList;

public class User {
    protected long user_id;

    protected String name;
    protected String email;
    protected String password;


    private ArrayList<ToDo> todoList;

    public ArrayList<ToDo> getTodoList() {
        return todoList;
    }

    public void setTodoList(ArrayList<ToDo> todoList) {
        this.todoList = todoList;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
