package com.dependencyinjection.foxclass.controllers;

import com.dependencyinjection.foxclass.ClassServicesIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClassController {
    @Autowired
    private ClassServicesIF service;

    @GetMapping("gfa")
    public String index(Model model){
        model.addAttribute("count", service.count());
        return "gfa";
    }

    @GetMapping("gfa/list")
    public String listAllStudents(Model model){
        model.addAttribute("students", service.findAll());
        return "list";
    }

    @GetMapping("gfa/add")
    public String addStudentPage(){
        return "add-student";
    }

    @PostMapping("add-student")
    public String addStudentToTheList(@ModelAttribute("student") String student){
        service.save(student);
        return "redirect:/gfa/list";
    }

    @GetMapping("gfa/check")
    public String checkStuden(){
        return "check-student";
    }

    @GetMapping("check-student")
    public String checkStudentInList(@RequestParam(value = "student", required = true) String student, Model model) {
        model.addAttribute("exist", service.checkStudent(student));
        return "result";
    }
}
