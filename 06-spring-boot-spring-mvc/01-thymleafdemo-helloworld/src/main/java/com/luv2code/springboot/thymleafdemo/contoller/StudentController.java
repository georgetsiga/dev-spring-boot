package com.luv2code.springboot.thymleafdemo.contoller;

import com.luv2code.springboot.thymleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;
    @Value("${ossystems}")
    private List<String> ossystems;

    @GetMapping("/student")
    public String showForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        model.addAttribute("ossystems", ossystems);
        return "student-form";
    }

    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute("student") Student student) {
        System.out.printf("Student" + student.toString());
        return "student-confirmation";
    }
}
