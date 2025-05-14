package com.example.PAF.controller;

import com.example.PAF.model.Comment;
import com.example.PAF.model.LearningProgressUpdate;
import com.example.PAF.service.LearningProgressUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    // created learning progress update controller
@RestController
@RequestMapping("/api/progress")
@CrossOrigin
public class LearningProgressUpdateController {

    @Autowired
    private LearningProgressUpdateService service;
    // created @postMapping
    @PostMapping
    public LearningProgressUpdate createProgress(@RequestBody LearningProgressUpdate update) {
        return service.createUpdate(update);
    }
    //created @getMapping

    @GetMapping("/user/{userId}")
    public List<LearningProgressUpdate> getUserProgress(@PathVariable String userId) {
        return service.getUpdatesByUser(userId);
    }
    //created getAll progress
    @GetMapping
    public List<LearningProgressUpdate> getAllProgress() {
        return service.getAllProgress();
    }

    // created putMapping
    @PutMapping("/{id}")
    public LearningProgressUpdate updateProgress(@PathVariable String id, @RequestBody LearningProgressUpdate update) {
        return service.updateProgress(id, update);
    }
    // created deleteMapping
    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable String id) {
        service.deleteProgress(id);
    }
    // created postMapping
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
