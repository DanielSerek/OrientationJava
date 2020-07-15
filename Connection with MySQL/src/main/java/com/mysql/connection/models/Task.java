package com.mysql.connection.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private boolean urgent;
    private boolean done;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "asssigneeId")
    private Assignee assignee;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    public Task(){
    }

    public Task(String title, String description, LocalDateTime dueDate, boolean urgent, boolean done){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm:ss");
        return dueDate.format(formatter);
    }

    public LocalDateTime getDueDateDateFormat(){
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
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

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }
}
