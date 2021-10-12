package com.tutdeputraw.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.database.account.DbHelper;
import com.tutdeputraw.todolist.database.account.Session;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPass;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session = new Session(this);

        onBind();
        checkState();
    }

    private void onBind() {
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
    }

    private void checkState() {
        if (session.loggedin()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    public void loginOnClick(View view) {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if (db.getUser(email, pass)) {
            session.setLoggedin(true);
            session.setUsername(email);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong email/password", Toast.LENGTH_SHORT).show();
        }
    }

    public void registerOnClick(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}