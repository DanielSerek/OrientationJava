package com.sqldatabase.reddit.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private int votes;
    private String title;
    private String url;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ElementCollection
    private List<Long> votedUserIds;

    public Post(String title, String url) {
        this.title = title;
        this.url = url;
        this.votes = 0;
        timeStamp = new Date();
        votedUserIds = new ArrayList<>();
    }

    public Post() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Long> getVotedUserIds() {
        return votedUserIds;
    }

    public void addVotingUserId(long userId){
        this.votedUserIds.add(userId);
    }

    public void setVotedUserIds(List<Long> votedUserIds) {
        this.votedUserIds = votedUserIds;
    }
}
