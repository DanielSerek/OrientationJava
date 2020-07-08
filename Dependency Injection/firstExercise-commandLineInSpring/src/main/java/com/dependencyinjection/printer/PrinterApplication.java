package com.dependencyinjection.printer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrinterApplication implements CommandLineRunner {

    private final Printer printer;

    public PrinterApplication(Printer printer) {
        this.printer = printer;
    }


    public static void main(String[] args) {
        SpringApplication.run(PrinterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        printer.log("Hello World");
    }
}
