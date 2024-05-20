package com.example.bootdata.service;

import com.example.bootdata.dao.EmployeeRepository;
import com.example.bootdata.domain.Employee;
import com.example.bootdata.dao.EmployeeJpaRepository;
import jakarta.transaction.NotSupportedException;
import javax.naming.OperationNotSupportedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultEmployeeService implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeJpaRepository employeeJpaRepository;

    private final EmployeeSypherService employeeSypherService;

    @Override
    public List<Employee> getAll() {
//        Iterable<Employee> employees = employeeRepository.findAll();
//        List<Employee> currentEmployees = new ArrayList<>();
//        for (Employee employee: employees){
//            currentEmployees.add(employee);
//        }
//        return currentEmployees;
        return employeeJpaRepository.findAll();
    }

    @Override
    public List<Employee> getAllPageble(int size, int pageNumber) {
        throw new NotImplementedException();
    }

    public Employee create(Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        return newEmployee;
    }

    @Override
    public Optional<Employee> getById(Integer userId) {
        Employee currentEmployee = employeeRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with this id is absent"));
        return Optional.of(currentEmployee);

    }
    @Override
    public Optional<Employee> getByIdEncoded(Integer userId) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Employee employee) {
        Employee currentEmployee = employeeRepository.save(employee);

    }

    @Override
    public void delete(Integer id) {
        throw new NotImplementedException();
    }

}
