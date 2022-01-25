package com.rascal.chat.models;

public class ChannelDTO {

    private String name;
    private String description;

    public ChannelDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ChannelDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
