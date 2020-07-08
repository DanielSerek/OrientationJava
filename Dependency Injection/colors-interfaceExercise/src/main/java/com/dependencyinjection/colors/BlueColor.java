package com.dependencyinjection.colors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlueColor implements MyColor {

    @Autowired
    private PrintingService printingService;

    @Override
    public void printColor() {
        printingService.printMe("Blue Color");
    }
}
