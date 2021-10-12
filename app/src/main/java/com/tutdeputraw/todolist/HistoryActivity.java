package com.tutdeputraw.todolist;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tutdeputraw.todolist.database.account.Session;
import com.tutdeputraw.todolist.adapter.CompletedTaskListAdapter;
import com.tutdeputraw.todolist.database.task.TaskDatabase;
import com.tutdeputraw.todolist.database.task.model.Task;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {
    private Session session;
    private RecyclerView recyclerView;
    private CompletedTaskListAdapter completedTaskListAdapter;
    private TaskDatabase database;
    private final List<Task> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycler_view2);
        session = new Session(this);

        getDatabaseInstance();
        initList();
        setAdapter();
        setRecyclerView();
    }

    private void initList() {
        list.clear();
        list.addAll(database.taskDao().getCompletedTask(session.getUsername()));
    }

    private void getDatabaseInstance() {
        database = TaskDatabase.getInstance(getApplicationContext());
    }

    private void setAdapter() {
        completedTaskListAdapter =
                new CompletedTaskListAdapter(getApplicationContext(), list);
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(completedTaskListAdapter);
    }

    public void back(View view) {
        this.finish();
    }

    private void refresh() {
        list.clear();
        list.addAll(database.taskDao().getCompletedTask(session.getUsername()));
        completedTaskListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }
}