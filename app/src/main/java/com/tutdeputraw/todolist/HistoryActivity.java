package com.tutdeputraw.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.adapter.HistoryListAdapter;
import com.tutdeputraw.todolist.data.HistoryDataList;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private HistoryListAdapter historyListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListAdapter = new HistoryListAdapter(this, HistoryDataList.toDoList);
        listView = findViewById(R.id.listItem);
        listView.setAdapter(historyListAdapter);
    }

    public void back(View view) {
        this.finish();
    }
}