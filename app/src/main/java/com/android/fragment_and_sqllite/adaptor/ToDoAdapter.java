package com.android.fragment_and_sqllite.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.fragment_and_sqllite.R;
import com.android.fragment_and_sqllite.object.ToDo;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoHolder>{


    private ArrayList<ToDo> listTodo = new ArrayList<>();

    public ToDoAdapter(ArrayList<ToDo> listTodo){
        this.listTodo = listTodo;
    }

    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_cart, parent, false);

        return new ToDoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoHolder holder, int position) {
        ToDo todo = listTodo.get(position);
        holder.setWidget(todo);
    }

    @Override
    public int getItemCount() {
        return listTodo.size();
    }


    public static class ToDoHolder extends RecyclerView.ViewHolder {

        private TextView tvid, tvtitle, tvdecription;

        public ToDoHolder(@NonNull View itemView) {
            super(itemView);
            this.tvid = itemView.findViewById(R.id.tvid);
            this.tvtitle = itemView.findViewById(R.id.tvtitle);
            this.tvdecription = itemView.findViewById(R.id.tvdescription);
        }

        public void setWidget(ToDo todo){
            tvid.setText("TODO ID : " + todo.getTodo_id());
            tvtitle.setText("Title : " + todo.getTitle());
            tvdecription.setText(todo.getDescription());
        }
    }

}
