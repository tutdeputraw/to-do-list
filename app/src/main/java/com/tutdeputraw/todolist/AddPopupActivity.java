package com.tutdeputraw.todolist;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.database.local.TaskDatabase;

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

        getWindow().setLayout((int) (width), (int) (height));

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
        EditText editText = findViewById(R.id.edt_todo);
        String title = editText.getText().toString();

        TaskDatabase database = TaskDatabase.getInstance(getApplicationContext());

        database.taskDao().insertAll(title, 0);
        finish();
//        closeOnClick(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "AddPopupActivity onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "AddPopupActivity onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "AddPopupActivity onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "AddPopupActivity onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "AddPopupActivity onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "AddPopupActivity onDestroy invoked");
    }

//    public void addOnClick(View view) {
//        EditText editText = findViewById(R.id.edt_todo);
//        String title = editText.getText().toString();
//        ToDoList.todoList.add(new ToDo(title));
//        closeOnClick(view);
//    }
}