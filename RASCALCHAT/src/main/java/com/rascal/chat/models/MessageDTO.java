package com.rascal.chat.models;

public class MessageDTO {

    private String channelId;
    private String channelSecret;
    private String content;

    public MessageDTO(String channelId, String channelSecret, String content) {
        this.channelId = channelId;
        this.channelSecret = channelSecret;
        this.content = content;
    }

    public MessageDTO() {
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelSecret() {
        return channelSecret;
    }

    public void setChannelSecret(String channelSecret) {
        this.channelSecret = channelSecret;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
