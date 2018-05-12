/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

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
    
}
