package com.example.bootdata.service;

import com.example.bootdata.dao.EmployeeRepository;
import com.example.bootdata.domain.Employee;
import com.example.bootdata.dao.EmployeeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DefaultEmployeeService implements EmployeeService {
    private final EmployeeJpaRepository employeeJpaRepository;
    private final EmployeeRepository employeeRepository;

    private final EmployeeSypherService employeeSypherService;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        Iterable<Employee> all = employeeRepository.findAll();
        List<Employee> result = new ArrayList<>();
        for (Employee employee : all) {
            result.add(employee);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllPageble(int size, int pageNumber) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "lastName"));
        Pageable pageable = PageRequest.of(pageNumber, size, sort);
        Page<Employee> employeePage = employeeJpaRepository.findAll(pageable);
        return employeePage.toList();
    }

    public void create(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> getById(Integer userId) {
        return employeeRepository.findById(userId);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> getByIdEncoded(Integer userId) {
        return employeeRepository.findById(userId)
                .map(e -> {
                    if (e.getLastName() == null){
                        return e;
                    }
                    e.setLastName(new String(Base64.getEncoder().encode(e.getLastName().getBytes())));
                    return e;
                });
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.save(employee);

    }

    @Override
    public void delete(Integer id) {
        employeeRepository.findById(id).ifPresentOrElse(
                e -> employeeRepository.delete(e),
                () -> log.error("No user with such id for delete = {} ", id)
        );
    }

}
