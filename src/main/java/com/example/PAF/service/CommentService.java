package com.example.PAF.service;

import com.example.PAF.dtos.CommentRequest;
import com.example.PAF.model.PostComment;
import java.util.List;

public interface CommentService {
    PostComment addComment(CommentRequest request);
    List<PostComment> getCommentsByPostId(String postId);
}
