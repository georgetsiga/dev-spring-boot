package com.luv2ocode.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @Value("${person.name}")
    private String personName;

    @GetMapping("/havefun")
    public String getHello() {
        return "Hello " + personName + "!!";
    }

    @GetMapping("/workout")
    public String getWorkOut() {
        return "Hello " + personName + "!! Time to workout";
    }
}
