package com.foxclub.foxclub.controllers;

import com.foxclub.foxclub.models.Drink;
import com.foxclub.foxclub.models.Food;
import com.foxclub.foxclub.resources.FoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class MainController {

    @Autowired
    private FoxService service;
    private static String loggedName;

    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String getIndex(@RequestParam (required = true) String name, Model model){
        model.addAttribute("fox", service.getAFox(name));
        model.addAttribute("loggedName", loggedName);
        return "index";
    }

    @GetMapping("login")
    public String showLoginScreen(){
        return "login";
    }

    @PostMapping("login")
    public String userLogin(@RequestParam("name") String name, Model model){
        service.login(name);
        loggedName = name;
        model.addAttribute("loggedName", loggedName);
        return "redirect:/index?name=" + name;
    }

    @GetMapping("nutritionStore")
    public String showNutritionStore(@RequestParam("name") String name, Model model){
        model.addAttribute("loggedName", loggedName);
        model.addAttribute("drinks", Arrays.asList(Drink.values()));
        model.addAttribute("foods", Arrays.asList(Food.values()));
        model.addAttribute("defaultFood", service.getDefaultFood(name));
        model.addAttribute("defaultDrink", service.getDefaultDrink(name));
        return "nutrition-store";
    }

    @PostMapping("nutritionStore")
    public String changeNutrition(@RequestParam("name") String name, @ModelAttribute("food") String food, @ModelAttribute("drink") String drink, Model model){
        service.changeNutrition(name, food, drink);
        model.addAttribute("loggedName", loggedName);
        return "redirect:/index?name=" + name;
    }

    @GetMapping("trickCenter")
    public String showTrickCenter(@RequestParam("name") String name, Model model){
        model.addAttribute("loggedName", loggedName);
        model.addAttribute("allLearned", service.allSkillsLearned(name));
        model.addAttribute("tricks", service.getTricks(name));
        return "trick-center";
    }

    @PostMapping("trickCenter")
    public String LearnATrick(@RequestParam("name") String name, @ModelAttribute("trick") String trick, Model model){
        service.learnATrick(name, trick);
        model.addAttribute("loggedName", loggedName);
        return "redirect:/index?name=" + name;
    }

}
