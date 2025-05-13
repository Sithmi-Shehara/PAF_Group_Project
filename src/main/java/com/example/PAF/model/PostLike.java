package com.example.PAF.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post_likes")
public class PostLike {
//created postLike model
    @Id
    private String id;
    private String postId;
    private String userName;

    public PostLike() {}

    public PostLike(String postId, String userName) {
        this.postId = postId;
        this.userName = userName;
    }

    // created Getters & Setters

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
