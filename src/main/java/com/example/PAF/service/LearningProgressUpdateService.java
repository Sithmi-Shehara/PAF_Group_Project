package com.example.PAF.service;

import com.example.PAF.model.Comment;
import com.example.PAF.model.LearningProgressUpdate;
import com.example.PAF.repository.LearningProgressUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LearningProgressUpdateService {

    @Autowired
    private LearningProgressUpdateRepository repository;

    public LearningProgressUpdate createUpdate(LearningProgressUpdate update) {
        update.setCreatedAt(LocalDateTime.now());
        return repository.save(update);
    }

    public List<LearningProgressUpdate> getUpdatesByUser(String userId) {
        return repository.findByUserId(userId);
    }

    public List<LearningProgressUpdate> getAllProgress() {
        return repository.findAll();
    }

    public LearningProgressUpdate updateProgress(String id, LearningProgressUpdate updated) {
        LearningProgressUpdate existing = repository.findById(id).orElseThrow();
        existing.setTitle(updated.getTitle());
        existing.setContent(updated.getContent());
        existing.setTemplateType(updated.getTemplateType());
        existing.setCreatedAt(LocalDateTime.now());
        return repository.save(existing);
    }

    public void deleteProgress(String id) {
        repository.deleteById(id);
    }

    public LearningProgressUpdate addComment(String postId, Comment comment) {
        LearningProgressUpdate post = repository.findById(postId).orElseThrow();
        comment.setCommentedAt(LocalDateTime.now());
        post.getComments().add(comment);
        return repository.save(post);
    }
}
