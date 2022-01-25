package com.rascal.chat.models;

import java.util.Date;

public class Message {

    private String content;
    private Date created;
    private Author author;

    public Message(String content, Date created, Author author) {
        this.content = content;
        this.created = created;
        this.author = author;
    }

    public Message() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
