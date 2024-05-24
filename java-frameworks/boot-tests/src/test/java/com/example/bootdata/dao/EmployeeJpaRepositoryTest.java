package com.example.bootdata.dao;

import com.example.bootdata.domain.Employee;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeJpaRepositoryTest {

    @Autowired
    private EmployeeJpaRepository repository;

    @Test
    public void testFindAll() {
        List<Employee> employees = repository.findAll();
        assertNotNull(employees);
        assertEquals(10, employees.size());
    }

    @Test
    public void testCreate( ){
        Employee newEmployee = new Employee();
        newEmployee.setLastName("e");
        newEmployee.setEmail("e");
        newEmployee.setPhoneNumber("12");
        newEmployee.setSalary(BigDecimal.TEN);
        newEmployee.setHireDate(new Date());
        newEmployee.setCreationDate(new Date());
        newEmployee.setJobs(null);
        Employee savedEmployee = repository.save(newEmployee);

        System.out.println("\n\n\n\n ++++++++++++++++++++++++");
        Optional<Employee> readerEmployee = repository.findById(savedEmployee.getId());
        assertEquals("e", readerEmployee.get().getLastName());

        List<Employee> employees = repository.findAll();
        assertNotNull(employees);
        assertEquals(11, employees.size());
        System.err.println(readerEmployee);
    }
}