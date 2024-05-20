package com.example.bootdata.service;

import com.example.bootdata.domain.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();

    @Transactional(readOnly = true)
    List<Employee> getAllPageble(int size, int pageNumber);

    Optional<Employee> getById(Integer userId);

    @Transactional(readOnly = true)
    Optional<Employee> getByIdEncoded(Integer userId);

    void update(Employee employee);
    Employee create(Employee employee);
    void delete(Integer id );
}
