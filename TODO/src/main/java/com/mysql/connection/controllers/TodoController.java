package com.mysql.connection.controllers;

import com.mysql.connection.models.Task;
import com.mysql.connection.services.AssigneeService;
import com.mysql.connection.services.TODOService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Controller("/todo")
public class TodoController {

    private TODOService todoService;
    private AssigneeService assigneeService;

    private List<Task> currentlyDisplayedItems;

    @Autowired
    TodoController (TODOService todoService, AssigneeService assigneeService){
        this.todoService = todoService;
        this.assigneeService = assigneeService;
    }

    @RequestMapping(value = {"/","/list"})
    public String list(@RequestParam(required = false) boolean isActive, Model model){
        model.addAttribute("isActive", isActive);
        List<Task> tasks = null;
        try{
            if(isActive){
                tasks = this.todoService.getAllActiveTasks();
            }
            else{
                tasks = this.todoService.getAllTasks();
            }
        }
        catch (Exception e){
            e.getMessage();
        }
        model.addAttribute("todos", tasks);
        return "todolist";
    }

    @GetMapping("assignees-list/{assigneeId}")
    public String ListTasksOfAssignee(@PathVariable long assigneeId, Model model){
        model.addAttribute("todos", this.todoService.getTasksOfAssignee(assigneeId));
        return "todolist";
    }

    @GetMapping("search")
    public String displaySearchedItems(@RequestParam String searchItem, Model model) {
        currentlyDisplayedItems = this.todoService.searchedItems(searchItem);
        if(currentlyDisplayedItems == null || !currentlyDisplayedItems.isEmpty()){
            model.addAttribute("todos", currentlyDisplayedItems);
        }
        return "todolist";
    }

    @GetMapping("searchDate")
    public String filterItemsAccordingDates(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate, @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,  Model model) {
        currentlyDisplayedItems = this.todoService.searchItemsAccordingDates(fromDate, toDate);
        if(currentlyDisplayedItems == null || !currentlyDisplayedItems.isEmpty()){
            model.addAttribute("todos", currentlyDisplayedItems);
        }
        return "todolist";
    }

    @GetMapping("add-task")
    public String addNewTask(){
        return "add-task";
    }

    @PostMapping("add-task")
    public String addtask(@RequestParam("toDoBefore") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDoBefore, @ModelAttribute("task") Task task){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm:ss");
//        String dueDate =  toDoBefore.format(formatter);
        todoService.createTask(task.getTitle(), task.getDescription(), toDoBefore, task.isUrgent(), false);
        return "redirect:/list";
    }

    @GetMapping("delete-task/{id}")
    public String deleteTask(@PathVariable long id, Model model){
        todoService.deleteTask(id);
        return "redirect:/list";
    }

    @GetMapping("{id}/edit")
    public String editATask(@PathVariable long id, Model model){
        Object unproxiedEntity = Hibernate.unproxy(todoService.getTask(id));
        model.addAttribute("task", (Task)unproxiedEntity);
        model.addAttribute("assignees", assigneeService.getAllAssignees());
        return "edit-task";
    }

    @PostMapping("edit-task")
    public String submitEditedTask(@RequestParam (value = "id") long id, @ModelAttribute("task") Task task, long assigneeId) throws ParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm:ss");
//        String dueDate =  toDoBefore.format(formatter);
//        @RequestParam("toDoBefore") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDoBefore,
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        todoService.updateTask(id, assigneeId, task.getTitle(), task.getDescription(), task.getDueDate(), todoService.getTask(id).getOriginalTimeStampDate(), task.isUrgent(), task.isDone());
        return "redirect:/list";
    }
}
