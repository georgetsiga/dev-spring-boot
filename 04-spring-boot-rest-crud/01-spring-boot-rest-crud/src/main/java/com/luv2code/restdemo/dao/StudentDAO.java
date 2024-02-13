package com.luv2code.restdemo.dao;

import com.luv2code.restdemo.models.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String theLastName);
    Student updateStudent(Student student);
    void deleteStudent(Integer id);
    int deleteAll();
}
