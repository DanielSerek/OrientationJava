package com.rascal.chat.controllers;

import com.rascal.chat.models.UserLoginDTO;
import com.rascal.chat.models.UserUpdateDTO;
import com.rascal.chat.services.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginScreen() {
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(UserLoginDTO userLoginDTO) {
        System.setProperty("APIKEY", loginService.logUser(userLoginDTO));
        System.out.println(System.getProperty("APIKEY"));
        return "redirect:/api/get-messages";
    }

    @GetMapping("/register")
    public String showRegistrationScreen() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserLoginDTO userLoginDTO) {
        loginService.registerUser(userLoginDTO);
        return "index";
    }

    @GetMapping("/user/update")
    public String showUpdateScreen() {
        return "update-url";
    }

    @PostMapping("/user/update")
    public String updateUser(UserUpdateDTO userUpdateDTO) {
        loginService.updateUrl(System.getProperty("APIKEY"), userUpdateDTO.getUsername(), userUpdateDTO.getAvatarurl());
        return "index";
    }

    @GetMapping("/user/logout")
    public ResponseEntity<?> logOut(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("apiKey", System.getProperty("APIKEY"));
        return ResponseEntity.ok().headers(responseHeaders).body(null);
    }


}
