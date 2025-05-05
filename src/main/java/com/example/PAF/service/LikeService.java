package com.example.PAF.service;

import com.example.PAF.dtos.LikeRequest;
import com.example.PAF.model.PostLike;

import java.util.List;

public interface LikeService {
    String toggleLike(LikeRequest request);
    List<PostLike> getLikesByPostId(String postId);
}
