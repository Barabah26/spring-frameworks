package com.example.bootjdbc.service;

import com.example.bootjdbc.dao.EmployeeDao;
import com.example.bootjdbc.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class DefaultEmployeeService implements EmployeeService {

    private EmployeeDao employeeDao;

    public DefaultEmployeeService(EmployeeDao templateEmployeeDao) { // List<UserDao> dao - inject all deps
        this.employeeDao = templateEmployeeDao;
    }

    public void setUserDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = NoSuchElementException.class, timeout = 1000, propagation = Propagation.NEVER)
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
        Employee newEmployee = new Employee(id, null, null, null);
        employeeDao.delete(newEmployee);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentName(String departmentName) {
        return employeeDao.getAllEmployeesByDepartmentName(departmentName);
    }
}
