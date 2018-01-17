/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.Queries;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author philip
 */
public class ProjectDao extends BaseDao {
    
    public Project getProject(long id) {
        String sql = Queries.SELECT_PROJECT;
        Project project = new Project();
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                project.setTitle(rs.getString("title"));
                project.setSummary(rs.getString("summary"));
                project.setBudget(rs.getDouble("budget"));
                project.setSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("security_rating")));
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return project;
    }
    
    public int getTotalProjectCount(){
        String sql = Queries.COUNT_PROJECTS;
        Connection dbConnection = connectionFactory.getConnection();
        int amount = 0;
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                amount = rs.getInt("numberOfProjects");
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        return amount;
    }
    
    public List<Employee> getEmployeesForProject(long projectId) {
        String sql = Queries.SELECT_EMPLOYEES_FOR_PROJECT;
        Connection dbConnection = connectionFactory.getConnection();
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, projectId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Employee drone = new Employee(rs.getLong("id"), rs.getString(("name")));
                drone.setClearanceLevel(ServiceLibrary.getSecurityRating(rs.getInt("securityLevel")));
                drone.setSalary(rs.getDouble("salary"));
                drone.setDepartment(ServiceLibrary.getDepartment(rs.getInt("department")));
                drone.setBirthday(rs.getDate("birthday"));
                drone.setWorkplace(new Site(rs.getLong("workplace")));
                employees.add(drone);
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return employees;
    }
    
    public List<Site> getSitesForProject(long id){
        String sql = Queries.SELECT_SITES_FOR_PROJECT;
        
        Connection dbConnection = connectionFactory.getConnection();
        List<Site> sites = new ArrayList<>();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Site site = new Site(rs.getLong("id"));
                    site.setCountry(rs.getString("country"));
                    site.setMinimumSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("securityLevel")));
                    site.setRegion(rs.getString("region"));
                    site.setPostalAddress(rs.getString("postalAddress"));
                    sites.add(site);
                }
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return sites;
    }

    public List<Project> getProjectsForEmployee(long employeeId) {
        String sql = "SELECT p.id, p.title, p.summary, p.security_rating, ep.employee_id FROM projects AS p, employee_projects AS ep WHERE ep.employee_id = ? AND ep.project_id = p.id";
        List<Project> projects = new ArrayList<>();
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setTitle(rs.getString("title"));
                    project.setSummary(rs.getString("summary"));
//                    project.setBudget(rs.getDouble("budget"));
                    project.setSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("security_rating")));
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return projects;
    }
    
}
