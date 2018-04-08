/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.EmployeeRowMapper;
import com.aceade.corpolc.inventory.database.Queries;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author philip
 */
public class EmployeeDao extends BaseDao {
    
    private static final Logger LOGGER = LogManager.getLogger(EmployeeDao.class);
    
    public Employee getEmployee (long id) {
        String sql = Queries.SELECT_EMPLOYEE;
        return (Employee) jdbcTemplate.queryForObject(sql, Employee.class, id);
    }
    
    public List<Employee> getAllEmployees() {
        String sql = Queries.SELECT_ALL_EMPLOYEES;
        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return employees;
    }
    
    public int getTotalEmployeeCount() {
        String sql = "SELECT COUNT(id) from employees AS count";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

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
    
    public boolean deleteEmployee(long id) {
        String sql = Queries.DELETE_EMPLOYEE;
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected == 1;
    }
}
