package com.apiexercises.frontend.models;

public class Greeting {

    private String name;
    private String title;
    private String welcome_message;
    private String error;

    public Greeting(String name, String title) {
        this.name = name;
        this.title = title;
        welcome_message = "Oh, hi there " + name + ", my dear " + title + "!";
    }

    public Greeting (String error){
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWelcome_message() {
        return welcome_message;
    }

    public void setWelcome_message(String welcome_message) {
        this.welcome_message = welcome_message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
