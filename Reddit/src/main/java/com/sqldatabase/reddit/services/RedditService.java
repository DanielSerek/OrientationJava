package com.sqldatabase.reddit.services;

import com.sqldatabase.reddit.models.Post;
import com.sqldatabase.reddit.models.User;

import java.util.List;


public interface RedditService {

    void createPost(Long loggedId, String title, String url);

    List<Post> getAllPosts(Integer page);

    void countChange(long loggedId, long postId, String change);

    List<Integer> getPages();

    long getUserId(String userName, String password);

    void addANewUser(User user);

    String getUserName(long loggedId);
}
