package com.example.PAF.service;

import com.example.PAF.dtos.LikeRequest;
import com.example.PAF.model.Notification;
import com.example.PAF.model.Post;
import com.example.PAF.model.PostLike;
import com.example.PAF.model.User;
import com.example.PAF.repository.LikeRepository;
import com.example.PAF.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    @Override
    public String toggleLike(LikeRequest request) {
        var existingLike = likeRepository.findByPostIdAndUserName(request.getPostId(), request.getUserName());

        if (existingLike.isPresent()) {
            likeRepository.deleteByPostIdAndUserName(request.getPostId(), request.getUserName());
            return "Like removed";
        } else {
            PostLike newLike = new PostLike(request.getPostId(), request.getUserName());
            likeRepository.save(newLike);

            Notification notification = new Notification();
            notification.setUserName(request.getUserName());
            notification.setCreatedAt(new Date());
            notification.setTitle("Like Received");
            User user = userService.findByUserName(request.getUserName());
            Post post = postRepository.findById(request.getPostId());
            notification.setDescription(((user != null) ? (user.getFirstName() + " " + user.getLastName()) : request.getUserName()) + " liked the post about : " + post.getTitle());
            notificationService.addNotification(notification);
            return "Like added";
        }
    }

    @Override
    public List<PostLike> getLikesByPostId(String postId) {
        return likeRepository.findByPostId(postId);
    }
}
