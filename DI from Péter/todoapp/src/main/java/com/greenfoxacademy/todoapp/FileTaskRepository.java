package com.greenfoxacademy.todoapp;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FileTaskRepository implements TaskRepository {
    private final TaskFileReader reader;
    private final TaskFileWriter writer;

    public FileTaskRepository(TaskFileReader reader, TaskFileWriter writer) {
//        reader = new TaskFileReader(filename);
//        writer = new TaskFileWriter(filename);

        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public List<Task> findAll() {
        return reader.loadTasks();
    }

    @Override
    public void add(Task task) {
        List<Task> tasks = findAll();

        long nextId = findNextId(tasks);

        tasks.add(task.withId(nextId));

        save(tasks);
    }

    @Override
    public Task findById(long id) {
        Optional<Task> task = findAll().stream()
                .filter(t -> t.getId() == id)
                .findFirst();

        if (!task.isPresent()) {
            return null;
        }

        return task.get();
    }

    @Override
    public void save(Task task) {
        List<Task> tasks = findAll();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                tasks.remove(i);
                tasks.add(i, task);
            }
        }

        save(tasks);
    }

    @Override
    public void remove(Task task) {
        List<Task> tasks = findAll();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                tasks.remove(i);
                i--;
            }
        }

        save(tasks);
    }

    private long findNextId(List<Task> tasks) {
        long largestId = tasks.stream()
                .mapToLong(t -> t.getId())
                .max()
                .getAsLong();

        return largestId + 1;
    }

    private void save(List<Task> tasks) {
        writer.writeTasks(tasks);
    }
}
