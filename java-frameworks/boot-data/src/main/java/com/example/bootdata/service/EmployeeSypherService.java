package com.example.bootdata.service;

import com.example.bootdata.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EmployeeSypherService {
    public Employee encrypt(Employee employee) {
        employee.setLastName(new String(Base64.getEncoder().encode(employee.getLastName().getBytes())));
        return employee;
    }
}
