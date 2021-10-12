package com.tutdeputraw.todolist.database.task.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey
    public int uid;

    public String name;

    public int isDone;

    public String user;
}
