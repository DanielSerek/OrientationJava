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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "asssigneeId")
    private Assignee assignee;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    private Date originalTimeStamp;

    public Task(){
    }

    public Task(String title, String description, LocalDateTime dueDate, boolean urgent, boolean done){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.urgent = urgent;
        this.done = done;
        this.timeStamp = new Date();
        this.originalTimeStamp = new Date();
    }

    public Task(String title, String description, LocalDateTime dueDate, Date originalTimeStamp, boolean urgent, boolean done){
        this(title, description,dueDate, urgent,done);
        this.originalTimeStamp = originalTimeStamp;
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

    public String getDueDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy HH:mm:ss");
        return dueDate.format(formatter);
    }

    public LocalDateTime getDueDate(){
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

    public Date getTimeStampDate(){
        return this.timeStamp;
    }

    public String getTimeStamp() {
        if(this.timeStamp == null) return "";
        String pattern = "dd. MMMM yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(this.timeStamp);
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getOriginalTimeStampDate(){
        return this.originalTimeStamp;
    }

    public String getOriginalTimeStamp() {
        if(this.originalTimeStamp == null) return "";
        String pattern = "dd. MMMM yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(this.originalTimeStamp);
    }

    public void setOriginalTimeStamp(Date originalTimeStamp) {
        this.originalTimeStamp = originalTimeStamp;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }
}
