package com.foxclub.foxclub.resources;

import com.foxclub.foxclub.models.Fox;

public interface FoxService {
    public Fox getAFox(String name);

    void login(String name);

    void changeNutrition(String name, String food, String drink);
}
