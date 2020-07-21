package com.apiexercises.frontend.models;

public class NumberRequest {

    private Integer until;
    private Integer result;

    public NumberRequest(Integer until, Integer result) {
        this.until = until;
        this.result = result;
    }

    public NumberRequest(Integer until) {
        this.until = until;
    }

    public NumberRequest(){
    }

    public int getUntil() {
        return until;
    }

    public void setUntil(Integer until) {
        this.until = until;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
