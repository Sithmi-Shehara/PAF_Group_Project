package com.example.PAF.service;

import com.example.PAF.dtos.CommentRequest;
import com.example.PAF.model.PostComment;
import com.example.PAF.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public PostComment addComment(CommentRequest request) {
        PostComment comment = new PostComment();
        comment.setContent(request.getContent());
        comment.setUserName(request.getUserName());
        comment.setPostId(request.getPostId());

        return commentRepository.save(comment);
    }

    @Override
    public List<PostComment> getCommentsByPostId(String postId) {
        return commentRepository.findByPostId(postId);
    }
}
