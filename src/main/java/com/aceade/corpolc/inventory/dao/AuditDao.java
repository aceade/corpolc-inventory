/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author philip
 */
public class AuditDao {

    @Inject
    private JdbcTemplate jdbcTemplate;
    
    public void logUserStatusChange(String remoteUser, String user) {
        String sql = "INSERT INTO user_auditing (uid, username, previous_state, last_changed) VALUES (?, ?, false, ?); "
                + "UPDATE user_auditing SET previous_state = (SELECT enabled FROM users u WHERE u.username = ? ) WHERE uid = ? AND last_changed = ? ";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(sql, user, remoteUser, timestamp, user, user, timestamp);
    }

    public void logNewUser(NewUserRequest request, String remoteUser) {
        String sql = "INSERT INTO user_auditing (uid, username, previous_state, last_changed) VALUES (?, ?, false, ?)";
        long timestamp = ServiceLibrary.getDate().getTime();
        String uid = request.getUsername();
        jdbcTemplate.update(sql, uid, remoteUser, timestamp);
    }

    public void logProjectStatusChange(ChangeProjectStatusRequest request, String remoteUser) {
        String sql = "INSERT INTO project_auditing(project_id, username, last_changed) VALUES (?,?,?);";
        long projectId = request.getProjectId();
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(sql, projectId, remoteUser, timestamp);
        
        // Keeping the queries separate to make them easier to edit
        String updateSql = "UPDATE project_auditing SET previous_status = (SELECT status FROM projects WHERE id = ?), "
                + "previous_summary = (SELECT summary FROM projects WHERE id = ?)," 
                + "previous_title = (SELECT title FROM projects WHERE id = ?), "
                + "previous_budget = (SELECT budget FROM projects WHERE id = ?)," 
                + "previous_security_rating = (SELECT security_rating FROM projects WHERE id = ?) WHERE project_id = ?";
        jdbcTemplate.update(updateSql, projectId, projectId, projectId, projectId, projectId ,projectId);
    }

    public void logNewProject(NewProjectRequest request, String remoteUser, long projectId) {
        String sql = "INSERT INTO project_auditing(project_id, username, last_changed) VALUES(?, ?,?)";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(sql, projectId, remoteUser, timestamp);
    }

    public void logEmployeeAdded(NewEmployeeRequest newEmployeeRequest, String remoteUser, long employeeId) {
        String sql = "INSERT INTO employee_auditing(employee_id, username, last_changed) VALUES(?,?,?)";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(sql, employeeId, remoteUser, timestamp);
    }

    public void logEmployeeStatusChange(long employeeId, String remoteUser) {
        String sql = "SELECT current FROM employees WHERE id = ?";
        boolean previousValue = jdbcTemplate.queryForObject(sql, Boolean.class, employeeId);
        
        String updateSql = "INSERT into employee_auditing (employee_id, last_changed, username, previously_current) "
                + "VALUES (?,?,?,?)";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(updateSql, employeeId, timestamp, remoteUser, previousValue);
    }
    
}
