package com.tutdeputraw.todolist;

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

public class AddPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_popup);

        initPopup();
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

    public void closeOnClick(View view) {
        this.finish();
    }

    public void addOnClick(View view) {
        Session session = new Session(this);
        EditText editText = findViewById(R.id.edt_todo);
        String title = editText.getText().toString();

        TaskDatabase database = TaskDatabase.getInstance(getApplicationContext());

        database.taskDao().insertAll(title, 0, session.getUsername());
        finish();
//        closeOnClick(view);
    }
}