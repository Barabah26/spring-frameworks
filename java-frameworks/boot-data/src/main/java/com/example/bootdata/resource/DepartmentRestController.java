package com.example.bootdata.resource;

import com.example.bootdata.domain.Department;
import com.example.bootdata.domain.dto.DepartmentDto;
import com.example.bootdata.domain.dto.DepartmentDtoWithEmployees;
import com.example.bootdata.service.DepartmentDtoMapper;
import com.example.bootdata.service.DepartmentDtoWithEmployeesMapper;
import com.example.bootdata.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentRestController {
    private final DepartmentService departmentService;
    private final DepartmentDtoMapper departmentDtoMapper;
    private final DepartmentDtoWithEmployeesMapper departmentDtoWithEmployeesMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        Optional<Department> department = departmentService.findById(id);
        if (department.isEmpty()){
            return ResponseEntity.badRequest().body(String.format("Not found department by %d", id));
        }

        DepartmentDto departmentDto = departmentDtoMapper.convertToDto(department.get());
        return ResponseEntity.ok(departmentDto);

    }

    @GetMapping("/{from}/{to}")
    public List<DepartmentDto> getByIdInOrder(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
        return departmentService.findAllInRange(from, to, Sort.by(new Sort.Order(Sort.Direction.ASC, "id")))
                .stream().map(departmentDtoMapper::convertToDto)
                .toList();

    }

    @GetMapping
    public List<DepartmentDtoWithEmployees> getByIdInOrderPageable(Pageable pageable) {
        Sort sort = Sort.by(Sort.Order.asc("departmentName"), Sort.Order.asc("id"));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return departmentService.getByIdInOrderPageable(pageable).stream()
                .map(departmentDtoWithEmployeesMapper::convertToDto)
                .toList();

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exceptionHandler(Exception ex){
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
