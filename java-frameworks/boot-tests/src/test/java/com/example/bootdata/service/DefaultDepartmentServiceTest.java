package com.example.bootdata.service;

import com.example.bootdata.dao.DepartmentJpaRepository;
import com.example.bootdata.dao.EmployeeJpaRepository;
import com.example.bootdata.dao.EmployeeRepository;
import com.example.bootdata.domain.Department;
import com.example.bootdata.domain.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultDepartmentServiceTest {
    @Mock
    private DepartmentJpaRepository departmentJpaRepository;


    @InjectMocks
    private DefaultDepartmentService departmentService;

    @Captor
    private ArgumentCaptor<Department> departmentArgumentCaptor;

    @Test
    void findById(){
        Department department = new Department();
        when(departmentJpaRepository.findById(0)).thenReturn(Optional.of(department));
        Department currentDepartments = departmentJpaRepository.findById(0).get();
        assertEquals(department, currentDepartments);
    }

}