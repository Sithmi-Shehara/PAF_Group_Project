package com.MyMongoSpring.MyMongoSpring.Model;

import lombok.AllArgsConstructor; //initialization
import lombok.Data;  //getters and setters
import lombok.NoArgsConstructor;  //initialization
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;  //document or table name mongodb

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id //primary key
    private Integer rno;

    private String name;

    private String address;
}
