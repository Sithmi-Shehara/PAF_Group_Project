package com.MyMongoSpring.MyMongoSpring.Model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Topic {

    private String name;
    private String resourceLink;
    private String targetDate;
    private String status;
    public Topic() {
    }

    public Topic(String name, String resourceLink, String targetDate, String status) {
        this.name = name;
        this.resourceLink = resourceLink;
        this.targetDate = targetDate;
        this.status = status;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceLink() {
        return resourceLink;
    }

    public void setResourceLink(String resourceLink) {
        this.resourceLink = resourceLink;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
