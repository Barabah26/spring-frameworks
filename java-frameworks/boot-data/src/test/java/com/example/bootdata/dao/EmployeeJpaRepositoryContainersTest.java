package com.example.bootdata.dao;

import com.example.bootdata.domain.Employee;
import jakarta.transaction.Transactional;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class EmployeeJpaRepositoryContainersTest extends ContainersTest {

    @Autowired
    private EmployeeJpaRepository repository;

    @Test
    public void testFindAll() {
        List<Employee> employees = repository.findAll();
        assertNotNull(employees);
        assertEquals(10, employees.size());
    }

    @Test
    @Transactional
    public void testCreate( ) {
        Employee newEmployee = new Employee();
        newEmployee.setLastName("e");
        newEmployee.setEmail("e");
        newEmployee.setPhoneNumber("12");
        newEmployee.setSalary(BigDecimal.TEN);
        newEmployee.setHireDate(new Date());
        newEmployee.setCreationDate(new Date());
        newEmployee.setJobs(null);
        Employee savedEmployee = repository.save(newEmployee);

        List<Employee> employees = repository.findAll();
        List<Employee> employees1 = repository.findAllFull();
        System.out.println(employees.size());
        System.out.println(employees1.size());
        assertNotNull(employees);

        System.out.println("\n\n\n\n ++++++++++++++++++++++++");
        Optional<Employee> readerEmployee = repository.findById(savedEmployee.getId());
        assertEquals("e", readerEmployee.get().getLastName());


        assertEquals(11, employees.size());
        System.err.println(readerEmployee);
    }
}