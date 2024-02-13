package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer id);
    Employee saveEmployee(Employee employee);
    void deleteEmployeeById(Integer id);
}
