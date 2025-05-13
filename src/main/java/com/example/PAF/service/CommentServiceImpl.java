package com.example.PAF.service;

import com.example.PAF.dtos.CommentRequest;
import com.example.PAF.model.Notification;
import com.example.PAF.model.Post;
import com.example.PAF.model.PostComment;
import com.example.PAF.model.User;
import com.example.PAF.repository.CommentRepository;
import com.example.PAF.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    @Override
    public PostComment addComment(CommentRequest request) {
        PostComment comment = new PostComment();
        comment.setContent(request.getContent());
        comment.setUserName(request.getUserName());
        comment.setPostId(request.getPostId());


        Notification notification = new Notification();
        notification.setUserName(request.getUserName());
        notification.setCreatedAt(new Date());
        notification.setTitle("Comment Received");
        Post post = postRepository.findById(request.getPostId());
        User user = userService.findByUserName(request.getUserName());
        notification.setDescription(((user != null) ? (user.getFirstName() + " " + user.getLastName()) : request.getUserName()) + " commented the post about : " + post.getTitle());
        notificationService.addNotification(notification);

        return commentRepository.save(comment);
    }

    @Override
    public List<PostComment> getCommentsByPostId(String postId) {
        return commentRepository.findByPostId(postId);
    }
}
