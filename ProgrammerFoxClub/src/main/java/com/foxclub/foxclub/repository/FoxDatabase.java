package com.foxclub.foxclub.repository;

import com.foxclub.foxclub.models.Drink;
import com.foxclub.foxclub.models.Food;
import com.foxclub.foxclub.models.Fox;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FoxDatabase {
    private List<Fox> foxList = new ArrayList<>();

    public FoxDatabase(){
        this.foxList.add(new Fox("Karak", new ArrayList<>(Arrays.asList("kills other foxes", "plays piano")), Food.pasta, Drink.Cola));
        this.foxList.add(new Fox("John", null,Food.steaks, Drink.Whiskey));
    }

    public List<Fox> getFoxList() {
        return foxList;
    }

    public void addFox(Fox fox) {
        this.foxList.add(fox);
    }
}
