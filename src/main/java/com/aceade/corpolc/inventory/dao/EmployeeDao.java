/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

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
        Employee employee = null;
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    employee = createEmployee(rs);
                }
            }
            
        } catch (SQLException e) {
            LOGGER.error("Could not retrieve employee ["+id+"]", e);
        }
        
        return employee;
    }
    
    public List<Employee> getAllEmployees() {
        String sql = Queries.SELECT_ALL_EMPLOYEES;
        List<Employee> employees = new ArrayList<>();
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Employee employee = createEmployee(rs);
                employees.add(employee);
            }
            LOGGER.info("Returned [" + employees.size()+"] employees");
            
        } catch (SQLException e) {
            LOGGER.error("Could not retrieve all employees ", e);
        }
        
        return employees;
    }
    
    private Employee createEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee(rs.getLong("id"), rs.getString("name"));
        employee.setBirthday(rs.getDate("birthday"));
        employee.setClearanceLevel(ServiceLibrary.getSecurityRating(rs.getInt("securityLevel")));
        employee.setDepartment(ServiceLibrary.getDepartment(rs.getInt("department")));
        employee.setSalary(rs.getDouble("salary"));
        employee.setCurrentlyEmployed(rs.getBoolean("current"));
        Site workplace = new Site(rs.getLong("siteid"));
        workplace.setCountry(rs.getString("country"));
        workplace.setRegion(rs.getString("region"));
        workplace.setPostalAddress(rs.getString("postalAddress"));
        workplace.setMinimumSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("siteSecurityLevel")));
        employee.setWorkplace(workplace);
        return employee;
    }
    
    public int getTotalEmployeeCount() {
        String sql = "SELECT COUNT(id) from employees AS count";
        int amount = 0;
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                amount = rs.getInt("count");
            }
            LOGGER.debug("Returned [" + amount+"] employees");
        } catch  (SQLException e) {
            LOGGER.error("Could not retrieve number of employees ", e);
        }
        return amount;
    }

    public long addEmployee(NewEmployeeRequest newEmployee) {
        LOGGER.info("Adding new employee");
        String sql = Queries.ADD_EMPLOYEE;
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            long newId = getTotalEmployeeCount();
            stmt.setLong(1, newId + 1);
            stmt.setString(2, newEmployee.getName());
            java.util.Date date = newEmployee.getBirthday();
            LOGGER.info(date);
            stmt.setDate(3, new java.sql.Date (newEmployee.getBirthday().getTime()));
            stmt.setInt(4, newEmployee.getDepartmentId());
            stmt.setInt(5, newEmployee.getSiteId());
            stmt.setInt(6, newEmployee.getSecurityLevel());
            stmt.setDouble(7, newEmployee.getSalary());
            stmt.execute();
            
            return newId;
            
        } catch (SQLException e) {
            LOGGER.error("Could not add new employee ", e);
            return 0;
        }
    }
    
    public boolean deleteEmployee(long id) {
        String sql = Queries.DELETE_EMPLOYEE;
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not delete employee ["+id+"]", e);
            return false;
        }
    }
}
