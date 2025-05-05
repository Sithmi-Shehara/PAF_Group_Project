package com.example.PAF.controller;

import com.example.PAF.dtos.LikeRequest;
import com.example.PAF.model.PostLike;
import com.example.PAF.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(@RequestBody LikeRequest request) {
        return ResponseEntity.ok(likeService.toggleLike(request));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<PostLike>> getLikesByPost(@PathVariable String postId) {
        return ResponseEntity.ok(likeService.getLikesByPostId(postId));
    }
}
