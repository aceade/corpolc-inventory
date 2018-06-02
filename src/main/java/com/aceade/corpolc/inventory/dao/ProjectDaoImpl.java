/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.EmployeeRowMapper;
import com.aceade.corpolc.inventory.database.ProjectRowMapper;
import com.aceade.corpolc.inventory.database.Queries;
import com.aceade.corpolc.inventory.database.SiteRowMapper;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author philip
 */
public class ProjectDaoImpl extends BaseDao implements ProjectDao {
    
    private static final Logger LOGGER = LogManager.getLogger(ProjectDaoImpl.class);
    
    @Override
    public Project getProject(long id, boolean sanitise) {
        String sql = Queries.SELECT_PROJECT;
        return (Project) jdbcTemplate.queryForObject(sql, new ProjectRowMapper(), id);
    }
    
    @Override
    public int getTotalProjectCount(){
        String sql = Queries.COUNT_PROJECTS;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    @Override
    public List<Employee> getEmployeesForProject(long projectId) {
        String sql = Queries.SELECT_EMPLOYEES_FOR_PROJECT;
        List<Employee> workers = jdbcTemplate.query(sql, new EmployeeRowMapper(), projectId);
        LOGGER.info("Returned [" + workers.size() + "] workers");
        return workers;
    }
    
    @Override
    public List<Site> getSitesForProject(long id){
        String sql = Queries.SELECT_SITES_FOR_PROJECT;
        List<Site> sites = jdbcTemplate.query(sql, new SiteRowMapper(), id);
        return sites;  
    }

    @Override
    public List<Project> getProjectsForEmployee(long employeeId, boolean sanitise) {
        String sql = "SELECT p.id, p.title, p.summary, p.security_rating, p.status, ep.employee_id FROM projects AS p, employee_projects AS ep WHERE ep.employee_id = ? AND ep.project_id = p.id";
        List<Project> projects = jdbcTemplate.query(sql, new ProjectRowMapper(), employeeId);
        return projects;
    }

    @Override
    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";
        List<Project> results = jdbcTemplate.query(sql, new ProjectRowMapper());
        return results;
    }

    @Override
    public long addNewProject(NewProjectRequest newProjectRequest) {
        String sql = "INSERT INTO projects (id, title, summary, budget, \"security_rating\", status) VALUES(?, ?, ?, ?, ?, 'PROPOSED')";
        
        int totalProjects = getTotalProjectCount();
        int newId = totalProjects + 1;
        int rowsAffected = jdbcTemplate.update(sql, newId, newProjectRequest.getTitle(), newProjectRequest.getSummary(),
                newProjectRequest.getBudget(), newProjectRequest.getSecurityLevel());
        
        if (rowsAffected == 1) {
            return newId;
        } else {
            return 0;
        }
        
    }

    @Override
    public boolean setProjectStatus(ChangeProjectStatusRequest request) {
        long id = request.getProjectId();
        LOGGER.info("Updating status of project [" + id + "]");
        String sql = "UPDATE projects SET status = ?::project_status WHERE id = ?";
        
        // since jdbcTemplate.update returns the number of affected rows, might as well check that a single row updated
        int rowsAffected = jdbcTemplate.update(sql, request.getNewProjectStatus().name(), id);
        return rowsAffected == 1;
    }

    @Override
    public boolean isUserOnProject(long projectId, String username) {
        String sql = "SELECT * FROM employee_projects ep WHERE ep.project_id=? AND ep.employee_id = (SELECT \"employeeId\" FROM users WHERE username = ?)";
        return (Boolean) jdbcTemplate.queryForObject(sql, new RowMapper(){
            @Override
            public Boolean mapRow(ResultSet rs, int i) throws SQLException {
                return (rs.getLong("project_id") == projectId);
            }
            
        }, projectId, username);
    }
    
    /**
     * Used only for auditing. Exists so I can get the project ID
     * @param request
     * @return 
     */
    @Override
    public long getProjectId(NewProjectRequest request) {
        String sql = "SELECT id FROM projects WHERE summary = ? AND title = ? AND budget = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, request.getSummary(), request.getTitle(), request.getBudget());
    }
    
}
