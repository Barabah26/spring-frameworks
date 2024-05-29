package com.example.springsecurity.controller;

import com.example.springsecurity.domain.SysRole;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserDetailsServiceImpl;
import com.example.springsecurity.service.UserDtoMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @TestConfiguration
    public static class TestConfig {
        @Bean // Тестируем компонентно с меппером
        public UserDtoMapper employeeDtoMapper() {
            return new UserDtoMapper();
        }
    }

    @BeforeEach
    public void setUp() {
        SysUser user = new SysUser(1L, "1", "1", true, Set.of(new SysRole(1L, "USER", null)));
        when(userDetailsService.findAll()).thenReturn(List.of(user));
    }

    @Test
    @WithMockUser(value = "user1")
    public void testGetAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sysRoles", Matchers.is("USER")));
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