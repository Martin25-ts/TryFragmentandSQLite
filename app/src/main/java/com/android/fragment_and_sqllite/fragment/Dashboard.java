package com.android.fragment_and_sqllite.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fragment_and_sqllite.R;
import com.android.fragment_and_sqllite.adaptor.ToDoAdapter;
import com.android.fragment_and_sqllite.object.ToDo;
import com.android.fragment_and_sqllite.object.User;

import java.util.ArrayList;


public class Dashboard extends Fragment {

    RecyclerView rvtodo;

    private User user;
    private ArrayList<ToDo> todolist = new ArrayList<>();

    public Dashboard() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        rvtodo = view.findViewById(R.id.rvtodo);
        rvtodo.setLayoutManager(new LinearLayoutManager(getActivity()));

        ToDoAdapter toDoAdapter = new ToDoAdapter(this.todolist);
        rvtodo.setAdapter(toDoAdapter);

        return view;
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
}