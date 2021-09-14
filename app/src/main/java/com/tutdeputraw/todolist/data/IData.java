package com.tutdeputraw.todolist.data;

import com.tutdeputraw.todolist.models.ToDo;

public interface IData {
    void delete(int index);

    void add(ToDo toDo);

    void update(ToDo toDo, int index);

    ToDo getObject(int index);
}
