package com.example.boothibernate.service;

import com.example.boothibernate.dao.StudentDao;
import com.example.boothibernate.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultStudentService implements StudentService {


    private final StudentDao studentDao;

    @Override
    public List<Student> findAll() {

        return studentDao.findAll();
    }

    @Override
    public Student get(Long id) {

        return studentDao.get(id);
    }

    @Override
    public void create(Student student) {

        studentDao.create(student);
    }

    @Override
    public void update(Student student) {

        studentDao.update(student);
    }

    @Override
    public void delete(Long id) {
        studentDao.delete(id);
    }

    @Override
    public Optional<List<Student>> getAllByGroupId(Long groupId) {
        return studentDao.getAllByIdGroup(groupId);
    }
}
