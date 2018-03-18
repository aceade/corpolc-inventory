/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.ProjectRowMapper;
import com.aceade.corpolc.inventory.database.Queries;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.ProjectStatus;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author philip
 */
public class ProjectDao extends BaseDao {
    
    private static final Logger LOGGER = LogManager.getLogger(ProjectDao.class);
    
    public Project getProject(long id) {
        String sql = Queries.SELECT_PROJECT;
//        Project project = new Project();
        
//        Project project = jdbcTemplate.queryForObject(sql, Project.class, id);
        Project project = (Project) jdbcTemplate.queryForObject(sql, new ProjectRowMapper(), id);
        
        
//        Connection dbConnection = connectionFactory.getConnection();
//        
//        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            
//            while (rs.next()) {
//                project.setId(rs.getLong("id"));
//                project.setTitle(rs.getString("title"));
//                project.setSummary(rs.getString("summary"));
//                project.setBudget(rs.getDouble("budget"));
//                project.setStatus(ProjectStatus.valueOf(rs.getString("status")));
//                project.setSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("security_rating")));
//            }
//            
//        } catch (SQLException e) {
//            LOGGER.error("Could not retrieve project by id ["+id+"]", e);
//        }
        
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
            LOGGER.error("Could not retrieve total number of projects", e);
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
                drone.setCurrentlyEmployed(true);
                drone.setWorkplace(new Site(rs.getLong("workplace")));
                
                employees.add(drone);
            }
            
            
        } catch (SQLException e) {
            LOGGER.error("Could not retrieve project by id ["+projectId+"]", e);
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
            LOGGER.error("Could not retrieve sites involved in project with id ["+id+"]", e);
        }
        
        return sites;
    }

    public List<Project> getProjectsForEmployee(long employeeId) {
        String sql = "SELECT p.id, p.title, p.summary, p.security_rating, p.status, ep.employee_id FROM projects AS p, employee_projects AS ep WHERE ep.employee_id = ? AND ep.project_id = p.id";
        List<Project> projects = new ArrayList<>();
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setId(rs.getLong("id"));
                    project.setTitle(rs.getString("title"));
                    project.setSummary(rs.getString("summary"));
                    project.setBudget(rs.getDouble("budget"));
                    project.setStatus(ProjectStatus.valueOf(rs.getString("status")));
                    project.setSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("security_rating")));
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Could not retrieve projects for employee ["+employeeId+"]", e);
        }
        
        return projects;
    }

    public List<Project> getAll() {
        List<Project> results = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getLong("id"));
                project.setTitle(rs.getString("title"));
                project.setSummary(rs.getString("summary"));
                project.setBudget(rs.getDouble("budget"));
                project.setStatus(ProjectStatus.valueOf(rs.getString("status")));
                project.setSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("security_rating")));
                results.add(project);
            }
            
        } catch (SQLException e) {
            LOGGER.error("Could not retrieve all projects!", e);
        }
        
        return results;
    }

    public long addNewProject(NewProjectRequest newProjectRequest) {
        
        String sql = "INSERT INTO projects (id, title, summary, budget, \"security_rating\", status) VALUES(?, ?,?,?,?, PROPOSED)";
        Connection dbConnection = connectionFactory.getConnection();
        
        int totalProjects = getTotalProjectCount();
        int newId = totalProjects + 1;
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            
            stmt.setLong(1, newId);
            stmt.setString(2, newProjectRequest.getTitle());
            stmt.setString(3, newProjectRequest.getSummary());
            stmt.setDouble(4, newProjectRequest.getBudget());
            stmt.setInt(5, newProjectRequest.getSecurityLevel());
            stmt.execute();
            return newId;
        } catch (SQLException e) {
            LOGGER.error("Unable to add new project", e);
            return 0;
        }
        
    }

    public boolean setProjectStatus(ChangeProjectStatusRequest request) {
        long id = request.getProjectId();
        LOGGER.info("Updating status of project [" + id + "]");
        String sql = "UPDATE projects SET status = ?::project_status WHERE id = ?";
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement st = dbConnection.prepareStatement(sql)) {
            st.setString(1, request.getNewProjectStatus().name());
            st.setLong(2, id);
            st.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Unable to update project ["+id+"]", e);
            return false;
        }
    }
    
}
