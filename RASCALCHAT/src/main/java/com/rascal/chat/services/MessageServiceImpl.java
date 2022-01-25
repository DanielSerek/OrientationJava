package com.rascal.chat.services;

import com.rascal.chat.models.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageServiceImpl implements MessageService{

    String url = "https://rascals-chat.herokuapp.com/api/";

    @Override
    public void postMessage(MessageDTO messageDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.set("apiKey", System.getProperty("APIKEY"));
        HttpEntity<?> requestEntity = new HttpEntity<>(messageDTO, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(url + "message", requestEntity, UserLoginResponseDTO.class);
    }

    public ResponseEntity<MessageObjectDTO> getMessages() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("apiKey", System.getProperty("APIKEY"));
        GetMessagesDTO messagesDTO = new GetMessagesDTO();
        messagesDTO.setCount(50L);
        HttpEntity<?> requestEntity = new HttpEntity<>(messagesDTO, headers);
        return restTemplate.postForEntity(url + "channel/get-messages", requestEntity, MessageObjectDTO.class);
    }

    @Override
    public ChannelResponseDTO createChannel(ChannelDTO channelDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.set("apiKey", System.getProperty("APIKEY"));
        HttpEntity<?> requestEntity = new HttpEntity<>(channelDTO, headers);
        ResponseEntity<ChannelResponseDTO> response = restTemplate.postForEntity(url + "channel", requestEntity, ChannelResponseDTO.class);
        return response.getBody();
    }
}
