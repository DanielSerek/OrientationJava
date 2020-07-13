package com.greenfoxacademy.todoapp;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    void removeTask(long id);

    void completeTask(long id);

    void addTask(String taskName);

    List<Task> listAllTasks();

    void updateName(long id, String name);
}

