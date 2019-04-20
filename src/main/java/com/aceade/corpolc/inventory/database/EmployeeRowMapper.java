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
 * Maps employee rows to an Employee
 * Note to self: do <strong>not</strong> call rs.next() inside this; 
 * the jdbcTemplate will call this for each row in the ResultSet
 * @author philip
 */
public class EmployeeRowMapper implements RowMapper {

    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        Employee drone = new Employee(rs.getLong("id"), rs.getString(("name")));
        drone.setClearanceLevel(ServiceLibrary.getSecurityRating(rs.getString("security_level")));
        drone.setSalary(rs.getDouble("salary"));
        drone.setDepartment(ServiceLibrary.getDepartment(rs.getString("department_type")));
        drone.setBirthday(ServiceLibrary.getDateFromTimestamp(rs.getLong("birthday")));
        drone.setCurrentlyEmployed(true);
        drone.setWorkplace(new Site(rs.getLong("workplace")));
        return drone;
    }

}
