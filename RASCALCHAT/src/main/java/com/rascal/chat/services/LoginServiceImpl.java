package com.rascal.chat.services;

import com.rascal.chat.models.UserLoginDTO;
import com.rascal.chat.models.UserLoginResponseDTO;
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

    @Override
    public String logUser(UserLoginDTO userLoginDTO)  {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Map<String, Object> data = new HashMap<>();
        data.put("login", userLoginDTO.getLogin());
        data.put("password", userLoginDTO.getPassword());
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<UserLoginResponseDTO> response = restTemplate.postForEntity(url + "login", requestEntity, UserLoginResponseDTO.class);
        return response.getBody().getApiKey();
    }

    @Override
    public void registerUser(UserLoginDTO userLoginDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> data = new HashMap<>();
        data.put("login", userLoginDTO.getLogin());
        data.put("password", userLoginDTO.getPassword());
        ResponseEntity<?> response = restTemplate.postForEntity(url + "register", data, Object.class);
//        Long userId = response.getBody().getUserId();
    }

    @Override
    public void updateUrl(String apiKey, String username, String avatarurl) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("avatarurl", avatarurl);
        HttpHeaders responseHeaders = new HttpHeaders();
        System.out.println(System.getProperty("APIKEY"));
        responseHeaders.set("apiKey", System.getProperty("APIKEY"));
        HttpEntity<?> entity = new HttpEntity<>(data, responseHeaders);
        ResponseEntity<?> response = restTemplate.postForEntity(url + "update", entity, Object.class);
    }


}


