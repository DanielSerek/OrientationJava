package com.foxclub.controllers;

import com.foxclub.models.User;
import com.foxclub.services.FoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private FoxService service;
    boolean userExists = false;
    boolean matchingPasswords = true;
    long loggedId;

    @GetMapping("register")
    public String registerPage(Model model) {
        if(userExists){
            model.addAttribute("userExists", true);
        }
        if(!matchingPasswords){
            model.addAttribute("matchingPasswords", false);
        }
        return "register";
    }

    @PostMapping("register")
    public String registerUser(String userName, String password, String passwordCheck, Model model) {
        loggedId = service.getUserId(userName, password);
        if(loggedId!=-1){
            return "redirect:/register";
        }
        if(!password.equals(passwordCheck)){
            matchingPasswords = false;
            return "redirect:/register";
        }
        service.addANewUser(new User(userName, password));
        userExists = true;
        return "redirect:/login";
    }

    @GetMapping("login")
    public String showLoginScreen(Model model) {
        model.addAttribute("userExists", userExists);
        return "login";
    }

    @PostMapping("login")
    public String userLogin(String userName, String password, Model model) {
        loggedId = service.getUserId(userName, password);
        if (loggedId == -1) {
            return "login";
        }
        return "redirect:/index?loggedId=" + loggedId;
    }
}
