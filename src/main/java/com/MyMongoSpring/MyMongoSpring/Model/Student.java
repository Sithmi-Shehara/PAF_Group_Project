package com.MyMongoSpring.MyMongoSpring.Model;

import lombok.AllArgsConstructor; //initialization
import lombok.Data;  //getters and setters
import lombok.NoArgsConstructor;  //initialization
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;  //document or table name mongodb

import java.util.Date;

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

    private String topic;
    private String resourceLink;
    private String status;
    private Date targetdate;

    private Date createddate;
    private Date updateddate;
}
