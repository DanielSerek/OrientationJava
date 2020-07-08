package com.greenfoxacademy;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public final class Task {
    private final long id;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime completedAt;

    public Task(String name) {
        this(0, name, LocalDateTime.now(Clock.systemUTC()), null);
    }

    public Task(long id, String name, LocalDateTime createdAt, LocalDateTime completedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completedAt != null;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public Duration completionTime() {
        if (!isCompleted()) {
            return null;
        }

        return Duration.between(createdAt, completedAt);
    }

    @Override
    public String toString() {
        String todo = String.format("%s - [%s] %s", id, getXIfCompleted(), name);

        if (isCompleted()) {
            todo += ", completed in " + toHumanReadable();
        }

        return todo;
    }

    private String toHumanReadable() {
        long duration = completionTime().getSeconds();
        long days = duration / (60 * 60 * 24);
        duration %= (60 * 60 * 24);
        long hours = duration / (60 * 60);
        duration %= (60 * 60);
        long minutes = duration / 60;
        return String.format(" %s days, %s hours, %s minutes", days, hours, minutes);
    }

    private String getXIfCompleted() {
        if (isCompleted()) {
            return "X";
        }

        return " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Task asCompleted() {
        return new Task(id, name, createdAt, LocalDateTime.now(Clock.systemUTC()));
    }

    public Task withId(long id) {
        return new Task(id, name, createdAt, completedAt);
    }

    public Task withName(String name) {
        return new Task(id, name, createdAt, completedAt);
    }
}
