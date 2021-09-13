package com.tutdeputraw.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.adapter.HomeListAdapter;
import com.tutdeputraw.todolist.data.DataList;
import com.tutdeputraw.todolist.models.ToDo;

public class MainActivity extends AppCompatActivity {
    private DataList dataList = new DataList();
    private PopupWindow popupWindow;
    private ListView listView;
    private HomeListAdapter homeListAdapter;
    View popupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDataManually();

        homeListAdapter = new HomeListAdapter(this, dataList.toDoList);
        listView = findViewById(R.id.listItem);
        listView.setAdapter(homeListAdapter);
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
        dataList.toDoList.add(new ToDo("todo 11"));
        dataList.toDoList.add(new ToDo("todo 12"));
        dataList.toDoList.add(new ToDo("todo 13"));
        dataList.toDoList.add(new ToDo("todo 14"));
        dataList.toDoList.add(new ToDo("todo 15"));
    }

    public void addToDo(View view) {
//        dataList.toDoList.remove(2);
//        listView.setAdapter(listAdapter);
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.popup_make_todolist, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public void closePopup(View view) {
        popupWindow.dismiss();
    }

    public void clickHistory(View view) {
        Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(i);
    }

    public void addData(View view) {
        EditText edtTitle = popupView.findViewById(R.id.edt_todo);
        String title = edtTitle.getText().toString();

        dataList.add(new ToDo(title));
        popupWindow.dismiss();
    }
}