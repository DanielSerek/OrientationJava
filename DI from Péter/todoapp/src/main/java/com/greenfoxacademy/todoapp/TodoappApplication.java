package com.greenfoxacademy.todoapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan
public class TodoappApplication implements CommandLineRunner {

  private final TodoApp app;

  public TodoappApplication(TodoApp app) {
    this.app = app;
  }

  public static void main(String[] args) {
    SpringApplication.run(TodoappApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {
//    TaskService taskService = createDependencies();

//    TodoApp app = new TodoApp(taskService);
    app.run(args);
  }

  // Spring creates all this automatically
//  private static TaskService createDependencies() {
//    String filename = "todo.txt";
//
//    TaskFileReader reader = new TaskFileReader(filename);
//    TaskFileWriter writer = new TaskFileWriter(filename);
//    TaskRepository taskRepository = new FileTaskRepository(reader, writer);
//    return new TaskService(taskRepository);
//  }
}
