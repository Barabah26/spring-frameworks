package com.example.boothibernate.service;

import com.example.boothibernate.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Student get(Long id);

    void create(Student student);

    void update(Student student);


    void delete(Long id);


    Optional<List<Student>> getAllByGroupId(Long groupId);
}
