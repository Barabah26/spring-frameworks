package com.example.boothibernate.service;


import com.example.boothibernate.domain.hr.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(Long userId);

    void update(Employee employee);
    void delete(long id );
}
