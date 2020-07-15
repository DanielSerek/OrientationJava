package com.sqldatabase.reddit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/reddit")
public class RedditController {

    @GetMapping("add-post")
    public String addNewTask(){
        return "add-post";
    }
}
