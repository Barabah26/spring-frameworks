package com.example.boothibernate.dao;

import com.example.boothibernate.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    List<Student> findAll();

    Student get(Long id);

    void create(Student student);

    void update(Student student);

    void delete(Long id);


    Optional<List<Student>> getAllByIdGroup(Long groupId);
}
