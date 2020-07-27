package com.rascal.chat.services;

import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<?> logUser(String login, String password);
}
