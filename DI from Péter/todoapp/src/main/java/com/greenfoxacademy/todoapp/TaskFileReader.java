package com.greenfoxacademy.todoapp;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Component
public class TaskFileReader {
    private final TaskFileConfiguration fileConfiguration;

    public TaskFileReader(TaskFileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }

    public List<Task> loadTasks() {

        try {
            Path path = Paths.get(fileConfiguration.filename);
//            Files.createFile(path);
            return Files.lines(path)
                    .map(line -> line.trim().split(";"))
                    .map(parts -> new Task(Long.parseLong(parts[0]), parts[1], parseDateTime(parts[2]), parseDateTime(parts[3])))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emptyList();
    }

    private LocalDateTime parseDateTime(String dateString) {
        if (dateString.equals("null")) {
            return null;
        }

        try {
            return LocalDateTime.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }
}
