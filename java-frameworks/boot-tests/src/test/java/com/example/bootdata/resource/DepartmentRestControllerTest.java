package com.example.bootdata.resource;

import com.example.bootdata.dao.DepartmentJpaRepository;
import com.example.bootdata.domain.Department;
import com.example.bootdata.domain.Employee;
import com.example.bootdata.domain.dto.DepartmentDto;
import com.example.bootdata.service.DefaultDepartmentService;
import com.example.bootdata.service.DepartmentDtoMapper;
import com.example.bootdata.service.DepartmentDtoWithEmployeesMapper;
import com.example.bootdata.service.DepartmentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentRestController.class)
public class DepartmentRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefaultDepartmentService departmentService;

    @MockBean
    private DepartmentDtoMapper departmentDtoMapper;

    @MockBean
    private DepartmentDtoWithEmployeesMapper dtoWithEmployeesMapper;

    @Test
    public void testFindAll() throws Exception {
        List<Department> departments = new ArrayList<>();
        Department first = new Department();
        first.setId(1);
        String depName1 = "first";
        first.setDepartmentName(depName1);
        Employee manager = new Employee();
        String name = "Alex";
        manager.setFirstName(name);
        first.setManager(manager);
        Department second = new Department();
        String depName2 = "first";
        second.setDepartmentName(depName2);
        second.setId(32);
        Employee manager2 = new Employee();
        String name2 = "Pavlo";
        manager2.setFirstName(name2);
        second.setManager(manager);
        departments.add(first);
        departments.add(second);
        when(departmentService.findAll()).thenReturn(departments);
        DepartmentDto dtoFirst = new DepartmentDto(1, depName1, name);
        DepartmentDto dtoSecond = new DepartmentDto(32, depName2, name2);
        when(departmentDtoMapper.convertToDto(first)).thenReturn(dtoFirst);
        when(departmentDtoMapper.convertToDto(second)).thenReturn(dtoSecond);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is(depName1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].managerName", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(32)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is(depName2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].managerName", Matchers.is(name2)));
    }

}