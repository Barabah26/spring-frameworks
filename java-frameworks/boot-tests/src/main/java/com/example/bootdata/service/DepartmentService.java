package com.example.bootdata.service;

import com.example.bootdata.domain.Department;
import com.example.bootdata.domain.dto.DepartmentDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();
    Optional<Department> findById(Integer id);

    List<Department> findAllInRange(Integer from, Integer to, Sort id);

    List<Department> getByIdInOrderPageable(Pageable pageable);
}
