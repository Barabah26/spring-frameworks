package com.example.bootdata.service;

import com.example.bootdata.domain.Department;
import com.example.bootdata.domain.dto.DepartmentDto;
import org.springframework.stereotype.Service;

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
