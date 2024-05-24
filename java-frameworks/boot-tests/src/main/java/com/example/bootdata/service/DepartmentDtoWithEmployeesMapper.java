package com.example.bootdata.service;

import com.example.bootdata.domain.Department;
import com.example.bootdata.domain.Employee;
import com.example.bootdata.domain.dto.DepartmentDtoWithEmployees;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DepartmentDtoWithEmployeesMapper extends DtoMapperFacade<Department, DepartmentDtoWithEmployees>{
    public DepartmentDtoWithEmployeesMapper() {
        super(Department.class, DepartmentDtoWithEmployees.class);
    }

    @Override
    protected void decorateDto(DepartmentDtoWithEmployees dto, Department entity) {
        if (entity.getManager() != null){
            dto.setEmployeeNames(entity.getEmployees().stream().map(Employee::getFirstName).toList());
        } else {
            dto.setEmployeeNames(new ArrayList<>());
        }
    }
}
