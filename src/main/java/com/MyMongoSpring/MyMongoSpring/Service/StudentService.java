package com.MyMongoSpring.MyMongoSpring.Service;

import com.MyMongoSpring.MyMongoSpring.Model.Student;
import com.MyMongoSpring.MyMongoSpring.Repositary.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student addStudent(Student student) {
        student.setCreateddate(new Date());
        student.setUpdateddate(new Date());
        return studentRepo.save(student);
    }

    public Student getStudentById(String id) {
        return studentRepo.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student updateStudent(String id, Student updatedData) {
        Optional<Student> existing = studentRepo.findById(id);

        if (existing.isPresent()) {
            Student data = existing.get();
            data.setPlanName(updatedData.getPlanName());
            data.setPlandesc(updatedData.getPlandesc());
            data.setCompletedate(updatedData.getCompletedate());
            data.setStatus(updatedData.getStatus());
            data.setTopics(updatedData.getTopics()); // correctly update the topics list
            data.setUpdateddate(new Date());
            return studentRepo.save(data);
        }
        return null;
    }

    public boolean deleteStudent(String id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
