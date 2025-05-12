package com.example.PAF.controller;

import com.example.PAF.model.Notification;
import com.example.PAF.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // POST endpoint to add a new notification
    @PostMapping
    public ResponseEntity<Notification> addNotification(@RequestBody Notification notification) {
        return notificationService.addNotification(notification);
    }

    // GET endpoint to find a notification by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> findNotificationById(@PathVariable String id) {
        return notificationService.findNotificationById(id);
    }

    // GET endpoint to find all notifications (typically non-deleted ones)
    @GetMapping
    public ResponseEntity<List<Notification>> findAllNotifications() {
        return notificationService.findAllNotifications();
    }

    // PUT endpoint to update an existing notification by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotificationById(@PathVariable String id, @RequestBody Notification notification) {
        return notificationService.updateNotificationById(id, notification);
    }

    // DELETE endpoint to delete a notification by its ID (logically)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNotificationById(@PathVariable String id) {
        return notificationService.deleteNotificationById(id);
    }
} 