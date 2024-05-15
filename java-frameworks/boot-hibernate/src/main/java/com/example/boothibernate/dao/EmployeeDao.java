package com.example.boothibernate.dao;


import com.example.boothibernate.domain.hr.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();

    Employee get(Long id);

    void create(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);
}
