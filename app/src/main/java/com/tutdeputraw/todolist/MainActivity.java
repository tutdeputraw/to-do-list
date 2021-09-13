package com.tutdeputraw.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.tutdeputraw.todolist.adapter.ListAdapter;
import com.tutdeputraw.todolist.models.ToDo;

public class MainActivity extends AppCompatActivity {
    private DataList dataList = new DataList();
    private PopupWindow popupWindow;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDataManually();

        listAdapter = new ListAdapter(this, dataList.toDoList);
        listView = findViewById(R.id.listItem);
        listView.setAdapter(listAdapter);
    }

    private void addDataManually() {
        dataList.toDoList.add(new ToDo("todo 1"));
        dataList.toDoList.add(new ToDo("todo 2"));
        dataList.toDoList.add(new ToDo("todo 3"));
        dataList.toDoList.add(new ToDo("todo 4"));
        dataList.toDoList.add(new ToDo("todo 5"));
        dataList.toDoList.add(new ToDo("todo 6"));
        dataList.toDoList.add(new ToDo("todo 7"));
        dataList.toDoList.add(new ToDo("todo 8"));
        dataList.toDoList.add(new ToDo("todo 9"));
        dataList.toDoList.add(new ToDo("todo 10"));
    }

    public void addToDo(View view) {
        dataList.toDoList.remove(2);
//        listView.setAdapter(listAdapter);

//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.popup_window, null);
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window tolken
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public void makeToDo(View view) {
    }

    public void closePopup(View view) {
        popupWindow.dismiss();
    }

    public void clickHistory(View view) {
        Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(i);
    }
}