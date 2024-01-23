package com.android.fragment_and_sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.fragment_and_sqllite.dao.ToDoDAO;
import com.android.fragment_and_sqllite.dao.UserDAO;
import com.android.fragment_and_sqllite.fragment.Add;
import com.android.fragment_and_sqllite.fragment.Dashboard;
import com.android.fragment_and_sqllite.object.ToDo;
import com.android.fragment_and_sqllite.object.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // lu declare dulu Data Access Object nya dulu
    // userDAO sama todoDAO

    private UserDAO userDAO;
    private ToDoDAO toDoDAO;

    private User user = new User();

    private Dashboard fr1;
    private Add fr2;
    private TextView tvname, tvusername, tvpassword;
    private Button btnhome, btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDAO = new UserDAO(this);
        toDoDAO = new ToDoDAO(this);

        tvname = findViewById(R.id.tvname);
        tvusername = findViewById(R.id.tvemail);
        tvpassword = findViewById(R.id.tvpassword);

        btnhome = findViewById(R.id.btnhome);
        btnadd = findViewById(R.id.btnadd);

        fr1 = new Dashboard();
        fr2 = new Add();



        start("Martin", "martin@gmail.com", "Test123");

        fr1.setUser(user);
        fr1.setTodolist(user.getTodoList());
        displayFragment(fr1);


        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fr1.setUser(user);
                fr1.setTodolist(user.getTodoList());
                displayFragment(fr1);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setFr2();
                displayFragment(fr2);

                user.setTodoList(fr2.getTodolist());


            }
        });

    }
    private void setFr2(){
        fr2.setUser(user);
        fr2.setTodolist(user.getTodoList());
        fr2.setToDoDAO(toDoDAO);
    }

    private void displayFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flmenu, fragment);
        ft.commit();
    }




    private void start(String name, String email, String password){


        userDAO.open();
        toDoDAO.open();

        long userid = userDAO.addUser(email,name,password);
        long todoId;

        todoId = toDoDAO.addTodo(userid, "Membuat SQLite", "Belajar membuat aplikasi Android");
        todoId = toDoDAO.addTodo(userid, "Membuat Fragment", "Belajar membuat aplikasi Android");
        todoId = toDoDAO.addTodo(userid, "Membuat RecycleView", "Belajar membuat aplikasi Android");

        ArrayList<ToDo> getTodo = toDoDAO.getAllTodoByUser(userid);


        tvname.setText(name);
        tvusername.setText(email);
        tvpassword.setText(password);

        for (int i = 0; i < getTodo.size(); i++){
            Log.v("TODOLIST", "INDEX " + i);
            Log.v("TODOLIST", "userid " + getTodo.get(i).getUser_id());
            Log.v("TODOLIST", "todoid " + getTodo.get(i).getTodo_id());
            Log.v("TODOLIST", "title " + getTodo.get(i).getTitle());
            Log.v("TODOLIST", "description " + getTodo.get(i).getDescription());
            Log.v("TODOLIST", "+++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        user.setTodoList(getTodo);


    }





}