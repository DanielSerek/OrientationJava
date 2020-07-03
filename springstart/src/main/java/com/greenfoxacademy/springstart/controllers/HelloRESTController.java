package com.greenfoxacademy.springstart.controllers;

import com.greenfoxacademy.springstart.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloRESTController {

    AtomicLong counter = new AtomicLong();

    @ResponseBody
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    //@RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String helloWorld(@RequestParam String name) {
        counter.setCounter(counter.getCounter() + 1);
        return "Hello, " + name + "! This site was loaded " + counter.getCounter() + "times since last server start.";
    }

    @GetMapping(value = "/greetingLanguages")
    public String greetingLanguages(@RequestParam String name) {
        counter.setCounter(counter.getCounter() + 1);
        return "Hello, " + name + "! This site was loaded " + counter.getCounter() + "times since last server start.";
    }
}
