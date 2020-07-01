package com.greenfoxacademy.springstart;

public class AtomicLong {
    private int counter;

    public AtomicLong() {
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
