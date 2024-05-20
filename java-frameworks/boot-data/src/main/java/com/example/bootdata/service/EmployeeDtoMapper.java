package com.example.bootdata.service;

import com.example.bootdata.domain.dto.EmployeeDto;
import com.example.bootdata.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeDtoMapper extends DtoMapperFacade<Employee, EmployeeDto> {
    public EmployeeDtoMapper() {
        super(Employee.class, EmployeeDto.class);
    }

    @Override
    protected void decorateDto(EmployeeDto dto, Employee entity) {
        dto.setCreationDate(new Date());
    }
}
