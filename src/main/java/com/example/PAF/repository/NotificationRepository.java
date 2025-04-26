package com.example.PAF.repository;

import com.example.PAF.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, Integer>  {
}
