package com.foxclub.services;

import com.foxclub.models.Drink;
import com.foxclub.models.Food;
import com.foxclub.models.Fox;
import com.foxclub.models.User;

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

    void addANewUser(User user);

    boolean checkPassword(String userName, String password);
}
