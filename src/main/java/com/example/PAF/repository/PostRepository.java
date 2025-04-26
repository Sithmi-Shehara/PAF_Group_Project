package com.example.PAF.repository;

import com.example.PAF.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, Integer> {
    Post findById(String id);

    void deleteById(String id);
}
