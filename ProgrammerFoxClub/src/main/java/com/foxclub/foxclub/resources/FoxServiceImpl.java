package com.foxclub.foxclub.resources;

import com.foxclub.foxclub.models.Drink;
import com.foxclub.foxclub.models.Food;
import com.foxclub.foxclub.models.Fox;
import com.foxclub.foxclub.repository.FoxDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public boolean checkUserExists(String name) {
        if(getAFox(name) != null){
            if (getAFox(name).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void changeNutrition(String name, String food, String drink) {
        Fox fox = getAFox(name);
        if(fox.getName().equals(name)){
            Food previousFood = fox.getFood();
            fox.setFood(Food.valueOf(food));
            if(previousFood != Food.valueOf(food)){
                fox.addLog(getTimeStamp() + " : Food has been changed from: " + previousFood.toString() + " to: " + food);
            }
            Drink previousDrink = fox.getDrink();
            fox.setDrink(Drink.valueOf(drink));
            if(previousDrink != Drink.valueOf(drink)){
                fox.addLog(getTimeStamp() + " : Drink has been changed from: " + previousDrink.toString() + " to: " + drink);
            }
        }
    }

    public void learnATrick(String name, String trick) {
        Fox fox = getAFox(name);
        if(fox.getName().equals(name)){
            if(!(fox.getTricks().contains(trick))){
                fox.addTrick(trick);
                fox.addLog(getTimeStamp() + " : Learned to: " + trick);
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
        Fox fox = getAFox(name);
        if(fox.getName().equals(name)){
            return fox.getFood();
        }
        return null;
    }

    public Drink getDefaultDrink(String name) {
        Fox fox = getAFox(name);
        if(fox.getName().equals(name)){
            return fox.getDrink();
        }
        return null;
    }

    public List<String> getLogs(String name) {
        Fox fox = getAFox(name);
        if(fox.getName().equals(name)){
            return fox.getLogs();
        }
        return null;
    }

    private String getTimeStamp() {
        String pattern = "yyyy. MMMM dd. HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
}
