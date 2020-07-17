package com.sqldatabase.reddit.repositories;

import com.sqldatabase.reddit.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedditRepository extends JpaRepository<Post, Long> {
}
