package com.mysql.connection.controllers;

import com.mysql.connection.services.TODOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/todo")
public class TodoController {

    private TODOService todoService;

    @Autowired
    TodoController (TODOService todoService){
        this.todoService = todoService;
    }

    @RequestMapping(value = {"/","/hello"})
    public String list(Model model){
        model.addAttribute("todos", this.todoService.getAllTasks());
        return "todolist";
    }
}
