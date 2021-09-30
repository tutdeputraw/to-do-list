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

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UncompletedTaskListAdapter uncompletedTaskListAdapter;
    private TaskDatabase database;
    private List<Task> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        uncompletedTaskListAdapter = new UncompletedTaskListAdapter(MainActivity.this, list);
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

    //        @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d("lifecycle", "MainActivity onStart invoked");
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d("lifecycle", "MainActivity onResume invoked");
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d("lifecycle", "MainActivity onPause invoked");
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d("lifecycle", "MainActivity onStop invoked");
//    }
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.d("lifecycle", "MainActivity onRestart invoked");
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d("lifecycle", "MainActivity onDestroy invoked");
//    }


    //    private HomeListAdapter homeListAdapter;
//    private ListView listView;
//    private PopupWindow popupWindow;
//    private View popupView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        addDummyData();
//        setAdapter();
//    }
//
//    private void addDummyData() {
//        ToDoList.todoList.add(new ToDo("todo 1"));
//        ToDoList.todoList.add(new ToDo("todo 2"));
//        ToDoList.todoList.add(new ToDo("todo 3"));
//        ToDoList.todoList.add(new ToDo("todo 4"));
//        ToDoList.todoList.add(new ToDo("todo 5"));
//        ToDoList.todoList.add(new ToDo("todo 6"));
//        ToDoList.todoList.add(new ToDo("todo 7"));
//        ToDoList.todoList.add(new ToDo("todo 8"));
//        ToDoList.todoList.add(new ToDo("todo 9"));
//        ToDoList.todoList.add(new ToDo("todo 10"));
//        ToDoList.todoList.add(new ToDo("todo 11"));
//        ToDoList.todoList.add(new ToDo("todo 12"));
//        ToDoList.todoList.add(new ToDo("todo 13"));
//        ToDoList.todoList.add(new ToDo("todo 14"));
//        ToDoList.todoList.add(new ToDo("todo 15"));
//    }
//
//    private void setAdapter() {
//        homeListAdapter = new HomeListAdapter(this, ToDoList.todoList);
//        listView = findViewById(R.id.listItem);
//        listView.setAdapter(homeListAdapter);
//    }
//
//    public void fabOnClick(View view) {
//        Intent i = new Intent(getApplicationContext(), AddPopupActivity.class);
//        startActivity(i);
//    }
//
//    public void clickHistory(View view) {
//        Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
//        startActivity(i);
//    }
}