package com.example.bootjdbc.service;

import com.example.bootjdbc.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(Long userId);

    void update(Employee employee);
    void delete(long id);
    void create(Employee employee);
    List<Employee> getAllEmployeesByDepartmentName(String departmentName);
}
