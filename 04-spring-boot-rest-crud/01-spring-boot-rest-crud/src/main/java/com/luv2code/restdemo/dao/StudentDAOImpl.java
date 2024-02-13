package com.luv2code.restdemo.dao;

import com.luv2code.restdemo.models.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery = entityManager.createQuery("FROM Student", Student.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> typedQuery = entityManager.createQuery("FROM Student WHERE lastName=:lName", Student.class);
        typedQuery.setParameter("lName", theLastName);
        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public Student updateStudent(Student student) {
        return entityManager.merge(student);
    }

    @Transactional
    @Override
    public void deleteStudent(Integer id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    @Transactional
    @Override
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }
}
