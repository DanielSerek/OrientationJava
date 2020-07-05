package com.simbabank.bank.models;

import java.text.DecimalFormat;

public class BankAccount {
    private String name;
    private Double balance;
    private String animalType;
    private Boolean isKing;
    private Boolean goodGuy;

    public BankAccount(String name, Double balance, String animalType, Boolean isKing, Boolean goodGuy) {
        this.name = name;
        this.balance = balance;
        this.animalType = animalType;
        if ((isKing == null)) this.isKing = false;
        else this.isKing = isKing;
        if ((goodGuy == null)) this.goodGuy = false;
        else this.goodGuy = goodGuy;
    }

    public String getName() {
        return name;
    }

    public boolean isGoodGuy() {
        return goodGuy;
    }

    public void setGoodGuy(Boolean goodGuy) {
        this.goodGuy = goodGuy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(balance));
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAnimalType() {
        return animalType;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(Boolean king) {
        isKing = king;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
