package com.mysql.connection.controllers;

import com.mysql.connection.models.Assignee;
import com.mysql.connection.services.AssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("assignees-list")
public class AssigneeController {

    private AssigneeService assigneeService;

    @Autowired
    public AssigneeController (AssigneeService assigneeService){
        this.assigneeService = assigneeService;
    }

    @GetMapping("assignees-list")
    public String Asigneeslist(Model model){
        model.addAttribute("assignees", this.assigneeService.getAllAssignees());
        return "assignees-list";
    }

    @GetMapping("delete-assignee/{id}")
    public String deleteTask(@PathVariable long id, Model model){
        assigneeService.deleteAssignee(id);
        return "redirect:/assignees-list";
    }

    @GetMapping("{id}/editAssignee")
    public String editATask(@PathVariable long id, Model model){
        model.addAttribute("assignee", assigneeService.getAssignee(id));
        return "edit-assignee";
    }

    @PostMapping("edit-assignee")
    public String submitEditedTask(@RequestParam(value = "id") long id, @ModelAttribute("assignee") Assignee assignee){
        assigneeService.updateAssignee(id, assignee.getName(), assignee.getEmail());
        return "redirect:/list";
    }
}
