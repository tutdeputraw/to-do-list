package com.tutdeputraw.todolist.historyList.data;

import com.tutdeputraw.todolist.data.IData;
import com.tutdeputraw.todolist.models.ToDo;

import java.util.ArrayList;

public class HistoryList implements IData {
    public static ArrayList<ToDo> historyList = new ArrayList<>();

    @Override
    public void delete(int index) {
        historyList.remove(index);
    }

    @Override
    public void add(ToDo toDo) {
        historyList.add(toDo);
    }

    @Override
    public void update(ToDo toDo, int index) {
        historyList.get(index).setName(toDo.getName());
    }


    @Override
    public ToDo getObject(int index) {
        return historyList.get(index);
    }
}
