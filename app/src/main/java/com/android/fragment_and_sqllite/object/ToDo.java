package com.android.fragment_and_sqllite.object;

public class ToDo {

    protected long user_id, todo_id;
    protected String title;
    protected String description;


    public ToDo(long user_id, long todo_id, String title, String description) {
        this.user_id = user_id;
        this.todo_id = todo_id;
        this.title = title;
        this.description = description;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getTodo_id() {
        return todo_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
