package com.example.bootjdbc.dao;

import com.example.bootjdbc.domain.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();

    Employee get(Long id);

    void create(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);
    List<Employee> getAllEmployeesByDepartmentName(String departmentName);
}
