package com.foxclub.foxclub.services;

import com.foxclub.foxclub.models.Drink;
import com.foxclub.foxclub.models.Food;
import com.foxclub.foxclub.models.Fox;

import java.util.List;

public interface FoxService {
    Fox getAFox(String name);

    boolean checkUserExists(String name);

    void changeNutrition(String name, String food, String drink);

    void learnATrick(String name, String trick);

    List<String> getTricks(String name);

    boolean allSkillsLearned(String name);

    Food getDefaultFood(String name);

    Drink getDefaultDrink(String name);

    List<String> getLogs(String name);
}
