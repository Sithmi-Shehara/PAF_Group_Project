package com.MyMongoSpring.MyMongoSpring.Controller;

import com.MyMongoSpring.MyMongoSpring.Model.Student;
import com.MyMongoSpring.MyMongoSpring.Repositary.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    StudentRepo studentRepo;

    @PostMapping("/addPlan")  //function for inserting data
    public void addStudent(@RequestBody Student student){
        studentRepo.save(student);

    }

    @GetMapping ("/getPlan/{id}")  //function for inserting data
    public Student getStudent(@PathVariable Integer id){

        return studentRepo.findById(id).orElse(null);
    }

    @GetMapping ("/fetchPlans")  //function for inserting data
    public List<Student> fetchStudents(){

        return studentRepo.findAll();
    }

    @PutMapping ("/updatePlan")  //function for inserting data
    public void updateStudent(@RequestBody Student student){
        //fetch data using id
        Student data=studentRepo.findById(student.getPlanId()).orElse(null);
        System.out.println(data);

        //check if null
        if(data!=null)
        {
            data.setPlanName(student.getPlanName());
            data.setPlandesc(student.getPlandesc());
            data.setTopic(student.getTopic());
            data.setResourceLink(student.getResourceLink());
            studentRepo.save(data);
        }
    }

    @DeleteMapping ("/deletePlan/{id}")  //function for inserting data
    public void deleteStudent(@PathVariable Integer id){

        studentRepo.deleteById(id);
    }
}
