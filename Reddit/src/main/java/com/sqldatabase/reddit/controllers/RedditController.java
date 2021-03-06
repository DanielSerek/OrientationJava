package com.sqldatabase.reddit.controllers;

import com.sqldatabase.reddit.models.Post;
import com.sqldatabase.reddit.services.RedditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("/reddit")
public class RedditController {

    private RedditService redditService;
    private Integer currentPage;

    @Autowired
    public RedditController(RedditService redditService) {
        this.redditService = redditService;
    }

    @GetMapping("add-post")
    public String addNewPost(@RequestParam(value = "loggedId") long loggedId, Model model){
        model.addAttribute("userId", loggedId);
        return "add-post";
    }
    @PostMapping("add-post")
    public String submitNewPost(@RequestParam(value = "loggedId") long loggedId, @ModelAttribute("post") Post post){
        this.redditService.createPost(loggedId, post.getTitle(),post.getUrl());
        return "redirect:/index?loggedId=" + loggedId;
    }

    @RequestMapping(value = {"/","index"})
    public String listPosts(@RequestParam(value = "loggedId") long loggedId, @RequestParam(required = false, value = "page") Integer page,  Model model){
        if(page == null){
            page = 1;
        }
        currentPage = page;
        model.addAttribute("user",this.redditService.getUserName(loggedId));
        model.addAttribute("loggedId", loggedId);
        model.addAttribute("posts", this.redditService.getAllPosts(page));
        model.addAttribute("totalpages", this.redditService.getPages().size());
        model.addAttribute("pages", this.redditService.getPages());
        model.addAttribute("currentPage", currentPage);
        return "list";
    }

    @RequestMapping(value = {"add"})
    public String addCounts(@RequestParam (value = "postId") long postId, @RequestParam(value = "loggedId") long loggedId){
        this.redditService.countChange(loggedId, postId, "add");
        return "redirect:/index?loggedId=" + loggedId;
    }

    @RequestMapping(value = {"deduct"})
    public String deductCounts(@RequestParam (value = "postId") long postId, @RequestParam(value = "loggedId") long loggedId){
        this.redditService.countChange(loggedId, postId, "deduct");
        return "redirect:/index?loggedId=" + loggedId;
    }
}
