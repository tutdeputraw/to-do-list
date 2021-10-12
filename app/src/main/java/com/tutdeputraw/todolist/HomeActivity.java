package com.tutdeputraw.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tutdeputraw.todolist.adapter.UncompletedTaskListAdapter;
import com.tutdeputraw.todolist.database.local.TaskDatabase;
import com.tutdeputraw.todolist.database.model.Task;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UncompletedTaskListAdapter uncompletedTaskListAdapter;
    private TaskDatabase database;
    private final List<Task> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler_view1);

        getDatabaseInstance();
        initList();
        setAdapter();
        setRecyclerView();
    }

    private void initList() {
        list.clear();
        list.addAll(database.taskDao().getUncompletedTask());
    }

    private void getDatabaseInstance() {
        database = TaskDatabase.getInstance(getApplicationContext());
    }

    private void setAdapter() {
        uncompletedTaskListAdapter = new UncompletedTaskListAdapter(HomeActivity.this, list);
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(uncompletedTaskListAdapter);
    }

    public void fabOnClick(View view) {
        Intent i = new Intent(getApplicationContext(), AddPopupActivity.class);
        startActivity(i);
    }

    public void historyOnClick(View view) {
        Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(i);
    }

    private void refresh() {
        list.clear();
        list.addAll(database.taskDao().getUncompletedTask());
        uncompletedTaskListAdapter.notifyDataSetChanged();
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