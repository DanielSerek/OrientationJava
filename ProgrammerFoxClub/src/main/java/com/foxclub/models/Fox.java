package com.foxclub.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Fox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foxId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    @ElementCollection
    private List<String> tricks;
    private Food food;
    private Drink drink;
    @ElementCollection
    private List<String> logs;

    public Fox(String name, List<String> tricks, Food food, Drink drink) {
        this.tricks = tricks;
        this.food = food;
        this.drink = drink;
        logs = new ArrayList<>();
    }

    public Fox(){
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getFoxId() {
        return foxId;
    }

    public void setFoxId(long foxId) {
        this.foxId = foxId;
    }

    public void setTricks(List<String> tricks) {
        this.tricks = tricks;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    public List<String> getTricks() {
        return tricks;
    }

    public void addTrick(String trick) {
        this.tricks.add(trick);
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void addLog(String log) {
        this.logs.add(log);
    }
}
