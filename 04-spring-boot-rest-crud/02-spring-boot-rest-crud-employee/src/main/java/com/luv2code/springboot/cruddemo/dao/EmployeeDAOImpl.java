package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.rest.EmployeeNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> typedQuery = entityManager.createQuery("FROM Employee", Employee.class);
        return typedQuery.getResultList();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return findEmployee(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        Employee employee = findEmployee(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with id: " + id + " not found");
        }
        entityManager.remove(employee);
    }

    private Employee findEmployee(Integer id) {
        return entityManager.find(Employee.class, id);
    }
}
