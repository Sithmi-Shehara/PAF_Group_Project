package com.example.PAF.model;

import java.util.Date;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "notifications")
@Data
public class Notification {
    @MongoId(value = FieldType.STRING)
    private String id;
    private String userName;
    private String title;
    private String description;
    private boolean deleted = false;
    private Date createdAt;
}
