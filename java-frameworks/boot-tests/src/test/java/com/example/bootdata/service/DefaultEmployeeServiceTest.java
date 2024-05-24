package com.example.bootdata.service;

import com.example.bootdata.dao.EmployeeJpaRepository;
import com.example.bootdata.dao.EmployeeRepository;
import com.example.bootdata.domain.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultEmployeeServiceTest {

    @Mock
    private EmployeeJpaRepository employeeJpaRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DefaultEmployeeService employeeService;

    @Captor
    private ArgumentCaptor<Employee> employeeArgumentCaptor;

    @Test
    public void testGetAllPageble() {
        Employee employee = new Employee();
        when(employeeJpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(employee)));
        List<Employee> employees = employeeService.getAllPageble(1, 2);

        assertEquals(employee, employees.get(0));
    }

    @Test
    public void test_GetAll_Success() {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employeesExpected = List.of(employee1, employee2);
        when(employeeRepository.findAll())
                .thenReturn(new PageImpl<>(employeesExpected));

        List<Employee> employeesActual = employeeService.getAll();
        assertNotNull(employeesActual);
        assertFalse(employeesActual.isEmpty());
        assertIterableEquals(employeesExpected, employeesActual);
    }

    @Test
    public void test_Create_Success() {
        Employee employee1 = new Employee();

        employeeService.create(employee1);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee employeeActualArgument = employeeArgumentCaptor.getValue();
        assertEquals(employee1, employeeActualArgument);
    }

    @Test
    public void testGetByIdEncoded() {
        Employee testEmployee = new Employee();
        String lastName = "Black";
        testEmployee.setLastName(lastName);
        when(employeeRepository.findById(100)).thenReturn(Optional.of(testEmployee));
        Employee employee = employeeService.getByIdEncoded(100).get();

        assertEquals(testEmployee.getLastName(), employee.getLastName());
    }
}