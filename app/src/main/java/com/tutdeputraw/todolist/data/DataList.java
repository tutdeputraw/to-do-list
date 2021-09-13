package com.tutdeputraw.todolist.data;

import com.tutdeputraw.todolist.models.ToDo;

import java.util.ArrayList;

public class DataList {
    public static ArrayList<ToDo> toDoList = new ArrayList<>();

    public void delete(int index) {
        toDoList.remove(index);
    }

    public void add(ToDo toDo) {
        toDoList.add(toDo);
    }
}
