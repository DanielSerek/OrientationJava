package com.mysql.connection.models;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private boolean urgent;
    private boolean done;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    public Task(){
    }

    public Task(String title, boolean urgent, boolean done){
        this.title = title;
        this.urgent = urgent;
        this.done = done;
        timeStamp = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTimeStamp() {
        String pattern = "dd. MMMM yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(timeStamp);
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
