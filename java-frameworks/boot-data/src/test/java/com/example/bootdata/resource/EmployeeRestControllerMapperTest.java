package com.example.bootdata.resource;

import com.example.bootdata.dao.EmployeeJpaRepository;
import com.example.bootdata.dao.EmployeeRepository;
import com.example.bootdata.domain.Employee;
import com.example.bootdata.service.EmployeeDtoMapper;
import com.example.bootdata.service.EmployeeService;
//import com.example.bootdata.task.DepartmentJpaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeRestController.class)
// Содержимое главной конфигурации должно быть чисто ApplicationRunner
// Конфликтует с аудитом
public class EmployeeRestControllerMapperTest {

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

    @Autowired
    private EmployeeDtoMapper employeeDtoMapper;

    @TestConfiguration
    public static class TestConfig {
        @Bean // Тестируем компонентно с меппером
        public EmployeeDtoMapper employeeDtoMapper() {
            return new EmployeeDtoMapper();
        }
    }

    @BeforeEach
    public void setUp() {
        Employee employee = new Employee();
        employee.setId(42);
        employee.setFirstName("Kris");

        when(employeeService.getAll()).thenReturn(List.of(employee));
    }

    @Test
    public void getById() {

    }

    @Test
    public void testGetAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                        .param("id", "2")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(42)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fn", Matchers.is("Kris")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ln", Matchers.nullValue()));
    }

    @Test
    public void testCreate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType("application/json")
                        .content(
                        """
                        {
                            "id": 11,
                            "email": "email@gmail.com",
                            "phoneNumber": "+38093100111",
                            "hireDate": "17-06-1987 00:00:00",
                            "managerFirstName": "",
                            "jobs":{"id":"AD_PRES","jobTitle":"President"},
                            "departmentName": "Executive",
                            "salary": 12,
                            "fn": "Bob",
                            "ln": "Marley"
                        }
                        """
                ))
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(42)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fn", Matchers.is("Kris")))
        ;
    }
}