package com.tutdeputraw.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.database.account.Session;
import com.tutdeputraw.todolist.database.task.TaskDatabase;
import com.tutdeputraw.todolist.database.task.model.Task;

public class EditPopupActivity extends AppCompatActivity {
    private EditText todoName;
    private TaskDatabase database;
    private int uid = 0;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_popup);

        todoName = findViewById(R.id.edt_todo);

        initDatabase();
        getIntentExtra();
        initializeTask();
        setTextTodoEditText(task.name);
        initPopup();
    }

    private void initDatabase() {
        database = TaskDatabase.getInstance(getApplicationContext());
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
    }

    private void initializeTask() {
        Session session = new Session(this);
        task = database.taskDao().getUncompletedTask(uid,session.getUsername());
    }

    public void setTextTodoEditText(String todoEditText) {
        this.todoName.setText(todoEditText);
    }

    public void initPopup() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;

        getWindow().setLayout(width, height);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);
    }

    public void deleteOnClick(View view) {
        database.taskDao().delete(task);
        finish();
    }

    public void saveOnClick(View view) {
        Session session = new Session(this);
        database.taskDao().update(task.uid, todoName.getText().toString(), 0, session.getUsername());
        finish();
    }

    public void closeOnClick(View view) {
        this.finish();
    }
}