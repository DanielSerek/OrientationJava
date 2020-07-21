package com.apiexercises.frontend.models;

public class CamellzerTranslated {

    private String translated;
    private String lang;

    public CamellzerTranslated(String translated, String lang) {
        this.translated = translated;
        this.lang = lang;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
