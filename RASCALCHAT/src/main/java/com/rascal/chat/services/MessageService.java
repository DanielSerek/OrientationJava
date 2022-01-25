package com.rascal.chat.services;

import com.rascal.chat.models.ChannelDTO;
import com.rascal.chat.models.ChannelResponseDTO;
import com.rascal.chat.models.MessageDTO;
import com.rascal.chat.models.MessageObjectDTO;
import org.springframework.http.ResponseEntity;

public interface MessageService {

    void postMessage(MessageDTO messageDTO);

    ResponseEntity<MessageObjectDTO> getMessages();

    ChannelResponseDTO createChannel(ChannelDTO channelDTO);
}
