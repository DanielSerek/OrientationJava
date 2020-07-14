package com.mysql.connection.services;

import com.mysql.connection.models.Task;
import com.mysql.connection.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void createTask(String title, boolean urgent, boolean done) {
        Task task = new Task(title, urgent, done);
        this.todoRepository.save(task);
    }

    @Override
    public List<Task> getAllActiveTasks() {
        return this.todoRepository.findAll().stream().filter(x -> x.isDone()==false).collect(Collectors.toList());
    }

    @Override
    public void deleteTask(long id) {
        this.todoRepository.deleteById(id);
    }

    @Override
    public Task getTask(long id) {
        return this.todoRepository.getOne(id);
    }

    @Override
    public void updateTask(Long id, String title, boolean urgent, boolean done) {
        Task task = new Task(title, urgent, done);
        task.setId(id);
        this.todoRepository.save(task);
    }

    @Override
    public List<Task> searchedItems(String searchItem) {
        List<Task> searchedItems = this.todoRepository.findAll().stream()
                .filter(x -> x.getTitle().toLowerCase().contains(searchItem.toLowerCase()))
                .collect(Collectors.toList());

        if (searchedItems.isEmpty()) searchedItems.add(new Task("No item was found.",false, false));

        return searchedItems;
    }
}
