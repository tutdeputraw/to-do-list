package com.tutdeputraw.todolist.toDoList.data;

import com.tutdeputraw.todolist.data.IData;
import com.tutdeputraw.todolist.models.ToDo;

import java.util.ArrayList;

public class ToDoList implements IData {
    public static ArrayList<ToDo> todoList = new ArrayList<>();

    @Override
    public void delete(int index) {
        todoList.remove(index);
    }

    @Override
    public void add(ToDo toDo) {
        todoList.add(toDo);
    }

    @Override
    public void update(ToDo toDo, int index) {
        todoList.get(index).setName(toDo.getName());
    }

    @Override
    public ToDo getObject(int index) {
        return todoList.get(index);
    }
}
