package com.rascal.chat.models;

public class UserUpdateDTO {

    private String username;
    private String avatarurl;

    public UserUpdateDTO(String username, String avatarurl) {
        this.username = username;
        this.avatarurl = avatarurl;
    }

    public UserUpdateDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }
}
