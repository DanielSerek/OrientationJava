package com.rascal.chat.services;

import com.rascal.chat.models.UserLoginDTO;

public interface LoginService {

    String logUser(UserLoginDTO userLoginDTO);

    void registerUser(UserLoginDTO userLoginDTO);

    void updateUrl(String apiKey, String username, String avatarurl);
}
