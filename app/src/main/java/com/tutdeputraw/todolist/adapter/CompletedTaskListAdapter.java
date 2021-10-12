package com.tutdeputraw.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tutdeputraw.todolist.R;
import com.tutdeputraw.todolist.database.account.Session;
import com.tutdeputraw.todolist.database.task.TaskDatabase;
import com.tutdeputraw.todolist.database.task.model.Task;

import java.util.List;

public class CompletedTaskListAdapter
        extends RecyclerView.Adapter<CompletedTaskListAdapter.ViewAdapter> {
    private final List<Task> list;
    private final Context context;
    private final TaskDatabase database;

    public CompletedTaskListAdapter(Context context, List<Task> list) {
        this.list = list;
        this.context = context;
        database = TaskDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public CompletedTaskListAdapter.ViewAdapter onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.history, parent, false);
        return new CompletedTaskListAdapter.ViewAdapter(view, context);
    }

    @Override
    public void onBindViewHolder(
            @NonNull CompletedTaskListAdapter.ViewAdapter holder, int position) {
        Task task = list.get(position);
        holder.heading.setText(task.name);

        holder.restore.setOnClickListener(v -> {
            Session session = new Session(context);
            database.taskDao().update(task.uid, task.name, 0, session.getUsername());
            holder.refresh();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewAdapter extends RecyclerView.ViewHolder {
        Context context;
        TextView heading;
        Button restore;

        public ViewAdapter(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            heading = itemView.findViewById(R.id.heading);
            restore = itemView.findViewById(R.id.restore_button);
        }

        public void refresh() {
            Session session = new Session(context);
            list.clear();
            list.addAll(database.taskDao().getCompletedTask(session.getUsername()));
            notifyDataSetChanged();
        }
    }
}
