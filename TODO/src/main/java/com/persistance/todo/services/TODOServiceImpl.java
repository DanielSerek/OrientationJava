package com.persistance.todo.services;

import com.persistance.todo.models.Task;
import com.persistance.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TODOServiceImpl implements TODOService{

    TodoRepository todoRepository;

    @Autowired
    public TODOServiceImpl(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return this.todoRepository.findAll();
    }

    @Override
    public void createTask(String content, boolean urgent, boolean done) {
        Task task = new Task(content, urgent, done);
        this.todoRepository.save(task);
    }
}