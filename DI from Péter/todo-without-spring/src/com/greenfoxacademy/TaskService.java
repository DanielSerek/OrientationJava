package com.greenfoxacademy;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        // without DI, the dependency is created inside of the class
//        this.taskRepository = new FileTaskRepository("resources/todo.txt");

        this.taskRepository = taskRepository;
    }

    public void removeTask(long id) {
        Task task = taskRepository.findById(id);

        taskRepository.remove(task);
    }

    public void completeTask(long id) {
        Task task = taskRepository.findById(id);

        if (task == null) {
            throw new IndexOutOfBoundsException();
        }

        taskRepository.save(task.asCompleted());
    }

    public void addTask(String taskName) {
        taskRepository.add(new Task(taskName));
    }

    public void listAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        if (tasks.isEmpty()) {
            System.out.println("No todos for today! :)");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void updateName(long id, String name) throws IndexOutOfBoundsException {
        Task task = taskRepository.findById(id);

        if (task == null) {
            throw new IndexOutOfBoundsException();
        }

        task = task.withName(name);

        taskRepository.save(task);
    }
}
