package com.example.PAF.model;

import java.time.LocalDateTime;
import java.util.UUID;

//created the coment model
public class Comment {

    private String id = UUID.randomUUID().toString();
    private String commentText;
    private String commentedBy;
    private LocalDateTime commentedAt;

    // created setter and getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(String commentedBy) {
        this.commentedBy = commentedBy;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }
}
