/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.EmployeeRowMapper;
import com.aceade.corpolc.inventory.database.ExtendedEmployeeRowMapper;
import com.aceade.corpolc.inventory.database.Queries;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author philip
 */
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
    
    private static final Logger LOGGER = LogManager.getLogger(EmployeeDaoImpl.class);
    
    @Override
    public Employee getEmployee (long id) {
        String sql = Queries.SELECT_EMPLOYEE;
        // querying for the object directly doesn't work here...but this does
        return (Employee) jdbcTemplate.query(sql, new ExtendedEmployeeRowMapper(), id).get(0);
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        String sql = Queries.SELECT_ALL_EMPLOYEES;
        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return employees;
    }
    
    @Override
    public int getTotalEmployeeCount() {
        String sql = "SELECT COUNT(id) from employees AS count";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public long addEmployee(NewEmployeeRequest newEmployee) {
        LOGGER.info("Adding new employee");
        String sql = Queries.ADD_EMPLOYEE;
        long newId = getTotalEmployeeCount() + 1;
        int rowsAffected = jdbcTemplate.update(sql, newId, newEmployee.getName(), new java.sql.Date (newEmployee.getBirthday().getTime()),
            newEmployee.getDepartmentId(), newEmployee.getSiteId(), newEmployee.getSecurityLevel(), newEmployee.getSalary());
        if (rowsAffected == 1) {
            return newId;
        } else {
            return 0;
        }
    }
    
    @Override
    public boolean deleteEmployee(long id) {
        String sql = Queries.DELETE_EMPLOYEE;
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected == 1;
    }

    @Override
    public Site getSite(long employeeId) {
        String sql = "SELECT * FROM sites WHERE id = (SELECT workplace FROM employees WHERE id = ?)";
        Site theSite = jdbcTemplate.queryForObject(sql, Site.class, employeeId);
        return theSite;
    }

    @Override
    public long getEmployeeId(NewEmployeeRequest newEmployeeRequest) {
        String sql = "SELECT id FROM employees WHERE name = ? AND birthday = ? AND workplace = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, newEmployeeRequest.getName(), newEmployeeRequest.getBirthday(),
                newEmployeeRequest.getSiteId());
    }
}
