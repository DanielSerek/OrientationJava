package com.dependencyinjection.colors;

public class RedColor implements MyColor {

    private final PrintingService printingService;

    public RedColor(PrintingService printingService) {
        this.printingService = printingService;
    }

    @Override
    public void printColor() {
        printingService.printMe("Blue Color");
    }
}
