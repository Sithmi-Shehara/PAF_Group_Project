package com.MyMongoSpring.MyMongoSpring.Controller;

import com.MyMongoSpring.MyMongoSpring.Model.Student;
import com.MyMongoSpring.MyMongoSpring.Model.Badge;



import com.MyMongoSpring.MyMongoSpring.Repositary.BadgeRepo;
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

    @GetMapping ("/getPlan/{id}")  //function for retrieve data
    public Student getStudent(@PathVariable Long id){

        return studentRepo.findById(id).orElse(null);
    }

    @GetMapping ("/fetchPlans")  //function for retrieve all data
    public List<Student> fetchStudents(){

        return studentRepo.findAll();
    }

    @PutMapping ("/updatePlan")  //function for updating data
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

    @DeleteMapping ("/deletePlan/{id}")  //function for deleting data
    public void deleteStudent(@PathVariable Long id){

        studentRepo.deleteById(id);
    }

    @Autowired
    BadgeRepo badgeRepo;

    @PostMapping("/badges/add")
    public void addBadge(@RequestBody Badge badge) {
        badgeRepo.save(badge);
    }

    @GetMapping("/badges")
    public List<Badge> getAllBadges() {
        return badgeRepo.findAll();
    }

    @PutMapping("/badges/claim/{id}")
    public void claimBadge(@PathVariable String id) {
        Badge badge = badgeRepo.findById(id).orElse(null);
        if (badge != null && !badge.isClaimed()) {
            badge.setClaimed(true);
            badgeRepo.save(badge);
        }
    }

}
