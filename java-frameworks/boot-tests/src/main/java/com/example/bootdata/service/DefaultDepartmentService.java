package com.example.bootdata.service;

import com.example.bootdata.dao.DepartmentJpaRepository;
import com.example.bootdata.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultDepartmentService implements DepartmentService {

    private final DepartmentJpaRepository departmentJpaRepository;

    @Override
    public List<Department> findAll() {
        return departmentJpaRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentJpaRepository.findById(id);
    }

    @Override
    public List<Department> findAllInRange(Integer from, Integer to, Sort id){
        return departmentJpaRepository.findAllInRange(from, to, id);
    }

    @Override
    public List<Department> getByIdInOrderPageable(Pageable pageable) {
        return departmentJpaRepository.findAll(pageable).stream().toList();
    }
}
