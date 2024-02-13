package com.luv2code.restdemo.rest;

import com.luv2code.restdemo.dao.StudentDAO;
import com.luv2code.restdemo.models.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentDAO.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudents(@PathVariable int studentId) {
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw  new StudentNotFoundException("Student with id: " + studentId + " not found");
        }
        return student;
    }
}
