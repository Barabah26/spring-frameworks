package com.example.bootjdbc.resource;

import com.example.bootjdbc.domain.Employee;
import com.example.bootjdbc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Slf4j
public class RestEmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> getById(@PathVariable("id") String userId) {
//        if (employeeService.getById(Long.parseLong(userId)) == null){
//            return ResponseEntity.badRequest().body("User not found");
//        }
//        return ResponseEntity.ok(employeeService.getById(Long.parseLong(userId))) ;
        try {
            return ResponseEntity.ok(employeeService.getById(Long.parseLong(userId))) ;
        } catch (RuntimeException e){
            log.error("User not found", e);
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteById(@PathVariable("id") String userId) {
//        employeeService.delete(Long.parseLong(userId));
        try {
            employeeService.delete(Long.parseLong(userId));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            log.error("User not found", e);
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @PutMapping()
    public void updateById(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @PostMapping
    public void createEmployee(@RequestBody Employee employee){employeeService.create(employee);}

    @GetMapping("/department/{name}")
    public ResponseEntity <?> getAllEmployeesByDepartmentName(@PathVariable("name") String departmentName) {
//        if (employeeService.getById(Long.parseLong(userId)) == null){
//            return ResponseEntity.badRequest().body("User not found");
//        }
//        return ResponseEntity.ok(employeeService.getById(Long.parseLong(userId))) ;
        try {
            return ResponseEntity.ok(employeeService.getAllEmployeesByDepartmentName(departmentName));
        } catch (RuntimeException e){
            log.error("Department not found", e);
            return ResponseEntity.badRequest().body("Department not found");
        }
    }

}
