package com.tutdeputraw.todolist.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.transition.TransitionManager;

import com.tutdeputraw.todolist.R;
import com.tutdeputraw.todolist.models.ToDo;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ToDo> {
    private final Activity context;
    ArrayList<ToDo> data;

    public ListAdapter(Activity context, ArrayList<ToDo> data) {
        super(context, R.layout.todo, data);
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.todo, null, true);

        TextView nameText = rowView.findViewById(R.id.titleTodo);
        nameText.setText(data.get(position).getName());

        return rowView;
    }
}