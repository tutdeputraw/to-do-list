package com.tutdeputraw.todolist.toDoList.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.tutdeputraw.todolist.R;
import com.tutdeputraw.todolist.historyList.data.HistoryList;
import com.tutdeputraw.todolist.models.ToDo;
import com.tutdeputraw.todolist.toDoList.EditPopupActivity;

import java.util.ArrayList;

public class HomeListAdapter extends ArrayAdapter<ToDo> {
    private final Activity context;
    ArrayList<ToDo> data;
    PopupWindow popupWindow;

    public HomeListAdapter(Activity context, ArrayList<ToDo> data) {
        super(context, R.layout.todo, data);
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.todo, null, true);

        CardView cardView = rowView.findViewById(R.id.base_cardview);
        ImageButton arrow = rowView.findViewById(R.id.arrow_button);
        LinearLayout hiddenView = rowView.findViewById(R.id.hidden_view);
        Button btnDone = rowView.findViewById(R.id.btn_done);
        Button btnEdit = rowView.findViewById(R.id.btn_edit);
        Button btnClose = rowView.findViewById(R.id.btn_close);


        TextView header = rowView.findViewById(R.id.heading);
        header.setText(data.get(position).getName());

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < data.size(); i++) {
                    System.out.println(data.get(i).getName());
                }
                HistoryList.historyList.add(data.get(position));
                data.remove(position);

                TransitionManager.beginDelayedTransition(cardView,
                        new AutoTransition());
                hiddenView.setVisibility(View.GONE);
                arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditPopupActivity.class);
                context.startActivity(i);
            }
        });

//        btnClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.finish();
//            }
//        });


        return rowView;
    }
}