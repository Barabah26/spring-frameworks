package com.example.boothibernate.resource;

import com.example.boothibernate.domain.hr.Employee;
import com.example.boothibernate.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestEmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable("id") String userId) {
        return null;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteById(@PathVariable("id") String userId) {

    }

    @PutMapping("/employees")
    public void updateById(@RequestBody Employee employee) {

    }
}
