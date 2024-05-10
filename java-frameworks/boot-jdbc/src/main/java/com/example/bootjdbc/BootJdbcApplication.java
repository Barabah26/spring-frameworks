package com.example.bootjdbc;

import com.example.bootjdbc.domain.Employee;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableTransactionManagement
public class BootJdbcApplication implements ApplicationRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(BootJdbcApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("http://localhost:9000/h2-console");
        System.out.println("JDBC Url: jdbc:h2:mem:testdb" );
        System.out.println("http://localhost:9000/swagger-ui/index.html");

        List<Employee> employeesAll = jdbcTemplate.query(
                "SELECT * FROM TBL_EMPLOYEES",
                (rs, rowNum) -> new Employee(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email")));

        System.out.println("Emps all: " + employeesAll);

        String surname = jdbcTemplate.queryForObject(
                "SELECT surname FROM TBL_EMPLOYEES WHERE email = ? ",
                String.class,
                "xyz@email.com"
        );
        System.out.println(surname);

        List<Employee> employees = jdbcTemplate.query(
                "SELECT * FROM TBL_EMPLOYEES",
                new BeanPropertyRowMapper<>(Employee.class));

        System.out.println("Bean property: " + employees);

        employees = jdbcTemplate.queryForStream(
                        "SELECT * FROM TBL_EMPLOYEES",
                        new BeanPropertyRowMapper<>(Employee.class))
                .collect(Collectors.toList());

        Integer total = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM TBL_EMPLOYEES",
                Integer.class);
        System.out.println(total);

        Employee employee = jdbcTemplate.queryForObject(
                "SELECT * FROM TBL_EMPLOYEES WHERE id=?",
                new BeanPropertyRowMapper<>(Employee.class),
                1
        );

        int deleted = jdbcTemplate.update(
                "DELETE FROM TBL_EMPLOYEES WHERE id = ?",
                4);
        System.out.println("Deleted: " + deleted);

        int updated = jdbcTemplate.update(
                "UPDATE TBL_EMPLOYEES SET id = ?, name = ?, surname = ?, email = ? WHERE id = ?",
                employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail(), employee.getId());
        System.out.println("Updated: " + updated);

    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("EIS API")
                        .description("Employee Information System sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .description("SpringShop Wiki Documentation")
                        .contact(new Contact().email("test@test.com").url("http://fullstackcode.dev")))
                ;
    }
}
