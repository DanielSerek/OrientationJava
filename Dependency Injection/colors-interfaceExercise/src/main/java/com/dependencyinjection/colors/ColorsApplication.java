package com.dependencyinjection.colors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColorsApplication implements CommandLineRunner {

    // Autowiring with a field
    @Autowired
    private MyColor myColor;

    // Autowiring with a constructor
//    @Autowired
//    public ColorsApplication(MyColor color){
//        this.myColor = color;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ColorsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myColor.printColor();
    }
}
