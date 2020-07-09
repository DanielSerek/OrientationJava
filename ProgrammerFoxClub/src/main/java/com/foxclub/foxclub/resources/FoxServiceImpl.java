package com.foxclub.foxclub.resources;

import com.foxclub.foxclub.models.Drink;
import com.foxclub.foxclub.models.Food;
import com.foxclub.foxclub.models.Fox;
import com.foxclub.foxclub.repository.FoxDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoxServiceImpl implements FoxService{

    @Autowired
    private FoxDatabase database;

    public Fox getAFox(String name){
        for (Fox fox : database.getFoxList()) {
            if(fox.getName().equals(name)){
                return fox;
            }
        }
        return null;
    }

    public void login(String name) {
        if(getAFox(name)==null){
            database.addFox(new Fox(name, null, Food.pasta, Drink.Beer));
        }
    }

    public void changeNutrition(String name, String food, String drink) {
        for (Fox fox : database.getFoxList()) {
            if(fox.getName().equals(name)){
                fox.setFood(Food.valueOf(food));
                fox.setDrink(Drink.valueOf(drink));
            }
        }
    }
}
