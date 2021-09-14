package com.tutdeputraw.todolist.toDoList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.R;
import com.tutdeputraw.todolist.historyList.HistoryActivity;
import com.tutdeputraw.todolist.models.ToDo;
import com.tutdeputraw.todolist.toDoList.adapter.HomeListAdapter;
import com.tutdeputraw.todolist.toDoList.data.ToDoList;

public class MainActivity extends AppCompatActivity {
    private HomeListAdapter homeListAdapter;
    private ListView listView;
    private PopupWindow popupWindow;
    private View popupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDummyData();
        setAdapter();
    }

    private void addDummyData() {
        ToDoList.todoList.add(new ToDo("todo 1"));
        ToDoList.todoList.add(new ToDo("todo 2"));
        ToDoList.todoList.add(new ToDo("todo 3"));
        ToDoList.todoList.add(new ToDo("todo 4"));
        ToDoList.todoList.add(new ToDo("todo 5"));
        ToDoList.todoList.add(new ToDo("todo 6"));
        ToDoList.todoList.add(new ToDo("todo 7"));
        ToDoList.todoList.add(new ToDo("todo 8"));
        ToDoList.todoList.add(new ToDo("todo 9"));
        ToDoList.todoList.add(new ToDo("todo 10"));
        ToDoList.todoList.add(new ToDo("todo 11"));
        ToDoList.todoList.add(new ToDo("todo 12"));
        ToDoList.todoList.add(new ToDo("todo 13"));
        ToDoList.todoList.add(new ToDo("todo 14"));
        ToDoList.todoList.add(new ToDo("todo 15"));
    }

    private void setAdapter() {
        homeListAdapter = new HomeListAdapter(this, ToDoList.todoList);
        listView = findViewById(R.id.listItem);
        listView.setAdapter(homeListAdapter);
    }

    public void fabOnClick(View view) {
        Intent i = new Intent(getApplicationContext(), AddPopupActivity.class);
        startActivity(i);
    }

    public void clickHistory(View view) {
        Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(i);
    }
}