package com.tutdeputraw.todolist.historyList.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tutdeputraw.todolist.R;
import com.tutdeputraw.todolist.models.ToDo;

import java.util.ArrayList;

public class HistoryListAdapter extends ArrayAdapter<ToDo> {
    private final Activity context;
    ArrayList<ToDo> data;

    public HistoryListAdapter(Activity context, ArrayList<ToDo> data) {
        super(context, R.layout.todo, data);
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.history, null, true);

        TextView header = rowView.findViewById(R.id.heading);
        header.setText(data.get(position).getName());

        return rowView;
    }
}
