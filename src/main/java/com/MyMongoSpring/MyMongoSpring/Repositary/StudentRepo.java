package com.MyMongoSpring.MyMongoSpring.Repositary;

import com.MyMongoSpring.MyMongoSpring.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student,Integer> {
}
