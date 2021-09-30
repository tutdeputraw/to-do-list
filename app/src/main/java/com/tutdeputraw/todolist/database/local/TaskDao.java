package com.tutdeputraw.todolist.database.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.tutdeputraw.todolist.database.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task where isDone = 0")
    List<Task> getUncompletedTask();

    @Query("SELECT * FROM task where isDone = 1")
    List<Task> getCompletedTask();

    @Query("INSERT INTO task (name, isDone) VALUES(:name, :isDone)")
    void insertAll(String name, int isDone);

    @Query("UPDATE task SET name=:name, isDone=:isDone WHERE uid=:uid")
    void update(int uid, String name, int isDone);

    @Query("SELECT * FROM task WHERE uid=:uid AND isDone = 1")
    Task getCompletedTask(int uid);

    @Query("SELECT * FROM task WHERE uid=:uid AND isDone = 0")
    Task getUncompletedTask(int uid);

    @Delete
    void delete(Task user);
}
