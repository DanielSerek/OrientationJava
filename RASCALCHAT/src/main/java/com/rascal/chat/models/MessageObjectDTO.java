package com.rascal.chat.models;

import java.util.List;

public class MessageObjectDTO {

    private List<Message> messages;
    private Channel channel;

    public MessageObjectDTO(List<Message> messages, Channel channel) {
        this.messages = messages;
        this.channel = channel;
    }

    public MessageObjectDTO() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}



