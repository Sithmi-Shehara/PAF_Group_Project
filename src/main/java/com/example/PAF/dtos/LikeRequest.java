package com.example.PAF.dtos;

public class LikeRequest {
    private String postId;
    private String userName;

    // Getters & Setters
    public String getPostId() {
        return postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
