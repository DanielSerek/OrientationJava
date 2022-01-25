package com.rascal.chat.controllers;

import com.rascal.chat.models.ChannelDTO;
import com.rascal.chat.models.MessageDTO;
import com.rascal.chat.models.MessageObjectDTO;
import com.rascal.chat.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

    private MessageService messageService;
    String url = "https://rascals-chat.herokuapp.com/api/";


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/api/message")
    public String showLoginScreen() {
        return "create-message";
    }

    @PostMapping("/api/message")
    public String postMessage(MessageDTO messageDTO) {
        messageService.postMessage(messageDTO);
        return "redirect:/api/get-messages";
    }

    @GetMapping("/api/channel")
    public String showCreateChannelScreen () {
        return "create-channel";
    }

    @PostMapping("/api/channel")
    public String createChannel (ChannelDTO channelDTO) {
        messageService.createChannel(channelDTO);
        return "redirect:/api/get-messages";
    }

    @GetMapping("/api/get-messages")
    public String getMessages(Model model) {
        ResponseEntity<MessageObjectDTO> response = this.messageService.getMessages();
        MessageObjectDTO messageObjects = response.getBody();
        model.addAttribute("messages",messageObjects.getMessages());
        return "index";
    }
}
