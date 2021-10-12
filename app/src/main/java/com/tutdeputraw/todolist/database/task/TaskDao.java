package com.tutdeputraw.todolist.database.task;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.tutdeputraw.todolist.database.task.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task where isDone = 0 AND user=:user")
    List<Task> getUncompletedTask(String user);

    @Query("SELECT * FROM task where isDone = 1 AND user=:user")
    List<Task> getCompletedTask(String user);

    @Query("INSERT INTO task (name, isDone, user) VALUES(:name, :isDone, :user)")
    void insertAll(String name, int isDone, String user);

    @Query("UPDATE task SET name=:name, isDone=:isDone, user=:user WHERE uid=:uid")
    void update(int uid, String name, int isDone, String user);

    @Query("SELECT * FROM task WHERE uid=:uid AND isDone = 0 AND user=:user")
    Task getUncompletedTask(int uid, String user);

    @Delete
    void delete(Task user);
}
