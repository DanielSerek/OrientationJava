package com.foxclub.controllers;

import com.foxclub.models.Drink;
import com.foxclub.models.Food;
import com.foxclub.services.FoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
public class FoxController {

    @Autowired
    private FoxService service;
    private static String loggedName;

    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String getIndex(@RequestParam(value = "name", required = false, defaultValue = "none") String name, Model model) {
        if (service.checkUserExists(name)) {
            loggedName = name;
        } else return "login";
//        if(!name.equals(loggedName) || name.equals("none")) {
//            return "login";
//        }
        model.addAttribute("fox", service.getAFox(name));
        model.addAttribute("loggedName", loggedName);
        model.addAttribute("logs", service.getLogs(name).stream().sorted(Comparator.reverseOrder()).limit(5).collect(Collectors.toList()));
        return "index";
    }

    @GetMapping("nutritionStore")
    public String showNutritionStore(@RequestParam(value = "name", required = false, defaultValue = "none") String name, Model model) {
        if (!name.equals(loggedName) || name.equals("none")) {
            return "login";
        }
        model.addAttribute("loggedName", loggedName);
        model.addAttribute("drinks", Arrays.asList(Drink.values()));
        model.addAttribute("foods", Arrays.asList(Food.values()));
        model.addAttribute("defaultFood", service.getDefaultFood(name));
        model.addAttribute("defaultDrink", service.getDefaultDrink(name));
        return "nutrition-store";
    }

    @PostMapping("nutritionStore")
    public String changeNutrition(@RequestParam String name, @ModelAttribute("food") String food, @ModelAttribute("drink") String drink, Model model) {
        service.changeNutrition(name, food, drink);
        model.addAttribute("loggedName", loggedName);
        return "redirect:/index?name=" + name;
    }

    @GetMapping("trickCenter")
    public String showTrickCenter(@RequestParam(value = "name", required = false, defaultValue = "none") String name, Model model) {
        if (!name.equals(loggedName) || name.equals("none")) {
            return "login";
        }
        model.addAttribute("loggedName", loggedName);
        model.addAttribute("allLearned", service.allSkillsLearned(name));
        model.addAttribute("tricks", service.getTricks(name));
        return "trick-center";
    }

    @PostMapping("trickCenter")
    public String LearnATrick(@RequestParam String name, @ModelAttribute("trick") String trick, Model model) {
        service.learnATrick(name, trick);
        model.addAttribute("loggedName", loggedName);
        return "redirect:/index?name=" + name;
    }

    @GetMapping("actionHistory")
    public String showActionHistory(@RequestParam(value = "name", required = false, defaultValue = "none") String name, Model model) {
        if (!name.equals(loggedName) || name.equals("none")) {
            return "login";
        }
        model.addAttribute("loggedName", loggedName);
        model.addAttribute("logs", service.getLogs(name));
        return "action-history";
    }
}
