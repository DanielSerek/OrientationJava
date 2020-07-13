package com.mysql.connection.services;

import com.mysql.connection.models.Task;

import java.util.List;

public interface TODOService {

    List<Task> getAllTasks();
    void createTask(String title, boolean urgent, boolean done);
}
