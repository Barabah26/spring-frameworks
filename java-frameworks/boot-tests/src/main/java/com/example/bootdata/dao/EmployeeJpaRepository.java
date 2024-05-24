package com.example.bootdata.dao;

import com.example.bootdata.domain.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    @Override
//    @EntityGraph(attributePaths = {"manager", "department", "jobs"})
    @EntityGraph("employeesFull")
    Optional<Employee> findById(Integer integer);
    //@Query("select e.firstName, c.name from Employee e join fetch Department d join fetch Location l join fetch Country c where c.name = "UA)
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    @EntityGraph("employeesFull")
    @Query("select e from Employee e where e.id in :id")
//    @Query(value = "select * from employees e where e.employee_id in (:id)", nativeQuery = true)
    Employee findByIdIn(Integer id);

    //@EntityGraph("employeesFull")
    @Query("select e from Employee e")
    List<Employee> findAllFull();

//    @EntityGraph("employeesFull")
//    Page<Employee> findAll(Pageable pageable);

//    @EntityGraph("employeesFull")
//    List<Employee> findAllExample(Example<Employee> example);
}
