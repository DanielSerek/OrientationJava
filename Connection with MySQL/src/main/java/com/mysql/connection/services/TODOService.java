package com.mysql.connection.services;

import com.mysql.connection.models.Task;

import java.util.List;

public interface TODOService {

    List<Task> getAllTasks();

    void createTask(String title, boolean urgent, boolean done);

    List<Task> getAllActiveTasks();

    void deleteTask(long id);

    Task getTask(long id);

    void updateTask(Long id, String title, boolean urgent, boolean done);

    List<Task> searchedItems(String searchItem);
}
