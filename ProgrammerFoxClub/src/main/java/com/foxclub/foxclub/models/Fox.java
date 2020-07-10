package com.foxclub.foxclub.models;

import java.util.ArrayList;
import java.util.List;

public class Fox {
    private String name;
    private List<String> tricks;
    private Food food;
    private Drink drink;
    private List<String> logs;

    public Fox(String name, List<String> tricks, Food food, Drink drink) {
        this.name = name;
        this.tricks = tricks;
        this.food = food;
        this.drink = drink;
        logs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTricks() {
        return tricks;
    }

    public void addTrick(String trick) {
        this.tricks.add(trick);
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void addLog(String log) {
        this.logs.add(log);
    }
}
