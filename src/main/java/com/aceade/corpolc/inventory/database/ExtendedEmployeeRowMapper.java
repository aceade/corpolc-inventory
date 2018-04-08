/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Extended version of EmployeeRowMapper; adds more details
 * @author philip
 */
public class ExtendedEmployeeRowMapper implements RowMapper {
    
    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        Employee drone = new Employee(rs.getLong("id"), rs.getString(("name")));
        drone.setClearanceLevel(ServiceLibrary.getSecurityRating(rs.getInt("securityLevel")));
        drone.setSalary(rs.getDouble("salary"));
        drone.setDepartment(ServiceLibrary.getDepartment(rs.getInt("department")));
        drone.setBirthday(rs.getDate("birthday"));
        drone.setCurrentlyEmployed(rs.getBoolean("current"));
        
        Site site = new Site (rs.getLong("workplace"));
        site.setCountry(rs.getString("country"));
        site.setRegion(rs.getString("region"));
        site.setPostalAddress(rs.getString("postalAddress"));
        site.setMinimumSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("siteSecurityLevel")));
        drone.setWorkplace(site);
        return drone;
    }
    
}
