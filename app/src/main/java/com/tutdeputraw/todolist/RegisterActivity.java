package com.tutdeputraw.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.account.DbHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPass;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DbHelper(this);
        onBind();
    }

    private void onBind() {
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void registerOnClick(View view) {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        if (email.isEmpty() && pass.isEmpty()) {
            displayToast("Username/password field empty");
        } else {
            db.addUser(email, pass);
            displayToast("User registered");
            finish();
        }
    }

    public void loginOnClick(View view) {
        finish();
    }
}