package com.example.bootdata.domain.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDtoWithEmployees {
    private String departmentName;
    private List<String> employeeNames;

}
