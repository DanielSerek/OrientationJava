package com.foxclub.controllers;

import com.foxclub.services.FoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private FoxService service;
    private static String loggedName;
    boolean userExists = true;

    @GetMapping("login")
    public String showLoginScreen(Model model){
        model.addAttribute("userExists", userExists);
        return "login";
    }

    @PostMapping("login")
    public String userLogin(@RequestParam(value = "name") String name, Model model){
        userExists = service.checkUserExists(name);
        if(!userExists){
            return "login";
        }
        loggedName = name;
        model.addAttribute("userExists", userExists);
        model.addAttribute("loggedName", loggedName);
        return "redirect:/index?name=" + name;
    }
}
