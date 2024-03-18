package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;
    @Override
    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
        if (null != theCode){
            return theCode.startsWith(coursePrefix);
        } else {
            return false;
        }

    }
}
