//package com.example.bootdata.dao;
//
//import com.example.bootdata.domain.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.rest.core.annotation.RestResource;
//
//import java.util.List;
//
//@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
//public interface EmployeeRestRepository extends JpaRepository<Employee, Long> {
//    @Override
////    @EntityGraph(attributePaths = {"manager", "department", "jobs"})
//    List<Employee> findAll();
//
//    @Override
//    @RestResource(exported = false)
//    void deleteById(Long aLong);
//
//}
