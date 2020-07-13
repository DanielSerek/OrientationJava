package com.greenfoxacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskFileWriter {
    private final String filename;

    public TaskFileWriter(String filename) {
        this.filename = filename;
    }

    public void writeTasks(List<Task> tasks) {
        try {
            Files.write(Paths.get(filename), serializeTasks(tasks));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> serializeTasks(List<Task> tasks) {
        return tasks.stream()
                .map(task -> String.format("%s;%s;%s;%s", task.getId(), task.getName(), task.getCreatedAt(), task.getCompletedAt()))
                .collect(Collectors.toList());
    }
}
