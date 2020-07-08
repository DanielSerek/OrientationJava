package com.dependencyinjections.utilities.controllers;

import com.dependencyinjections.utilities.UtilityServicesIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UtilitiesController {

    @Autowired
    private UtilityServicesIF service;

    @GetMapping("useful")
    public String useful(){
        return "useful";
    }

    @GetMapping("useful/colored")
    public String coloredPage(Model model){
        model.addAttribute("color", service.randomColor());
        return "blank";
    }

    @GetMapping("useful/email")
    public String email(@RequestParam (required = true) String email, Model model){
        String color;
        if(service.validateEmail(email)){
            color = "green";
        }
        else color = "red";

        model.addAttribute("color", color);
        model.addAttribute("email", email);
        return "email";
    }

    @GetMapping("useful/encoder")
    public String encoder(@RequestParam (required = true) String text, @RequestParam (required = true) int num, Model model){
        model.addAttribute("text", service.caesar(text, num));
        return "useful";
    }

    @GetMapping("useful/decoder")
    public String decoder(@RequestParam (required = true) String text, @RequestParam (required = true) int num, Model model){
        model.addAttribute("text", service.caesar(text, num));
        return "useful";
    }
}
