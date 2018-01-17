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

/**
 *
 * @author philip
 */
public class EmployeeDao extends BaseDao {
    
    public Employee getEmployee (long id) {
        String sql = Queries.SELECT_EMPLOYEE;
        Employee employee = null;
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                employee = createEmployee(rs);
            }
            System.out.println(employee);
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return employee;
    }
    
    public List<Employee> getAllEmployees() {
        String sql = Queries.SELECT_ALL_EMPLOYEES;
        List<Employee> employees = new ArrayList<>();
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Employee employee = createEmployee(rs);
                employees.add(employee);
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return employees;
    }
    
    private Employee createEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee(rs.getLong("id"), rs.getString("name"));
        employee.setBirthday(rs.getDate("birthday"));
        employee.setClearanceLevel(ServiceLibrary.getSecurityRating(rs.getInt("securityLevel")));
        employee.setDepartment(ServiceLibrary.getDepartment(rs.getInt("department")));
        employee.setSalary(rs.getDouble("salary"));
        Site workplace = new Site(rs.getLong("siteid"));
        workplace.setCountry(rs.getString("country"));
        workplace.setRegion(rs.getString("region"));
        workplace.setPostalAddress(rs.getString("postalAddress"));
        workplace.setMinimumSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("minimumSecurityLevel")));
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
        } catch  (SQLException e) {
            System.err.println(e);
        }
        return amount;
    }

    public boolean addEmployee(NewEmployeeRequest newEmployee) {
        String sql = Queries.ADD_EMPLOYEE;
        boolean success = false;
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            long newId = getTotalEmployeeCount();
            stmt.setLong(1, newId + 1);
            stmt.setString(2, newEmployee.getName());
            stmt.setDate(3, new java.sql.Date (newEmployee.getBirthday().getTime()));
            stmt.setInt(4, newEmployee.getDepartmentId());
            stmt.setInt(5, newEmployee.getSiteId());
            stmt.setInt(6, newEmployee.getSecurityLevel());
            stmt.setDouble(7, newEmployee.getSalary());
            success = stmt.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
    public boolean deleteEmployee(long id) {
        String sql = Queries.DELETE_EMPLOYEE;
        boolean success = false;
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            success = stmt.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
}
