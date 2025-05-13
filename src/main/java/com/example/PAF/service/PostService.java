package com.example.PAF.service;

import com.example.PAF.model.Notification;
import com.example.PAF.model.Post;
import com.example.PAF.dtos.PostRequest;
import com.example.PAF.dtos.PostUpdateRequest;
import com.example.PAF.model.User;
import com.example.PAF.repository.NotificationRepository;
import com.example.PAF.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final NotificationService notificationService;
    private final UserService userService;

    // TODO: change file directory

    private static final String IMAGE_DIRECTORY = "C:\\Users\\ASUS\\Documents\\PAF-Frontend\\public\\posts\\";


    public PostService(PostRepository postRepository, NotificationService notificationService, UserService userService) {
        this.postRepository = postRepository;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public ResponseEntity<Post> findPostById(String id) {
        Post post = postRepository.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    public ResponseEntity<Post> addPost(PostRequest postRequest) {
        try {
            Post post = new Post();
            Optional<User> user = userService.findByUsername(postRequest.getUserName());
            post.setDescription(postRequest.getDescription());
            post.setTitle(postRequest.getTitle());
            post.setUserName(postRequest.getUserName());
            if (user.isPresent()) {
                post.setName(user.get().getFirstName() + " " + user.get().getLastName());
                post.setHeadline(user.get().getHeadline());
            }
            post.setTags(postRequest.getTags());
            post.setCreatedAt(new Date());
            
            List<String> filePaths = new ArrayList<>();
            
            // Handle mediaFile0
            if (postRequest.getMediaFile0() != null && !postRequest.getMediaFile0().isEmpty()) {
                String filePath = saveMediaFile(postRequest.getMediaFile0());
                filePaths.add(filePath);
            }
            
            // Handle mediaFile1
            if (postRequest.getMediaFile1() != null && !postRequest.getMediaFile1().isEmpty()) {
                String filePath = saveMediaFile(postRequest.getMediaFile1());
                filePaths.add(filePath);
            }
            
            // Handle mediaFile2
            if (postRequest.getMediaFile2() != null && !postRequest.getMediaFile2().isEmpty()) {
                String filePath = saveMediaFile(postRequest.getMediaFile2());
                filePaths.add(filePath);
            }
            
            post.setFilePaths(filePaths);
            Post savedPost = postRepository.save(post);

            Notification notification = new Notification();
            notification.setTitle("New Post Created");
            notification.setDeleted(false);
            notification.setDescription(post.getName() + " shared an Post: " + post.getHeadline());
            notification.setCreatedAt(new Date());
            notification.setUserName(postRequest.getUserName());
            notificationService.addNotification(notification);

            return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveMediaFile(MultipartFile file) {
        // Allow image and video files and check size
        String contentType = file.getContentType();
        if ((!contentType.startsWith("image/") && !contentType.startsWith("video/"))) {
            throw new IllegalArgumentException("File Type not supported");
        }
        if (file.getSize() > 1024 * 1024 * 1024) {
            throw new IllegalArgumentException("Only image/video files under 1GB are allowed");
        }

        //create the directory if it doesn't exist
        File directory = new File(IMAGE_DIRECTORY);

        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Directory was created");
        }
        //generate unique file name for the file
        String uniqueFileName = UUID.randomUUID() + "_." + file.getOriginalFilename().split("\\.")[1];

        //Get the absolute path of the file
        String filePath = IMAGE_DIRECTORY + uniqueFileName;
        System.out.println("MediaPath = "+filePath);

        try {
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile); //writing the file to this folder
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving Media: " + e.getMessage());
        }
        return "/posts/"+uniqueFileName;
    }

    public ResponseEntity<Post> updatePostById(String id, PostUpdateRequest updatedPost) {
        Post post = postRepository.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            post.setDescription(updatedPost.getDescription());
            post.setTitle(updatedPost.getTitle());
            post.setTags(updatedPost.getTags());
            post.setUpdatedAt(new Date());
            
            List<String> filePaths = new ArrayList<>();
            
            // Handle mediaFile0
            if (updatedPost.getMediaFile0() != null && !updatedPost.getMediaFile0().isEmpty()) {
                String filePath = saveMediaFile(updatedPost.getMediaFile0());
                filePaths.add(filePath);
            }
            
            // Handle mediaFile1
            if (updatedPost.getMediaFile1() != null && !updatedPost.getMediaFile1().isEmpty()) {
                String filePath = saveMediaFile(updatedPost.getMediaFile1());
                filePaths.add(filePath);
            }
            
            // Handle mediaFile2
            if (updatedPost.getMediaFile2() != null && !updatedPost.getMediaFile2().isEmpty()) {
                String filePath = saveMediaFile(updatedPost.getMediaFile2());
                filePaths.add(filePath);
            }
            
            if (!filePaths.isEmpty()) {
                post.setFilePaths(filePaths);
            }
            
            Post savedPost = postRepository.save(post);
            return new ResponseEntity<>(savedPost, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deletePostById(String id) {
        Post post = postRepository.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Post>> findAllPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }
}
