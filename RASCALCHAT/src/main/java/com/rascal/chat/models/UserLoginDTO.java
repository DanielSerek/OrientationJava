package com.rascal.chat.models;

public class UserLoginDTO {

    private String login;
    private String password;


    public UserLoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserLoginDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
