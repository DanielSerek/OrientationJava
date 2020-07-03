package com.myshop.myshop.controllers;

import com.myshop.myshop.models.ShopItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShopController {
    List<ShopItem> items;

    public ShopController(){
        items = new ArrayList<>();
        items.add(new ShopItem("Running shoes","Nike running shoes for every day sport", 1000.0f, 5));
        items.add(new ShopItem("Printer","Some HP printer that will print pages", 3000.0f, 2));
        items.add(new ShopItem("Coca cola","0.5l standard coke", 25.0f, 0));
        items.add(new ShopItem("Wokin","Chicken with fried rice and WOKIN sauce", 119.0f, 100));
        items.add(new ShopItem("T-shirt","Blue with a corgi on a bike", 300.0f, 1));
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String getIndex(){
        return "redirect:only-available";
    }

    @GetMapping("list-items")
    public String displayAllItems(Model model){
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping("only-available")
    public String onlyAvailableItems(Model model){
        List<ShopItem> availableItems = items.stream()
                .filter(x -> x.getQuantityOfStock() > 0)
                .collect(Collectors.toList());
        model.addAttribute("items", availableItems);
        return "index";
    }

    @GetMapping("cheapest-first")
    public String cheapestFirst(Model model){
        List<ShopItem> cheapestAscending = items.stream()
                .sorted(Comparator.comparing(ShopItem::getPrice))
                .collect(Collectors.toList());
        model.addAttribute("items", cheapestAscending);
        return "index";
    }

    @GetMapping("contains-nike")
    public String containsNike(Model model){
        List<ShopItem> cheapestAscending = items.stream()
                .filter(x -> x.getDescription().toLowerCase().contains("nike") || x.getName().toLowerCase().contains("nike"))
                .collect(Collectors.toList());
        model.addAttribute("items", cheapestAscending);
        return "index";
    }

    @GetMapping("average-stock")
    public String averageStock(Model model){
        IntSummaryStatistics stats = items.stream()
                .mapToInt((x) -> x.getQuantityOfStock())
                .summaryStatistics();
        model.addAttribute("average", stats.getAverage());
        return "template";
    }

    @GetMapping("most-expensive")
    public String mostExpensive(Model model){
        ShopItem theMostExpensive = items.stream()
                .sorted(Comparator.comparing(ShopItem::getPrice).reversed())
                .findFirst()
                .orElse(null);
        model.addAttribute("expensiest", theMostExpensive.getName());
        return "template";
    }

    @GetMapping("search")
    public String displaySearchedItems(@RequestParam String searchItem,Model model){

        List<ShopItem> searchedItems = items.stream()
                .filter(x -> x.getName().toLowerCase().contains(searchItem.toLowerCase()) || x.getDescription().toLowerCase().contains(searchItem.toLowerCase()))
                .collect(Collectors.toList());

        if(searchedItems.isEmpty()) searchedItems.add(new ShopItem("","No item was found.", 0, 0));

        model.addAttribute("items", searchedItems);
        return "index";
    }

    // New line

//    @PostMapping("search")
//    public String searchItem(@RequestParam String searchItem){
//        searchedItems = items.stream()
//                .filter(x -> x.getName().toLowerCase().contains(searchItem.toLowerCase()) || x.getDescription().toLowerCase().contains(searchItem.toLowerCase()))
//                .collect(Collectors.toList());
//        return "redirect:/search-items";
//    }
//
//    @GetMapping("search-items")
//    public String displaySearchedItems(Model model){
//        if(searchedItems.isEmpty()) searchedItems.add(new ShopItem("","No item was found.", 0, 0));
//        model.addAttribute("items", searchedItems);
//        return "index";
//    }
}

