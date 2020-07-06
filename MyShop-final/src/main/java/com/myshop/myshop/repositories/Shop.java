package com.myshop.myshop.repositories;

import com.myshop.myshop.models.ShopItem;

import java.util.*;
import java.util.stream.Collectors;

public class Shop {
    private List<ShopItem> items;
    private boolean pricesInEUR = false;

    public boolean isPricesInEUR() {
        return pricesInEUR;
    }

    public Shop(){
        items = new ArrayList<>();
        items.add(new ShopItem("Running shoes","Clothes and Shoes","Nike running shoes for every day sport", 1000.0f, 5));
        items.add(new ShopItem("Printer","Electronics","Some HP printer that will print pages", 3000.0f, 2));
        items.add(new ShopItem("Coca cola","Beverages and Snacks","0.5l standard coke", 25.0f, 0));
        items.add(new ShopItem("Wokin","Beverages and Snacks","Chicken with fried rice and WOKIN sauce", 119.0f, 100));
        items.add(new ShopItem("T-shirt","Clothes and Shoes","Blue with a corgi on a bike", 300.0f, 1));
    }

    public List<ShopItem> getItems() {
        return items;
    }

    public List<ShopItem> onlyAvailable(){
        return items.stream()
                .filter(x -> x.getQuantityOfStock() > 0)
                .collect(Collectors.toList());
    }

    public List<ShopItem> cheapestAscending() {
         return items.stream()
                .sorted(Comparator.comparing(ShopItem::getPrice))
                .collect(Collectors.toList());
    }

    public List<ShopItem> containsNike() {
         return items.stream()
                .filter(x -> x.getDescription().toLowerCase().contains("nike") || x.getName().toLowerCase().contains("nike"))
                .collect(Collectors.toList());
    }

    public double getAverage() {
        IntSummaryStatistics stats = items.stream()
                .mapToInt((x) -> x.getQuantityOfStock())
                .summaryStatistics();
        return stats.getAverage();
    }

    public String theMostExpensive() {
        ShopItem theMostExpensive = items.stream()
                .sorted(Comparator.comparing(ShopItem::getPrice).reversed())
                .findFirst()
                .orElse(null);
        return theMostExpensive.getName();
    }

    public List<ShopItem> searchedItems(String searchItem) {
        List<ShopItem> searchedItems = items.stream()
                .filter(x -> x.getName().toLowerCase().contains(searchItem.toLowerCase()) || x.getDescription().toLowerCase().contains(searchItem.toLowerCase()))
                .collect(Collectors.toList());

        if(searchedItems.isEmpty()) searchedItems.add(new ShopItem("","","No item was found.", 0, 0));

        return searchedItems;
    }

    public List<ShopItem> filterByType(String filter) {
        return items.stream()
                .filter(x -> x.getType().toLowerCase().equals(filter.toLowerCase().replaceAll("-", " ")))
                .collect(Collectors.toList());
    }

    public List<ShopItem> pricesInEUR(List<ShopItem> listOfItems, float exchangeRate) {
        if (!pricesInEUR) {
            for (ShopItem item : items) {
                item.setPrice(item.getPrice()/exchangeRate);
            }
            pricesInEUR = true;
            return listOfItems;
        } else return listOfItems;
    }

    public List<ShopItem> originalCurrency(List<ShopItem> listOfItems, float exchangeRate) {
        if (pricesInEUR) {
            for (ShopItem item : items) {
                item.setPrice(item.getPrice()*exchangeRate);
            }
            pricesInEUR = false;
            return listOfItems;
        } else return listOfItems;
    }

    public List<ShopItem> filterByPrice(List<ShopItem> currentlyDisplayedItems, String condition, Integer price) {
        List<ShopItem> filteredItems = new ArrayList<>();
        for (ShopItem item : items) {
            if (condition.contains("exactly") && price.equals(Math.round(item.getPrice()))){
                filteredItems.add(item);
            }
            if (condition.contains("below") && item.getPrice() < price){
                filteredItems.add(item);
            }
            if(condition.contains("above") && item.getPrice() > price){
                filteredItems.add(item);
            }
        }
        if (filteredItems.isEmpty()) return currentlyDisplayedItems;
        else return filteredItems;
    }
}
