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
import com.tutdeputraw.todolist.database.local.TaskDatabase;
import com.tutdeputraw.todolist.database.model.Task;

import java.util.List;

public class CompletedTaskListAdapter extends RecyclerView.Adapter<CompletedTaskListAdapter.ViewAdapter> {
    private List<Task> list;
    private Context context;
    private TaskDatabase database;

    public CompletedTaskListAdapter(Context context, List<Task> list) {
        this.list = list;
        this.context = context;
        database = TaskDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public CompletedTaskListAdapter.ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history, parent, false);
        return new CompletedTaskListAdapter.ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedTaskListAdapter.ViewAdapter holder, int position) {
        Task task = list.get(position);
        holder.heading.setText(task.name);

        holder.restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.taskDao().update(task.uid, task.name, 0);
                holder.refresh();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewAdapter extends RecyclerView.ViewHolder {
        TextView heading;
        Button restore;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            restore = itemView.findViewById(R.id.restore_button);
        }

        public void refresh() {
            list.clear();
            list.addAll(database.taskDao().getCompletedTask());
            notifyDataSetChanged();
        }
    }
}
