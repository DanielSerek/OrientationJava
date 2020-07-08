package com.greenfoxacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class TaskFileReader {
    private final String filename;

    public TaskFileReader(String filename) {
        this.filename = filename;
    }

    public List<Task> loadTasks() {
        try {
            return Files.lines(Paths.get(filename))
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
