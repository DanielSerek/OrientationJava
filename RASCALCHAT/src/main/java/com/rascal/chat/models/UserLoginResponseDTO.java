package com.rascal.chat.models;

public class UserLoginResponseDTO {

    private String apiKey;

    public UserLoginResponseDTO(String apiKey) {
        this.apiKey = apiKey;
    }

    public UserLoginResponseDTO() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
