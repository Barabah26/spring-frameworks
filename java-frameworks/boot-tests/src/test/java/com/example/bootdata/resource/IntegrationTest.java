package com.example.bootdata.resource;

import com.example.bootdata.dao.EmployeeJpaRepository;
import com.example.bootdata.domain.Employee;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.yml")
@AutoConfigureMockMvc
// Содержимое главной конфигурации должно быть чисто ApplicationRunner
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Test
    public void getById() {

    }

    @Test
    public void testGetAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fn", Matchers.is("Steven")));
    }

    @Test
    public void testPutGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": 11,
                                    "email": "steven@gmail.com",
                                    "phoneNumber": "+38093100111",
                                    "hireDate": "17-06-1987 00:00:00",
                                    "managerFirstName": "",
                                    "jobs":{"id":"AD_PRES","jobTitle":"President"},
                                    "departmentName": "Executive",
                                    "salary": 12,
                                    "fn": "Steven",
                                    "ln": "King2"
                                }
                                """)
                        .header("Authorization", ""))
                .andExpect(status().isOk());

        System.out.println(employeeJpaRepository.findAll().stream().map(Employee::getId).collect(Collectors.toList()));
        Optional<Employee> employee = employeeJpaRepository.findById(110);
        System.out.println(employee);
        Thread.sleep(1000); // Need to wait until data will store to DB

        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/110"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(110)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fn", Matchers.is("Steven")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ln", Matchers.is("King2")));
    }
}