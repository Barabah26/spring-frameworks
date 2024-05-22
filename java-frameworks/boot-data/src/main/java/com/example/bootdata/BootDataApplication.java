package com.example.bootdata;

import com.example.bootdata.config.AppProperties;
import com.example.bootdata.dao.EmployeeJpaRepository;
import com.example.bootdata.dao.EmployeeRepository;
import com.example.bootdata.domain.Employee;
import com.example.bootdata.domain.dto.EmployeeDto;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.bootdata.dao.EmployeeSpecification.employeeByPhoneNumber;
import static com.example.bootdata.dao.EmployeeSpecification.employeeLastNameStartsWith;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

//@EnableJpaAuditing // TODO tests comment
@EnableTransactionManagement // TODO tests comment
@SpringBootApplication
        //@EnableConfigurationProperties(AppProperties.class)
public class BootDataApplication implements ApplicationRunner {
//    @Autowired
//    AppProperties appProperties;
    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(BootDataApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        mapper.createTypeMap(Employee.class, EmployeeDto.class)
                .addMapping(Employee::getFirstName, EmployeeDto::setFirstName)
                .addMapping(Employee::getLastName, EmployeeDto::setLastName);
        return mapper;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        System.out.println("http://localhost:9000/swagger-ui/index.html \n");
//        System.out.println(appProperties);
        // Commented for tests run

        findEmployeesByNameAndDept();
/*        Optional<Employee> employee = employeeRepository.findById(101);
        System.out.println(employee);

        Optional<Employee> steven = employeeJpaRepository.findById(100);
        steven.get().setPhoneNumber("515.123.45677");
        System.out.println(steven);

        getAllPageable(2, 2);*/
/*        Iterable<Employee> allById = employeeRepository.findAllById(List.of(100, 101, 102));
        for (Employee employee : allById) {
            System.out.println(employee);
        }*/

/*        Optional<Department> department = departmentJpaRepository.findOne(10L);
        System.out.println(department.get().getManager().getFirstName());
        //findEmployeesByNameAndDept();
        List<Employee> employees = getAllPageable(5, 0);
        System.out.println(employees.size());
        System.out.println(employees);

        createNewEmployeeWithAuditByExists();*/
        //findEmployeesByNameAndDept();

//        System.out.println(employeeJpaRepository.findByIdIn(100));

//        getByFirstnameAndLast();


//        createNewEmployeeWithAuditByExists();

//        findBySpecification();
    }

    private void findEmployeesByNameAndDept() {
        // Получает сотрудников с именем Jack и департаментом IT
        Employee employee = new Employee();
        employee.setFirstName("John");
//        employee.setLastName("Lee");
        Example<Employee> employeeExample = Example.of(employee);
        List<Employee> employees = employeeJpaRepository.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public List<Employee> getAllPageable(int size, int pageNumber) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "lastName"));
        Pageable pageable = PageRequest.of(pageNumber, size, sort);
        Page<Employee> employeePage = employeeJpaRepository.findAll(pageable);
        return employeePage.toList();
    }

    private void findBySpecification() {
        employeeJpaRepository.findAll(employeeLastNameStartsWith("Steven"))
                .forEach(System.out::println);

        employeeJpaRepository.findAll(employeeByPhoneNumber("515.123.4567"))
                .forEach(System.out::println);

        employeeJpaRepository.findAll(Specification.where(employeeLastNameStartsWith("Steven"))
                        .or(employeeByPhoneNumber("515.123.4567")))
                .forEach(System.out::println);
    }

    private void createNewEmployeeWithAuditByExists() {
        Employee employee = employeeJpaRepository.findById(100).get();
        Employee newEmployee = new Employee();
        newEmployee.setLastName("e1");
        newEmployee.setEmail("e1");
        newEmployee.setPhoneNumber("12");
        newEmployee.setSalary(BigDecimal.TEN);
        newEmployee.setHireDate(new Date());
        newEmployee.setJobs(employee.getJobs());

        employeeJpaRepository.save(newEmployee);


//        EmployeeDto employeeDto = employeeDtoMapper.convertToDto(employee);
//        System.out.println(employeeDto);
    }

    private void getByFirstnameAndLast() {
        Optional<Employee> employee = employeeJpaRepository.findByFirstNameAndLastName("Steven", "King");
        employee.ifPresentOrElse(System.out::println, () -> System.err.println("errr"));
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
