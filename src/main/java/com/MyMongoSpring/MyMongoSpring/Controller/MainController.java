package com.MyMongoSpring.MyMongoSpring.Controller;

import com.MyMongoSpring.MyMongoSpring.Model.Student;
import com.MyMongoSpring.MyMongoSpring.Model.Badge;
import com.MyMongoSpring.MyMongoSpring.Service.StudentService;
import com.MyMongoSpring.MyMongoSpring.Service.BadgeService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")  // Base path for API
public class MainController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BadgeService badgeService;

    // -------------------- Student Endpoints --------------------

    @PostMapping("/plans")
    public ResponseEntity<Student> createPlan(@RequestBody Student student) {
        Student savedPlan = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlan);
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<Student> getPlan(@PathVariable String id) {
        Student plan = studentService.getStudentById(id);
        if (plan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(plan);
    }



    @GetMapping("/plans")
    public ResponseEntity<List<Student>> getAllPlans() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }


    @PutMapping("/plans/{id}")
    public ResponseEntity<Student> updatePlan(@PathVariable String id, @RequestBody Student updatedData) {
        Student updated = studentService.updateStudent(id, updatedData);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable String id) {
        boolean deleted = studentService.deleteStudent(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // -------------------- Badge Endpoints --------------------

    // Create a new badge
    @PostMapping("/badges")
    public ResponseEntity<Badge> addBadge(@RequestBody Badge badge) {
        Badge savedBadge = badgeService.addBadge(badge);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBadge);
    }

    // Get all badges
    @GetMapping("/badges")
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> badges = badgeService.getAllBadges();
        return ResponseEntity.ok(badges);
    }

    // Claim a badge (custom action on badge resource)
    @PutMapping("/badges/{id}/claim")
    public ResponseEntity<String> claimBadge(@PathVariable String id) {
        boolean success = badgeService.claimBadge(id);
        if (success) {
            return ResponseEntity.ok("Badge claimed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Badge not found or already claimed.");
        }
    }
}
