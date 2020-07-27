package com.testing.groottest.models;

public class Translation {
    private String received;
    private String translated;

    public Translation(String received) {
        this.received = received;
        translated = "I am Groot!";
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }
}
