package com.greenfoxacademy.todoapp;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@Component - Controller is a Component
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/todos")
    public List<Task> getAll() {
        return taskService.listAllTasks();
    }
}
