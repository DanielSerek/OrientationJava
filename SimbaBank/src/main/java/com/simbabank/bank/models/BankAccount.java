package com.simbabank.bank.models;

import java.text.DecimalFormat;

public class BankAccount {
    private String name;
    private double balance;
    private String animalType;
    private boolean goodGuy;

    public BankAccount(String name, double balance, String animalType, boolean goodGuy) {
        this.name = name;
        this.balance = balance;
        this.animalType = animalType;
        this.goodGuy = goodGuy;
    }

    public String getName() {
        return name;
    }

    public boolean isGoodGuy() {
        return goodGuy;
    }

    public void setGoodGuy(boolean goodGuy) {
        this.goodGuy = goodGuy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(balance));
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
