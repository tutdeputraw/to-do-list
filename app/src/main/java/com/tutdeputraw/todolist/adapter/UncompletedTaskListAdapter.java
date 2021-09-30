package com.tutdeputraw.todolist.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.tutdeputraw.todolist.EditPopupActivity;
import com.tutdeputraw.todolist.R;
import com.tutdeputraw.todolist.database.local.TaskDatabase;
import com.tutdeputraw.todolist.database.model.Task;

import java.util.List;

public class UncompletedTaskListAdapter
        extends RecyclerView.Adapter<UncompletedTaskListAdapter.ViewAdapter> {
    private final List<Task> list;
    private final Context context;
    private final TaskDatabase database;

    public UncompletedTaskListAdapter(Context context, List<Task> list) {
        this.list = list;
        this.context = context;
        database = TaskDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent,
                                          int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.todo, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        Task task = list.get(position);
        holder.heading.setText(task.name);

        holder.btnDone.setOnClickListener(v -> {
            Log.e("BTN DONE", "TEST 123");
            database.taskDao().update(task.uid, task.name, 1);
            holder.manageExpandableCard();
            holder.refreshList();
        });

        holder.btnEdit.setOnClickListener(v -> {
            Intent i = new Intent(context, EditPopupActivity.class);
            i.putExtra("uid", task.uid);
            context.startActivity(i);
        });

        holder.arrow.setOnClickListener(v -> {
            holder.manageExpandableCard();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder {
        TextView heading;
        ImageButton arrow;
        LinearLayout hiddenView;
        CardView cardView;
        Button btnEdit;
        Button btnDone;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            arrow = itemView.findViewById(R.id.arrow_button);
            hiddenView = itemView.findViewById(R.id.hidden_view);
            cardView = itemView.findViewById(R.id.base_cardview);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDone = itemView.findViewById(R.id.btn_done);
        }

        void manageExpandableCard() {
            if (isCardVisible()) {
                openCard();
            } else {
                closeCard();
            }
        }

        private boolean isCardVisible() {
            return hiddenView.getVisibility() == View.VISIBLE;
        }

        private void openCard() {
            TransitionManager.beginDelayedTransition(cardView,
                    new AutoTransition());
            hiddenView.setVisibility(View.GONE);
            arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
        }

        private void closeCard() {
            TransitionManager.beginDelayedTransition(cardView,
                    new AutoTransition());
            hiddenView.setVisibility(View.VISIBLE);
            arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
        }

        void refreshList() {
            list.clear();
            list.addAll(database.taskDao().getUncompletedTask());
            notifyDataSetChanged();
        }
    }
}
