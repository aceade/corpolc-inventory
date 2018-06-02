/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.base.ProjectStatus;
import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import com.aceade.corpolc.inventory.model.response.AuditEntry;
import com.aceade.corpolc.inventory.model.response.EmployeeAuditEntry;
import com.aceade.corpolc.inventory.model.response.ProjectAuditEntry;
import com.aceade.corpolc.inventory.model.response.SiteAuditEntry;
import com.aceade.corpolc.inventory.model.response.UserAuditEntry;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author philip
 */
public class AuditDaoImpl implements AuditDao {

    @Inject
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void logUserStatusChange(String remoteUser, String user) {
        String sql = "INSERT INTO user_auditing (uid, username, previous_state, last_changed) VALUES (?, ?, false, ?); "
                + "UPDATE user_auditing SET previous_state = (SELECT enabled FROM users u WHERE u.username = ? ) WHERE uid = ? AND last_changed = ? ";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(sql, user, remoteUser, timestamp, user, user, timestamp);
    }

    @Override
    public void logNewUser(NewUserRequest request, String remoteUser) {
        String sql = "INSERT INTO user_auditing (uid, username, previous_state, last_changed) VALUES (?, ?, false, ?)";
        long timestamp = ServiceLibrary.getDate().getTime();
        String uid = request.getUsername();
        jdbcTemplate.update(sql, uid, remoteUser, timestamp);
    }

    @Override
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

    @Override
    public void logNewProject(NewProjectRequest request, String remoteUser, long projectId) {
        String sql = "INSERT INTO project_auditing(project_id, username, last_changed) VALUES(?, ?,?)";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(sql, projectId, remoteUser, timestamp);
    }

    @Override
    public void logEmployeeAdded(NewEmployeeRequest newEmployeeRequest, String remoteUser, long employeeId) {
        String sql = "INSERT INTO employee_auditing(employee_id, username, last_changed) VALUES(?,?,?)";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(sql, employeeId, remoteUser, timestamp);
    }

    @Override
    public void logEmployeeStatusChange(long employeeId, String remoteUser) {
        String sql = "SELECT current FROM employees WHERE id = ?";
        boolean previousValue = jdbcTemplate.queryForObject(sql, Boolean.class, employeeId);
        
        String updateSql = "INSERT into employee_auditing (employee_id, last_changed, username, previously_current) "
                + "VALUES (?,?,?,?)";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(updateSql, employeeId, timestamp, remoteUser, previousValue);
    }

    @Override
    public void logSiteAdded(NewSiteRequest req, String remoteUser, long siteId) {
        String sql = "INSERT INTO site_auditing (site_id, username, last_accessed, change_reason) VALUES(?,?,?,?::resource_change_reason)";
        long timestamp = ServiceLibrary.getDate().getTime();
        jdbcTemplate.update(sql, siteId, remoteUser, timestamp, AuditingReason.creating.toString());
    }

    @Override
    public List<AuditEntry> getUserAuditRecords(String userId) {
        String sql = "SELECT * FROM user_auditing WHERE uid = ?";
        return jdbcTemplate.query(sql, new RowMapper(){
            @Override
            public UserAuditEntry mapRow(ResultSet rs, int i) throws SQLException {
                UserAuditEntry entry = new UserAuditEntry();
                entry.setTimestamp(rs.getLong("last_changed"));
                entry.setPreviousState(rs.getBoolean("previous_state"));
                entry.setUsername(rs.getString("username"));
                return entry;
            }
            
        }, userId);

    }

    @Override
    public List<AuditEntry> getSiteAuditRecords(long siteId) {
        String sql = "SELECT * FROM site_auditing WHERE site_id = ?";
        return jdbcTemplate.query(sql, new RowMapper(){
            @Override
            public SiteAuditEntry mapRow(ResultSet rs, int i) throws SQLException {
                SiteAuditEntry entry = new SiteAuditEntry();
                entry.setTimestamp(rs.getLong("last_accessed"));
                entry.setUsername(rs.getString("username"));
                entry.setPreviousCountry(rs.getString("previous_country"));
                entry.setPreviousRegion(rs.getString("previous_region"));
                entry.setPreviousAddress(rs.getString("previous_address"));
                entry.setPreviousSecurityRating(ServiceLibrary.getSecurityRating(rs.getInt("previous_security_rating")));
                entry.setAuditingReason(AuditingReason.valueOf(rs.getString("change_reason")));
                return entry;
            }
            
        }, siteId);
    }

    @Override
    public List<AuditEntry> getProjectAuditRecords(long projectId) {
        String sql = "SELECT * FROM project_auditing WHERE project_id = ?";
        return jdbcTemplate.query(sql, new RowMapper(){
            @Override
            public ProjectAuditEntry mapRow(ResultSet rs, int i) throws SQLException {
                ProjectAuditEntry entry = new ProjectAuditEntry();
                
                // this will be null if the project is fresh
                String projectStatusString = rs.getString("previous_status") != null ? rs.getString("previous_status") : "PROPOSED";
                entry.setTimestamp(rs.getLong("last_changed"));
                entry.setUsername(rs.getString("username"));
                entry.setPreviousBudget(rs.getDouble("previous_budget"));
                entry.setPreviousState(ProjectStatus.valueOf(projectStatusString));
                entry.setPreviousSummary(rs.getString("previous_summary"));
                entry.setPreviousTitle(rs.getString("previous_title"));
                entry.setPreviousSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("previous_security_rating")));
                return entry;
            }
            
        }, projectId);
    }

    @Override
    public List<AuditEntry> getEmployeeAuditRecords(long employeeId) {
        String sql = "SELECT * FROM employee_auditing WHERE employee_id = ?";
        return jdbcTemplate.query(sql, new RowMapper(){
            @Override
            public EmployeeAuditEntry mapRow(ResultSet rs, int i) throws SQLException {
                EmployeeAuditEntry entry = new EmployeeAuditEntry();
                entry.setTimestamp(rs.getLong("last_changed"));
                entry.setUsername(rs.getString("username"));
                entry.setPreviousBirthday(rs.getDate("previous_birthday"));
                entry.setPreviousName(rs.getString("previous_name"));
                entry.setPreviousSalary(rs.getDouble("previous_salary"));
                entry.setPreviousSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("previous_security_level")));
                entry.setPreviousState(rs.getBoolean("previously_current"));
                entry.setPreviousWorkplace(rs.getLong("previous_workplace"));
                return entry;
            }
        }, employeeId);
    }
    
}
