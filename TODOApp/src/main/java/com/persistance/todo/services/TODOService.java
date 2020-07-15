package com.persistance.todo.services;

import com.persistance.todo.models.Task;

import java.util.List;

public interface TODOService {

    List<Task> getAllTasks();
    void createTask(String title, boolean urgent, boolean done);
}
