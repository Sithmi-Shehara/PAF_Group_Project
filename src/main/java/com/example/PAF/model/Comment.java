package com.example.PAF.model;

import java.time.LocalDateTime;

//created the coment model
public class Comment {

    private String commentText;
    private String commentedBy;
    private LocalDateTime commentedAt;

    // created setter and getters
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
