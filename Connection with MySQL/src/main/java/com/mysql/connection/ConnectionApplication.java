package com.mysql.connection;

import com.mysql.connection.services.TODOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectionApplication implements CommandLineRunner {

    private TODOService todoService;

    @Autowired
    ConnectionApplication (TODOService todoService){
        this.todoService = todoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConnectionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        todoService.createTask("Go to shop", true, false);
        todoService.createTask("Get some sleep", false, false);
    }
}
