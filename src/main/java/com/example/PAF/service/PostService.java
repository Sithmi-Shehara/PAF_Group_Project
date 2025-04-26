package com.example.PAF.service;

import com.example.PAF.model.Post;
import com.example.PAF.dtos.PostRequest;
import com.example.PAF.dtos.PostUpdateRequest;
import com.example.PAF.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;

    // TODO: change file directory

    private static final String IMAGE_DIRECTORY ="C:\\Users\\ASUS\\Documents\\PAF-Frontend\\public\\posts\\";

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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
            post.setDescription(postRequest.getDescription());
            post.setTitle(postRequest.getTitle());
            post.setUserName(postRequest.getUserName());
            post.setCreatedAt(new Date());
            MultipartFile file = postRequest.getImageFile();
            if (file != null && !file.isEmpty()) {
                String filePath = saveImageFile(file);
                post.setFilePath(filePath);
            }
            Post savedPost = postRepository.save(post);
            return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveImageFile(MultipartFile file) {
        // Allow only image files and check size
        if (!file.getContentType().startsWith("image/") || file.getSize() > 1024 * 1024 * 1024) {
            throw new IllegalArgumentException("Only image files under 1GB are allowed");
        }

        //create the directory if it doesn't exist
        File directory = new File(IMAGE_DIRECTORY);

        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Directory was created");
        }
        //generate unique file name for the file
        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        //Get the absolute path of the file
        String filePath = IMAGE_DIRECTORY + uniqueFileName;
        System.out.println("ImagePath = "+filePath);

        try {
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile); //writing the file to this folder
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving Image: " + e.getMessage());
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
            post.setUpdatedAt(new Date());
            MultipartFile file = updatedPost.getFile();
            if (file != null && !file.isEmpty()) {
                String filePath = saveImageFile(file);
                post.setFilePath(filePath);
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
