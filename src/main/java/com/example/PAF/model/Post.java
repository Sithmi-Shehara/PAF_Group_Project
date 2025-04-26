package com.example.PAF.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "posts")
@Data
public class Post {
    @MongoId(value = FieldType.STRING)
    private String id;
    private String title;
    private String userName;
    private String description;
    private String filePath;
    private Date createdAt;
    private Date updatedAt;
}
