package com.example.PAF.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Document(collection = "posts")
@Data
public class Post {
    @MongoId(value = FieldType.STRING)
    private String id;
    private String title;
    private String userName;
    private String headline;
    private String description;
    private String filePath;
    private List<String> tags;
    private Date createdAt;
    private Date updatedAt;
}
