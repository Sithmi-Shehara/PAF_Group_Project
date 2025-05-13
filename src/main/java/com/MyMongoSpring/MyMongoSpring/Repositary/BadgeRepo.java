package com.MyMongoSpring.MyMongoSpring.Repositary;

import com.MyMongoSpring.MyMongoSpring.Model.Badge;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BadgeRepo extends MongoRepository<Badge, String> {
}
