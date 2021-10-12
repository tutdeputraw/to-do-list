package com.tutdeputraw.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tutdeputraw.todolist.database.account.Session;

public class MainActivity extends AppCompatActivity {
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkState();
    }

    private void checkState() {
        session = new Session(this);
        if (!session.loggedin()) {
            logout();
        } else {
            goToHome();
        }
    }

    private void goToHome() {
        finish();
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void logoutOnClick(View view) {
        logout();
    }
}