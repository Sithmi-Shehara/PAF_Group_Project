package com.example.PAF.service;

import com.example.PAF.model.Notification;
import com.example.PAF.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Add Notification
    public ResponseEntity<Notification> addNotification(Notification notification) {
        try {
            notification.setCreatedAt(new Date());
            notification.setDeleted(false); // Ensure deleted flag is false on creation
            Notification savedNotification = notificationRepository.save(notification);
            return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception details here
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Find Notification by ID
    public ResponseEntity<Notification> findNotificationById(String id) {
        Optional<Notification> notificationData = notificationRepository.findById(id);

        return notificationData
                .map(notification -> new ResponseEntity<>(notification, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Find All Notifications (excluding deleted)
    public ResponseEntity<List<Notification>> findAllNotifications() {
        try {
            // Assuming you want to filter out logically deleted notifications
            // If Notification model doesn't have a 'deleted' flag or similar, adjust this logic
             List<Notification> notifications = notificationRepository.findAll().stream()
                     .filter(notification -> !notification.isDeleted())
                     .toList();
            if (notifications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception details here
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Notification by ID
    public ResponseEntity<Notification> updateNotificationById(String id, Notification updatedNotification) {
        Optional<Notification> notificationData = notificationRepository.findById(id);

        if (notificationData.isPresent()) {
            Notification existingNotification = notificationData.get();
            // Only update fields that are meant to be updatable
            existingNotification.setTitle(updatedNotification.getTitle());
            existingNotification.setDescription(updatedNotification.getDescription());
            // Optionally update userName if needed, depends on requirements
            // existingNotification.setUserName(updatedNotification.getUserName()); 
            // We typically don't update createdAt or id
            
            try {
                Notification savedNotification = notificationRepository.save(existingNotification);
                return new ResponseEntity<>(savedNotification, HttpStatus.OK);
            } catch (Exception e) {
                // Log the exception
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Notification by ID (Logical Delete)
    public ResponseEntity<HttpStatus> deleteNotificationById(String id) {
        try {
            Optional<Notification> notificationData = notificationRepository.findById(id);
            if (notificationData.isPresent()) {
                 Notification notification = notificationData.get();
                 notification.setDeleted(true); // Mark as deleted
                 notificationRepository.save(notification); // Persist the change
                // If you want a hard delete:
                // notificationRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Or HttpStatus.OK if preferred
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception details here
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 