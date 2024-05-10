package com.example.bootjdbc.dao;

import com.example.bootjdbc.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateEmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM TBL_EMPLOYEES",
                (rs, romNum) ->
                        new Employee(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("email")
                                ));
    }

    @Override
    public Employee get(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM TBL_EMPLOYEES WHERE id=?",
                new BeanPropertyRowMapper<>(Employee.class), id);
    }

    @Override
    public void create(Employee employee) {
        String sql = "INSERT INTO TBL_EMPLOYEES(name, surname, email) VALUES(?,?,?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getSurname(), employee.getEmail());

    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE TBL_EMPLOYEES SET id=?, name=?, surname=?, email=?";
        jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail());
    }

    @Override
    public void delete(Employee employee) {
        long employeeId = employee.getId();
        jdbcTemplate.execute(String.format("DELETE FROM TBL_EMPLOYEES WHERE id = %d", employeeId));

    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentName(String departmentName) {
        String sql = "SELECT e.* FROM TBL_EMPLOYEES e JOIN DEPARTMENT d ON e.department_id = d.id WHERE d.name = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), departmentName);
    }
}
