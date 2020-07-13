package com.dependencyinjection.colors;

import org.springframework.stereotype.Service;

@Service
public class PrintingService {
    public void printMe(String s) {
        System.out.println(s);
    }
}
