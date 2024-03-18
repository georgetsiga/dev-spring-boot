package com.luv2code.springboot.thymeleafedemo.dao;

import com.luv2code.springboot.thymeleafedemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    //sort by lastname
    public List<Employee> findAllByOrderByLastNameAsc();

}
