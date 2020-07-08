package com.myshop.myshop.controllers;

import com.myshop.myshop.models.ShopItem;
import com.myshop.myshop.repositories.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {

    private Shop shop = new Shop();
    private List<ShopItem> currentlyDisplayedItems;
    private float exchangeRate = 26.6f;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String getIndex(Model model) {
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "index";
    }

    @RequestMapping(value = "more-filters", method = RequestMethod.GET)
    public String getDetailedView(Model model) {
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "more-filters";
    }

    @GetMapping("list-items")
    public String displayAllItems(Model model) {
        currentlyDisplayedItems = shop.getItems();
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "index";
    }

    @GetMapping("only-available")
    public String onlyAvailableItems(Model model) {
        currentlyDisplayedItems = shop.onlyAvailable();
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "index";
    }

    @GetMapping("cheapest-first")
    public String cheapestFirst(Model model) {
        currentlyDisplayedItems = shop.cheapestAscending();
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "index";
    }

    @GetMapping("contains-nike")
    public String containsNike(Model model) {
        currentlyDisplayedItems = shop.containsNike();
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "index";
    }

    @GetMapping("average-stock")
    public String averageStock(Model model) {
        model.addAttribute("average", shop.getAverage());
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "template";
    }

    @GetMapping("most-expensive")
    public String mostExpensive(Model model) {
        model.addAttribute("expensiest", shop.theMostExpensive());
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "template";
    }

    @GetMapping("search")
    public String displaySearchedItems(@RequestParam String searchItem, Model model) {
        currentlyDisplayedItems = shop.searchedItems(searchItem);
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "index";
    }

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

    @GetMapping("filter-by-type/{filter}")
    public String displayOneBook(@PathVariable String filter, Model model) {
        currentlyDisplayedItems = shop.filterByType(filter);
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "more-filters";
    }

    @GetMapping("price-in-eur")
    public String priceInEUR(Model model) {
        currentlyDisplayedItems = shop.pricesInEUR(currentlyDisplayedItems, exchangeRate);
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "more-filters";
    }

    @GetMapping("/original-currency")
    public String originalCurrency(Model model) {
        currentlyDisplayedItems = shop.originalCurrency(currentlyDisplayedItems, exchangeRate);
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "more-filters";
    }

    @GetMapping("filter-by-price/{filter}")
    public String filterByPrice(@PathVariable("filter") String filter, @RequestParam(value = "price", required = true) int price, Model model) {
        currentlyDisplayedItems = shop.filterByPrice(currentlyDisplayedItems, filter, price);
        model.addAttribute("items", currentlyDisplayedItems);
        model.addAttribute("EUR", shop.isPricesInEUR());
        return "more-filters";
    }

}

