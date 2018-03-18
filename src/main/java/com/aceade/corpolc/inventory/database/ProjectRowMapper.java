/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.ProjectStatus;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author philip
 */
public class ProjectRowMapper implements RowMapper {

    @Override
    public Project mapRow(ResultSet rs, int i) throws SQLException {
        Project project = new Project();
        project.setId(rs.getLong("id"));
        project.setTitle(rs.getString("title"));
        project.setSummary(rs.getString("summary"));
        project.setBudget(rs.getDouble("budget"));
        project.setStatus(ProjectStatus.valueOf(rs.getString("status")));
        project.setSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("security_rating")));
        return project;
    }
    
}
