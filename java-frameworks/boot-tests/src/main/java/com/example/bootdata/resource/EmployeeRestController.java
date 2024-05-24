package com.example.bootdata.resource;

import com.example.bootdata.domain.Employee;
import com.example.bootdata.domain.dto.EmployeeDto;
import com.example.bootdata.service.EmployeeDtoMapper;
import com.example.bootdata.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {
    private final EmployeeService employeeService;
    private final EmployeeDtoMapper dtoMapper;

    public EmployeeRestController(EmployeeService employeeService, EmployeeDtoMapper employeeDtoMapper) {
        this.employeeService = employeeService;
        this.dtoMapper = employeeDtoMapper;
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.getAll().stream()
                .map(dtoMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<EmployeeDto> getById(@PathVariable("id") String userId) {
        return employeeService.getById(Integer.parseInt(userId))
                .map(dtoMapper::convertToDto);
    }

    @PostMapping
    public void create(@Valid @RequestBody EmployeeDto dto) {
        Employee employee = dtoMapper.convertToEntity(dto);
        employeeService.create(employee);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }


}
