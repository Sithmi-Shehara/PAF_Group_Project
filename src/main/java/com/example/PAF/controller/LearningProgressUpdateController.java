package com.example.PAF.controller;

import com.example.PAF.model.Comment;
import com.example.PAF.model.LearningProgressUpdate;
import com.example.PAF.service.LearningProgressUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//created learning progress update controller
@RestController
@RequestMapping("/api/progress")
@CrossOrigin
public class LearningProgressUpdateController {

    @Autowired
    private LearningProgressUpdateService service;

    @PostMapping
    public LearningProgressUpdate createProgress(@RequestBody LearningProgressUpdate update) {
        return service.createUpdate(update);
    }

    @GetMapping("/user/{userId}")
    public List<LearningProgressUpdate> getUserProgress(@PathVariable String userId) {
        return service.getUpdatesByUser(userId);
    }

    @GetMapping
    public List<LearningProgressUpdate> getAllProgress() {
        return service.getAllProgress();
    }

    @PutMapping("/{id}")
    public LearningProgressUpdate updateProgress(@PathVariable String id, @RequestBody LearningProgressUpdate update) {
        return service.updateProgress(id, update);
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable String id) {
        service.deleteProgress(id);
    }

    @PostMapping("/{id}/comment")
    public LearningProgressUpdate addComment(
            @PathVariable String id,
            @RequestBody Comment comment) {
        return service.addComment(id, comment);
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<?> toggleLike(
            @PathVariable String id,
            @RequestParam String userId) {

        LearningProgressUpdate updated = service.toggleLike(id, userId);
        return ResponseEntity.ok(updated);
    }
}
