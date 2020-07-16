package com.foxclub;

import com.foxclub.services.FoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoxclubApplication implements CommandLineRunner {

    private FoxService foxService;

    @Autowired
    FoxclubApplication (FoxService foxService){
        this.foxService = foxService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FoxclubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //tricks = new ArrayList<>(Arrays.asList("shoplifting", "raping", "killing", "torturing", "fighting", "kidnapping"));
//        Fox karak = new Fox("Karak", new ArrayList<>(Arrays.asList("kills other foxes", "plays piano")), Food.Pasta, Drink.Cola);
//        Fox john = new Fox("John", null, Food.Steaks, Drink.Whiskey);
//        User karakUser = new User ("Karak", "1234");
//        User johnUser = new User("John", "1234");
//        this.foxService.addANewUser(karakUser);
//        this.foxService.addANewUser(johnUser);
//        karak.setUser(karakUser);
//        john.setUser(johnUser);
//        this.foxService.addANewFox(karak);
//        this.foxService.addANewFox(john);
    }
}
