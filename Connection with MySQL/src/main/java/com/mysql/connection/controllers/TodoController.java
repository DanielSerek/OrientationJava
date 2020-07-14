package com.mysql.connection.controllers;

import com.mysql.connection.models.Task;
import com.mysql.connection.services.TODOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/todo")
public class TodoController {

    private TODOService todoService;
    private List<Task> currentlyDisplayedItems;

    @Autowired
    TodoController (TODOService todoService){
        this.todoService = todoService;
    }

    @RequestMapping(value = {"/","/list"})
    public String list(@RequestParam(required = false) boolean isActive, Model model){
        model.addAttribute("isActive", isActive);
        if(isActive){
            model.addAttribute("todos", this.todoService.getAllActiveTasks());
        }
        else{
            model.addAttribute("todos", this.todoService.getAllTasks());
        }
        return "todolist";
    }

    @GetMapping("search")
    public String displaySearchedItems(@RequestParam String searchItem, Model model) {
        currentlyDisplayedItems = this.todoService.searchedItems(searchItem);
        model.addAttribute("todos", currentlyDisplayedItems);
        return "todolist";
    }

    @GetMapping("add-task")
    public String addNewTask(){
        return "add-task";
    }

    @PostMapping("add-task")
    public String addtask(@ModelAttribute("task") Task task){
        todoService.createTask(task.getTitle(), task.isUrgent(), false);
        return "redirect:/list";
    }

    @GetMapping("delete-task/{id}")
    public String deleteTask(@PathVariable long id, Model model){
        todoService.deleteTask(id);
        return "redirect:/list";
    }

    @GetMapping("{id}/edit")
    public String editATask(@PathVariable long id, Model model){
        model.addAttribute("task", todoService.getTask(id));
        return "edit-task";
    }

    @PostMapping("edit-task")
    public String submitEditedTask(@RequestParam (value = "id") long id, @ModelAttribute("task") Task task){
        todoService.updateTask(id,task.getTitle(), task.isUrgent(), task.isDone());
        return "redirect:/list";
    }
}
