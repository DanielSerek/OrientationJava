package com.rascal.chat.models;

public class GetMessagesDTO {

    private String channelId;
    private String channelSecret;
    private Long count;

    public GetMessagesDTO(String channelId, String channelSecret, Long count) {
        this.channelId = channelId;
        this.channelSecret = channelSecret;
        this.count = count;
    }

    public GetMessagesDTO() {
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
