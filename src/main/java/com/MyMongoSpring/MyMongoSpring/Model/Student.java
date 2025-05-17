package com.MyMongoSpring.MyMongoSpring.Model;

import lombok.AllArgsConstructor; //initialization
import lombok.Data;  //getters and setters
import lombok.NoArgsConstructor;  //initialization
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;  //document or table name mongodb

import java.awt.*;
import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id //primary key
    private String planId;
    private String planName;
    private String plandesc;
    private Date completedate;
    private String status;
    private Date createddate;
    private Date updateddate;

    private List<Topic> topics;
}
