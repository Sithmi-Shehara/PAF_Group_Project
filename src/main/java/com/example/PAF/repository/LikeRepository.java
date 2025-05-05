package com.example.PAF.repository;

import com.example.PAF.model.PostLike;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends MongoRepository<PostLike, String> {
    Optional<PostLike> findByPostIdAndUserName(String postId, String userName);
    List<PostLike> findByPostId(String postId);
    void deleteByPostIdAndUserName(String postId, String userName);
}
