package com.example.boothibernate.service;

import com.example.boothibernate.dao.EmployeeDao;
import com.example.boothibernate.domain.hr.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class DefaultEmployeeService implements EmployeeService {
    private EmployeeDao employeeDao;

    public DefaultEmployeeService(EmployeeDao hibernateEmployeeDao) { // List<UserDao> dao - inject all deps
        this.employeeDao = hibernateEmployeeDao;
    }

    public void setUserDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = NoSuchElementException.class, timeout = 1000)
    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getById(Long userId) {
        return employeeDao.get(userId);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);

    }

    @Override
    public void delete(long id) {
        employeeDao.delete(employeeDao.get(id));

    }

}
