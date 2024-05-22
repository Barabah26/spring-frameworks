package com.example.bootdata.dao;

import com.example.bootdata.domain.Department;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentJpaRepository extends JpaRepository<Department, Integer> {

    @EntityGraph(attributePaths = {"manager"})
    Optional<Department> findById(Integer id);

    @EntityGraph(attributePaths = {"manager"})
    @Query("SELECT d FROM Department d WHERE d.id BETWEEN :from AND :to")
    List<Department> findAllInRange(Integer from, Integer to, Sort id);

    @EntityGraph(attributePaths = {"employees"})
    @Override
    Page<Department> findAll(Pageable pageable);

}
