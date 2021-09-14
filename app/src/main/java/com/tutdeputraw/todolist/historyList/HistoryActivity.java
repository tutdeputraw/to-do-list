package com.tutdeputraw.todolist.historyList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.R;
import com.tutdeputraw.todolist.historyList.adapter.HistoryListAdapter;
import com.tutdeputraw.todolist.historyList.data.HistoryList;

public class HistoryActivity extends AppCompatActivity {
    private HistoryListAdapter historyListAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListAdapter = new HistoryListAdapter(this, HistoryList.historyList);
        listView = findViewById(R.id.listItem);
        listView.setAdapter(historyListAdapter);
    }

    public void back(View view) {
        this.finish();
    }
}