package com.rascal.chat.models;

public class Channel {

    private int id;
    private String name;
    private String description;
    private String admin;
    private String iconurl;
    private String secret;

    public Channel(int id, String name, String description, String admin, String iconurl, String secret) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.admin = admin;
        this.iconurl = iconurl;
        this.secret = secret;
    }

    public Channel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
