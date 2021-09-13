package com.tutdeputraw.todolist.models;

public class ToDo {
    private static int id;
    private String name;
    private boolean isDone = false;

    public ToDo(String name) {
        this.name = name;
        this.id += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
