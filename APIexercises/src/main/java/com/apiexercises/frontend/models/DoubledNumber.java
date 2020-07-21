package com.apiexercises.frontend.models;

public class DoubledNumber {
    long received;
    long result;
    String error;

    public DoubledNumber(long received, long result) {
        this.received = received;
        this.result = result;
    }

    public DoubledNumber(String error){
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getReceived() {
        return received;
    }

    public long getResult() {
        return result;
    }

    public void setReceived(long received) {
        this.received = received;
    }

    public void setResult(long result) {
        this.result = result;
    }
}
