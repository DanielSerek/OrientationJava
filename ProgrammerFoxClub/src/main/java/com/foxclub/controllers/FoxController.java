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
    private Long loggedId;

    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String getIndex(@RequestParam (value = "loggedId") long loggedId, Model model) {
        this.loggedId = loggedId;
//        if (service.checkUserExists(name)) {
//            loggedName = name;
//        } else return "login";
//        if(!name.equals(loggedName) || name.equals("none")) {
//            return "login";
//        }
        model.addAttribute("fox", service.getAFox(loggedId));
        model.addAttribute("loggedId", service.getAFox(loggedId).getUser().getId());
        model.addAttribute("logs", service.getAFox(loggedId).getLogs().stream().sorted(Comparator.reverseOrder()).limit(5).collect(Collectors.toList()));
        return "index";
    }

    @GetMapping("nutritionStore")
    public String showNutritionStore(@RequestParam (value = "loggedId") long loggedId, Model model) {
//        if (!name.equals(loggedName) || name.equals("none")) {
//            return "login";
//        }
        model.addAttribute("loggedId", service.getAFox(loggedId).getUser().getId());
        model.addAttribute("drinks", Arrays.asList(Drink.values()));
        model.addAttribute("foods", Arrays.asList(Food.values()));
        model.addAttribute("defaultFood", service.getDefaultFood(service.getAFox(loggedId).getUser().getUserName()));
        model.addAttribute("defaultDrink", service.getDefaultDrink(service.getAFox(loggedId).getUser().getUserName()));
        return "nutrition-store";
    }

    @PostMapping("nutritionStore")
    public String changeNutrition(@RequestParam (value = "loggedId") long loggedId, @ModelAttribute("food") String food, @ModelAttribute("drink") String drink, Model model) {
        service.changeNutrition(service.getAFox(loggedId).getUser().getUserName(), food, drink);
        model.addAttribute("loggedId", service.getAFox(loggedId).getUser().getId());
        return "redirect:/index?loggedId=" + loggedId;
    }

    @GetMapping("trickCenter")
    public String showTrickCenter(long loggedId, Model model) {
//        if (!name.equals(loggedName) || name.equals("none")) {
//            return "login";
//        }
        model.addAttribute("loggedId", service.getAFox(loggedId).getUser().getId());
        model.addAttribute("allLearned", service.allSkillsLearned(service.getAFox(loggedId).getUser().getUserName()));
        model.addAttribute("tricks", service.getTricks(service.getAFox(loggedId).getUser().getUserName()));
        return "trick-center";
    }

    @PostMapping("trickCenter")
    public String LearnATrick(@RequestParam (value = "loggedId") long loggedId, @ModelAttribute("trick") String trick, Model model) {
        service.learnATrick(service.getAFox(loggedId).getUser().getUserName(), trick);
        model.addAttribute("loggedId", service.getAFox(loggedId).getUser().getId());
        return "redirect:/index?loggedId=" + loggedId;
    }

    @GetMapping("actionHistory")
    public String showActionHistory(@RequestParam (value = "loggedId") long loggedId, Model model) {
//        if (!name.equals(loggedName) || name.equals("none")) {
//            return "login";
//        }
        model.addAttribute("loggedId", service.getAFox(loggedId).getUser().getId());
        model.addAttribute("logs", service.getLogs(service.getAFox(loggedId).getUser().getUserName()));
        return "action-history";
    }
}
