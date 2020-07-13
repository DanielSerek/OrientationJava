package com.foxclub.repository;

import com.foxclub.models.Drink;
import com.foxclub.models.Food;
import com.foxclub.models.Fox;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FoxDatabase {
    private List<Fox> foxList = new ArrayList<>();
    private List<String> tricks;

    public FoxDatabase() {
        this.foxList.add(new Fox("Karak", new ArrayList<>(Arrays.asList("kills other foxes", "plays piano")), Food.Pasta, Drink.Cola));
        this.foxList.add(new Fox("John", null, Food.Steaks, Drink.Whiskey));
        tricks = new ArrayList<>(Arrays.asList("shoplifting", "raping", "killing", "torturing", "fighting", "kidnapping"));
    }

    public List<Fox> getFoxList() {
        return foxList;
    }

    public void addFox(Fox fox) {
        this.foxList.add(fox);
    }

    public List<String> getTricks() {
        return tricks;
    }
}
