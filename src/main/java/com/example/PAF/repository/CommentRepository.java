package com.example.PAF.repository;

import com.example.PAF.model.PostComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CommentRepository extends MongoRepository<PostComment, String> {
    List<PostComment> findByPostId(String postId);
}
