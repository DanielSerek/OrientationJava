package com.mysql.connection.services;

import com.mysql.connection.models.Task;
import com.mysql.connection.repositories.AssigneeRepository;
import com.mysql.connection.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TODOServiceImpl implements TODOService{

    TodoRepository todoRepository;
    AssigneeRepository assigneeRepository;

    @Autowired
    public TODOServiceImpl(TodoRepository todoRepository, AssigneeRepository assigneeRepository){
        this.todoRepository = todoRepository;
        this.assigneeRepository = assigneeRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return this.todoRepository.findAll();
    }

    @Override
    public void createTask(String title, String description, LocalDateTime dueDate, boolean urgent, boolean done) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(dueDate, formatter);
        Task task = new Task(title, description, dueDate, urgent, done);
        this.todoRepository.save(task);
    }

    @Override
    public List<Task> getAllActiveTasks() {
        if(this.todoRepository.findAll() == null || this.todoRepository.findAll().isEmpty()){
            return null;
        }
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
    public void updateTask(Long id, Long assigneeId, String title, String description, LocalDateTime dueDate, boolean urgent, boolean done) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(dueDate, formatter);
        Task task = new Task(title, description, dueDate, urgent, done);
        task.setId(id);
        task.setAssignee(this.assigneeRepository.getOne(assigneeId));
        this.todoRepository.save(task);
    }

    @Override
    public List<Task> searchedItems(String searchItem) {
        if(this.todoRepository.findAll() ==  null || this.todoRepository.findAll().isEmpty()){
            return null;
        }
        List<Task> searchedItems = this.todoRepository.findAll().stream()
                .filter(x -> x.getTitle().toLowerCase().contains(searchItem.toLowerCase()) || x.getAssignee().getName().toLowerCase().contains(searchItem.toLowerCase()))
                .collect(Collectors.toList());

        if (searchedItems.isEmpty()) {
            searchedItems = null;
        }

        return searchedItems;
    }

    @Override
    public List<Task> getTasksOfAssignee(long assigneeId) {
        if(this.todoRepository.findAll() == null || this.todoRepository.findAll().isEmpty()) {
            return null;
        }
        List<Task> tasksOfAssignee = new ArrayList<>();
        for (Task task : this.todoRepository.findAll()) {
            if(task.getAssignee() != null){
                if(task.getAssignee().getAssigneeId() == assigneeId){
                    tasksOfAssignee.add(task);
                }
            }
        }
        return tasksOfAssignee;
    }

    @Override
    public List<Task> searchItemsAccordingDates(LocalDateTime fromDate, LocalDateTime toDate) {
        if(this.todoRepository.findAll() == null || this.todoRepository.findAll().isEmpty()) {
            return null;
        }
        // Conversion from LocalDateTime to Date (needs to be done in stream as well)
        Date from = Date.from(fromDate.atZone(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(toDate.atZone(ZoneId.systemDefault()).toInstant());
        return this.todoRepository.findAll().stream()
                .filter(x -> Date.from(x.getDueDate().atZone(ZoneId.systemDefault()).toInstant()).after(from)
                        && Date.from(x.getDueDate().atZone(ZoneId.systemDefault()).toInstant()).before(to))
                .collect(Collectors.toList());
    }
}
