package com.rascal.chat.models;

public class Author {
    private String username;
    private int userId;
    private String avatarUrl;

    public Author(String username, int userId, String avatarUrl) {
        this.username = username;
        this.userId = userId;
        this.avatarUrl = avatarUrl;
    }

    public Author() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
