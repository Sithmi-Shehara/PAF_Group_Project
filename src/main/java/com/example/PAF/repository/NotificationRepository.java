package com.example.PAF.repository;

import com.example.PAF.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NotificationRepository extends MongoRepository<Notification, Integer>  {
    Optional<Notification> findById(String id);
}
