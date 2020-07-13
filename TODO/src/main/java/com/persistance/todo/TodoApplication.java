package com.persistance.todo;

import com.persistance.todo.services.TODOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

    private TODOService todoService;

    @Autowired
    TodoApplication (TODOService todoService){
        this.todoService = todoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        todoService.createTask("Go to shop", true, false);
        todoService.createTask("Get some sleep", false, false);
    }
}
