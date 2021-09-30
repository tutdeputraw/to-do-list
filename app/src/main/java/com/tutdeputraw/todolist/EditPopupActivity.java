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

import com.tutdeputraw.todolist.database.local.TaskDatabase;
import com.tutdeputraw.todolist.database.model.Task;

public class EditPopupActivity extends AppCompatActivity {
    private EditText todoEditText;
    private TaskDatabase database;
    private int uid = 0;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_popup);

        todoEditText = findViewById(R.id.edt_todo);

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
        task = database.taskDao().getUncompletedTask(uid);
    }

    public void setTextTodoEditText(String todoEditText) {
        this.todoEditText.setText(todoEditText);
    }

    public void initPopup() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;

        getWindow().setLayout((int) (width), (int) (height));

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
        database.taskDao().update(task.uid, task.name, 0);
        finish();
    }

    public void closeOnClick(View view) {
        this.finish();
    }
}