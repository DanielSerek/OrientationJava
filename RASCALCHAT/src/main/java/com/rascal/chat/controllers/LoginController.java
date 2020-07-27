package com.rascal.chat.controllers;

import com.rascal.chat.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginScreen(Model model) {
        model.addAttribute("userExists");
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(String login, String password) {
        loginService.logUser(login, password);
        return "index";
    }



}
