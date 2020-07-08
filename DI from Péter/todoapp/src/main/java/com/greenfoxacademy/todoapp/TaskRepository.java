package com.greenfoxacademy.todoapp;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();

    void add(Task task);

    Task findById(long id);

    void save(Task task);

    void remove(Task task);
}
