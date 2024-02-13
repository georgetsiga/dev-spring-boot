package com.luv2code.springboot.thymleafdemo.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class DemoController {

    //create a mapping foe "/hello"
    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("theDate", new Date());
        return "helloworld";
    }
}
