package com.rascal.chat.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    String url = "https://rascals-chat.herokuapp.com/api/user/";

//    @Override
//    public ResponseEntity<User> logUser(String login, String password) {
//        User user = new User (login, password);
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForEntity(url + "login", user, User.class);
//    }

    @Override
    public ResponseEntity<?> logUser(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Map<String, Object> data = new HashMap<>();
        data.put("login", login);
        data.put("password", password);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(url + "login", requestEntity, String.class);
        String s = (String) response.getBody();
        return response;
    }

}


