package com.example.boothibernate.dao;

import com.example.boothibernate.domain.hr.Employee;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import java.util.List;

@Repository
public class HibernateEmployeeDao implements EmployeeDao {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee get(Long id) {
        return null;
    }

    @Override
    public void create(Employee employee) {

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(Employee employee) {

    }
}
