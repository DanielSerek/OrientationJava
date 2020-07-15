package com.mysql.connection.services;

import com.mysql.connection.models.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TODOService {

    List<Task> getAllTasks();

    void createTask(String title, String description, String dueDate, boolean urgent, boolean done);

    List<Task> getAllActiveTasks();

    void deleteTask(long id);

    Task getTask(long id);

    void updateTask(Long id, Long assigneeId, String title, String description, String dueDate, boolean urgent, boolean done);

    List<Task> searchedItems(String searchItem);

    List<Task> getTasksOfAssignee(long assigneeId);

    List<Task> searchItemsAccordingDates(LocalDateTime fromDate, LocalDateTime toDate);
}
