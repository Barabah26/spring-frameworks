package com.example.bootdata.dao;

import com.example.bootdata.domain.Employee;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeSpecification {
    public static Specification<Employee> employeeLastNameStartsWith(final String lastName) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("lastName"), lastName + "%");
            }
        };
    }

    public static Specification<Employee> employeeByPhoneNumber(final String phoneNumber) {
        return (r, cq, cb) -> cb.equal(r.get("phoneNumber"), phoneNumber);
    }
}
