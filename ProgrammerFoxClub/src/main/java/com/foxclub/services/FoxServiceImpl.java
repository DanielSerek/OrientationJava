package com.foxclub.services;

import com.foxclub.models.Drink;
import com.foxclub.models.Food;
import com.foxclub.models.Fox;
import com.foxclub.repository.FoxDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoxServiceImpl implements FoxService{

    @Autowired
    private FoxDatabase database;

    public Fox getAFox(String name){
        return database.getFoxList().stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
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
        List<String> unavailable = database.getTricks().stream()
                .filter(e -> (getAFox(name).getTricks().stream()
                        .filter(d -> d.equals(e))
                        .count())<1)
                .collect(Collectors.toList());

//        List<String> newTricks = new ArrayList();
//        for (String trick : database.getTricks()) {
//            if(!getAFox(name).getTricks().contains(trick)){
//                newTricks.add(trick);
//            }
//        }
        return unavailable;
    }

    public boolean allSkillsLearned(String name) {
//        for (String trick : database.getTricks()) {
//            for (int i = 0; i < getAFox(name).getTricks().size(); i++) {
//                if(!getAFox(name).getTricks().contains(trick)) return false;
//            }
//        }
//        return true;
        return database.getTricks().stream().allMatch(getAFox(name).getTricks()::contains);
    }

    public Food getDefaultFood(String name) {
        if(getAFox(name).getName().equals(name)){
            return getAFox(name).getFood();
        }
        return null;
    }

    public Drink getDefaultDrink(String name) {
        if(getAFox(name).getName().equals(name)){
            return getAFox(name).getDrink();
        }
        return null;
    }

    public List<String> getLogs(String name) {
        if(getAFox(name).getName().equals(name)){
            return getAFox(name).getLogs();
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
