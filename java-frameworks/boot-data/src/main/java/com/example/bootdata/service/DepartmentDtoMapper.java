package com.example.bootdata.service;

import com.example.bootdata.domain.Department;
import com.example.bootdata.domain.Employee;
import com.example.bootdata.domain.dto.DepartmentDto;
import com.example.bootdata.domain.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepartmentDtoMapper extends DtoMapperFacade<Department, DepartmentDto> {
    public DepartmentDtoMapper() {
        super(Department.class, DepartmentDto.class);
    }

    @Override
    protected void decorateDto(DepartmentDto dto, Department entity) {
        if (entity.getManager() != null){
           dto.setManagerName(entity.getManager().getFirstName());
        }
    }

}
