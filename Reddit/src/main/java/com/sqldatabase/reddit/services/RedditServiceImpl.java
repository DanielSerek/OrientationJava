package com.sqldatabase.reddit.services;

import com.sqldatabase.reddit.models.Post;
import com.sqldatabase.reddit.models.User;
import com.sqldatabase.reddit.repositories.RedditRepository;
import com.sqldatabase.reddit.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedditServiceImpl implements RedditService {

    private RedditRepository redditRepository;
    private UsersRepository usersRepository;

    @Autowired
    public RedditServiceImpl(RedditRepository redditRepository, UsersRepository usersRepository) {
        this.redditRepository = redditRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void createPost(Long loggedId, String title, String url) {
        Post post = new Post(title, url);
        User user = this.usersRepository.getOne(loggedId);
        post.setUser(user);
        this.redditRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts(Integer page) {
        int max = this.redditRepository.findAll().size();
        if (page.equals(1)){
            return this.redditRepository.findAllByOrderByVotesDesc();
//            return this.redditRepository.findAll().stream()
//                    .sorted(Comparator.comparing(Post::getVotes).reversed())
//                    .limit(10)
//                    .collect(Collectors.toList());
        }
        else{
            return this.redditRepository.findAllByOrderByVotesDesc()
                    .subList((page-1)*10, ((page * 10) > max) ? max : page*10);
        }
    }

    @Override
    public void countChange(long loggedId, long postId, String change) {
        Post post = this.redditRepository.getOne(postId);

        List<Long> idsVotedForPost = this.redditRepository.getOne(postId).getVotedUserIds();
        if(!idsVotedForPost.contains(loggedId)) {
            if (change.equals("add")) {
                post.setVotes(post.getVotes() + 1);
            }
            if (change.equals("deduct")) {
                post.setVotes(post.getVotes() - 1);
            }
            idsVotedForPost.add(loggedId);
        }
        post.setPostId(post.getPostId());
        this.redditRepository.save(post);
    }

    @Override
    public List<Integer> getPages() {
        int modulo = this.redditRepository.findAll().size() % 10;
        int totalPages = this.redditRepository.findAll().size() / 10;
        if(modulo > 0) {
            totalPages++;
        }
        List<Integer> pages = new ArrayList<>();
        for (int i = 0; i < totalPages; i++) {
            pages.add(i + 1);
        }
        return pages;
    }

    @Override
    public long getUserId(String userName, String password) {
        for (User user : usersRepository.findAll()) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user.getUserId();
            }
        }
        return -1;
    }

    @Override
    public void addANewUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public String getUserName(long loggedId) {
        return usersRepository.findAll().stream().filter(x -> x.getUserId() == loggedId).map(x -> x.getUserName()).findFirst().orElse(null);
    }
}
