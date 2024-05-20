package com.example.bootdata.resource;

import com.example.bootdata.dao.EmployeeJpaRepository;
import com.example.bootdata.dao.EmployeeRepository;
import com.example.bootdata.domain.Employee;
import com.example.bootdata.domain.dto.EmployeeDto;
import com.example.bootdata.service.EmployeeDtoMapper;
import com.example.bootdata.service.EmployeeService;
//import com.example.bootdata.task.DepartmentJpaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeRestController.class)
// Содержимое главной конфигурации должно быть чисто в ApplicationRunner
// Конфликтует с аудитом
public class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    // Объявлены в SpringBootApplication
    @MockBean
    private EmployeeJpaRepository employeeJpaRepository;
    @MockBean
    private EmployeeRepository employeeRepository;
//    @MockBean
//    private DepartmentJpaRepository departmentJpaRepository;

    @MockBean
    private EmployeeDtoMapper employeeDtoMapper;

    @Test
    public void getById() {

    }

    @Test
    public void getAll() throws Exception {
        Employee employee = new Employee();

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(42L);
        employeeDto.setFirstName("Kris");

        when(employeeService.getAll()).thenReturn(List.of(employee));
        when(employeeDtoMapper.convertToDto(employee)).thenReturn(employeeDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(42)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fn", Matchers.is("Kris")));
    }
}