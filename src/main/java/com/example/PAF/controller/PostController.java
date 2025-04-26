package com.example.PAF.controller;


import com.example.PAF.model.Post;
import com.example.PAF.dtos.PostRequest;
import com.example.PAF.dtos.PostUpdateRequest;
import com.example.PAF.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/posts")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable String id) {
        return postService.findPostById(id);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Post>> getPosts() {
        return postService.findAllPosts();
    }

    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@ModelAttribute PostRequest postRequest) {
        return postService.addPost(postRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable String id, @ModelAttribute PostUpdateRequest postRequest) {
        return postService.updatePostById(id, postRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePostById(@PathVariable String id) {
        return postService.deletePostById(id);
    }

}