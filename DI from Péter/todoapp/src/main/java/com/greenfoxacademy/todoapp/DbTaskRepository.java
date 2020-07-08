package com.greenfoxacademy.todoapp;

import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class DbTaskRepository implements TaskRepository {
  @Override
  public List<Task> findAll() {
    // SELECT * FROM Tasks;
    return null;
  }

  @Override
  public void add(Task task) {

  }

  @Override
  public Task findById(long id) {
    return null;
  }

  @Override
  public void save(Task task) {

  }

  @Override
  public void remove(Task task) {

  }
}
