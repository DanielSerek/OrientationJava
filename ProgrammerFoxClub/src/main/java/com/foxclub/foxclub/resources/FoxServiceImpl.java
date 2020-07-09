package com.foxclub.foxclub.resources;

import com.foxclub.foxclub.models.Drink;
import com.foxclub.foxclub.models.Food;
import com.foxclub.foxclub.models.Fox;
import com.foxclub.foxclub.repository.FoxDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            database.addFox(new Fox(name, null, Food.Pasta, Drink.Beer));
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

    public void learnATrick(String name, String trick) {
        for (Fox fox : database.getFoxList()) {
            if(fox.getName().equals(name)){
                if(!(fox.getTricks().contains(trick))){
                    fox.addTrick(trick);
                }
            }
        }
    }

    public List<String> getTricks(String name) {
        List<String> newTricks = new ArrayList<>();
        for (String trick : database.getTricks()) {
            if(!getAFox(name).getTricks().contains(trick)){
                newTricks.add(trick);
            }
        }
        return newTricks;
    }

    public boolean allSkillsLearned(String name) {
        for (String trick : database.getTricks()) {
            for (int i = 0; i < getAFox(name).getTricks().size(); i++) {
                if(!getAFox(name).getTricks().contains(trick)) return false;
            }
        }
        return true;
    }

    public Food getDefaultFood(String name) {
        for (Fox fox : database.getFoxList()) {
            if(fox.getName().equals(name)){
                return fox.getFood();
            }
        }
        return null;
    }

    public Drink getDefaultDrink(String name) {
        for (Fox fox : database.getFoxList()) {
            if(fox.getName().equals(name)){
                return fox.getDrink();
            }
        }
        return null;
    }
}
